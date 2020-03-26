"""
Implement a simple Convolutional Neural Network in TensorFlow with two convolutional layers, 
followed by two fully-connected layers at the end
"""

# imports
import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt

# ___Step 1___: Load MNIST data
from tensorflow.examples.tutorials.mnist import input_data

# helper function for the MNIST data loading
# modes:    train (which loads the training and validation images and their corresponding labels)
#           test (which loads the test images and their corresponding labels)
def load_data(mode='train'):
    """
    Function to (download and) load the MNIST data
    :param mode: train or test
    :return: images and the corresponding labels
    """
    mnist = input_data.read_data_sets("MNIST_data/", one_hot=True)

    if mode == 'train':
        x_train, y_train, x_valid, y_valid = mnist.train.images, mnist.train.labels, \
                                             mnist.validation.images, mnist.validation.labels
        x_train, _ = reformat(x_train, y_train)
        x_valid, _ = reformat(x_valid, y_valid)
        return x_train, y_train, x_valid, y_valid
    elif mode == 'test':
        x_test, y_test = mnist.test.images, mnist.test.labels
        x_test, _ = reformat(x_test, y_test)
    return x_test, y_test

def reformat(x, y):
    """
    Reformats the data to the format acceptable for convolutional layers
    :param x: input array
    :param y: corresponding labels
    :return: reshaped input and labels
    """
    img_size, num_ch, num_class = int(np.sqrt(x.shape[-1])), 1, len(np.unique(np.argmax(y, 1)))
    dataset = x.reshape((-1, img_size, img_size, num_ch)).astype(np.float32)
    labels = (np.arange(num_class) == y[:, None]).astype(np.float32)
    return dataset, labels

def randomize(x, y):
    """ Randomizes the order of data samples and their corresponding labels"""
    permutation = np.random.permutation(y.shape[0])
    shuffled_x = x[permutation, :, :, :]
    shuffled_y = y[permutation]
    return shuffled_x, shuffled_y

def get_next_batch(x, y, start, end):
    x_batch = x[start:end]
    y_batch = y[start:end]
    return x_batch, y_batch

# load data
x_train, y_train, x_valid, y_valid = load_data(mode='train')

print("Size of MNIST:")
print("- Train images:\t\t{}".format(len(x_train)))
print("- Train labels:\t\t{}".format(len(y_train)))
print("- validation images:\t{}".format(len(x_valid)))
print("- validation labels:\t{}".format(len(y_valid)))

print("Shape of MNIST:")
print('- x_train:\t{}'.format(x_train.shape))
print('- y_train:\t{}'.format(y_train.shape))
print('- x_train:\t{}'.format(x_valid.shape))
print('- y_valid:\t{}'.format(y_valid.shape))

# ___Step 2___: Hyper-parameters

# batch size: the number of training examples in one forward/backward pass. 
# iteration: one forward pass and one backward pass of one batch of images the training examples.
# epoch: one forward pass and one backward pass of all the training examples. epoch(1)=55000/batch size(100)=550(iteration)
epochs = 10             # Total number of training epochs
batch_size = 100        # Training batch size
display_freq = 100      # Frequency of displaying the training results
learning_rate = 0.001   # The optimization initial learning rate
n_classes = 10          # Number of classes, one class per digit
n_channels = 1

# data dimenstion
img_h = img_w = 28             # MNIST images are 28x28
img_size_flat = img_h * img_w  # 28x28=784, the total number of pixels

# path to the folder that we want to save the logs for Tensorboard
logs_path = "./graphs"  

# ___Step 2.1___: Network configuration

# 1st Convolutional Layer
filter_size1 = 5    # Convolution filters are 5 x 5 pixels.
num_filters1 = 16   # There are 16 of these filters.
stride1 = 1         # The stride of the sliding window

# 2nd Convolutional Layer
filter_size2 = 5    # Convolution filters are 5 x 5 pixels.
num_filters2 = 32   # There are 32 of these filters.
stride2 = 1         # The stride of the sliding window

# Fully-connected layer.
h1 = 128            # Number of neurons in fully-connected layer.

# ___Step 3___: Variables

def weight_variable(shape):
    """
    Create a weight variable with appropriate initialization
    :param name: weight name
    :param shape: weight shape
    :return: initialized weight variable
    """
    initer = tf.truncated_normal_initializer(stddev=0.01)
    return tf.get_variable('W', dtype=tf.float32, shape=shape, initializer=initer)

def bias_variable(shape):
    """
    Create a bias variable with appropriate initialization
    :param name: bias variable name
    :param shape: bias variable shape
    :return: initialized bias variable
    """
    initial = tf.constant(0., shape=shape, dtype=tf.float32)
    return tf.get_variable('b', dtype=tf.float32, initializer=initial)

# Helper-function for creating a new Convolutional Layer
def conv_layer(x, filter_size, num_filters, stride, name):
    """
    Create a 2D convolution layer
    :param x: input from previous layer
    :param filter_size: size of each filter
    :param num_filters: number of filters (or output feature maps)
    :param stride: filter stride
    :param name: layer name
    :return: The output array
    """
    with tf.variable_scope(name):
        num_in_channel = x.get_shape().as_list()[-1]
        shape = [filter_size, filter_size, num_in_channel, num_filters]

        W = weight_variable(shape=shape)
        b = bias_variable(shape=[num_filters])

        # To visualize the parameters, we will use tf.summary class to write the summaries of parameters. 
        tf.summary.histogram('W', W)
        tf.summary.histogram('b', b)

        layer = tf.nn.conv2d(x, W, strides=[1, stride, stride, 1], padding="SAME")
        layer += b

        return tf.nn.relu(layer)

# Helper-function for creating a new Max-pooling Layer
def max_pool(x, ksize, stride, name):
    """
    Create a max pooling layer
    :param x: input to max-pooling layer
    :param ksize: size of the max-pooling filter
    :param stride: stride of the max-pooling filter
    :param name: layer name
    :return: The output array
    """
    return tf.nn.max_pool(x, ksize=[1, ksize, ksize, 1], strides=[1, stride, stride, 1], padding="SAME", name=name)

# Helper-function for flattening a layer
def flatten_layer(layer):
    """
    Flattens the output of the convolutional layer to be fed into fully-connected layer
    :param layer: input array
    :return: flattened array
    """
    with tf.variable_scope('Flatten_layer'):
        layer_shape = layer.get_shape()
        num_features = layer_shape[1:4].num_elements()
        layer_flat = tf.reshape(layer, [-1, num_features])
    return layer_flat

# Helper-function for creating a new fully-connected Layer
def fc_layer(x, num_units, name, use_relu=True):
    """
    Create a fully-connected layer
    :param x: input from previous layer
    :param num_units: number of hidden units in the fully-connected layer
    :param name: layer name
    :param use_relu: boolean to add ReLU non-linearity (or not)
    :return: The output array
    """
    with tf.variable_scope(name):
        in_dim = x.get_shape()[1]

        W = weight_variable(shape=[in_dim, num_units])
        b = bias_variable(shape=[num_units])

        tf.summary.histogram('W', W)
        tf.summary.histogram('b', b)

        layer = tf.matmul(x, W)
        layer += b

        if use_relu:
            layer = tf.nn.relu(layer)
        return layer

# ___Step 4___: Create the newwork graph

# Placeholders for the inputs (x) and corresponding labels (y)
with tf.name_scope('Input'):
    x = tf.placeholder(tf.float32, shape=[None, img_h, img_w, n_channels], name='X')
    y = tf.placeholder(tf.float32, shape=[None, n_classes], name='Y')

# Create the network layers
conv1 = conv_layer(x, filter_size1, num_filters1, stride1, name='conv1')
pool1 = max_pool(conv1, ksize=2, stride=2, name='pool1')

conv2 = conv_layer(pool1, filter_size2, num_filters2, stride2, name='conv2')
pool2 = max_pool(conv2, ksize=2, stride=2, name='pool2')

layer_flat = flatten_layer(pool2)
fc1 = fc_layer(layer_flat, h1, 'FC1', use_relu=True)

# ___Step 5___: Create the model structure

output_logits = fc_layer(fc1, n_classes, 'OUT', use_relu=False)

# ___Step 6___: Define the loss function, optimizer, accuracy, and predicted class
with tf.variable_scope('Train'):
    with tf.variable_scope('Loss'):
        loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(labels=y, logits=output_logits), name='loss')
        tf.summary.scalar('loss', loss)
    with tf.variable_scope('Optimizer'):
        optimizer = tf.train.AdamOptimizer(learning_rate=learning_rate, name='Adam-op').minimize(loss)
    with tf.variable_scope('Accuracy'):
        correct_prediction = tf.equal(tf.argmax(output_logits, 1), tf.argmax(y, 1), name='correct_pred')
        accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32), name='accuracy')
        tf.summary.scalar('accuracy', accuracy)
    with tf.variable_scope('Prediction'):
        # Network predictions
        cls_prediction = tf.argmax(output_logits, axis=1, name='predictions')
 
 # ___Step 7___: Initialize all variables

# Creating the op for initializing all variables
init = tf.global_variables_initializer()

# Finally, to merge all the summeries
merged = tf.summary.merge_all()

# ___Step 8___: Training

# Create an interactive session (to keep the session in the other cells)
sess = tf.InteractiveSession()

# Initialize all variables
sess.run(init)

# To write all the summaries on the disk
train_writer = tf.summary.FileWriter(logs_path, sess.graph)

# Number of training iterations in each epoch
num_tr_iter = int(len(y_train) / batch_size)

global_step = 0 # for visualize

# Loop for epoch
import datetime as time

for epoch in range(epochs):
    print('Training epoch: {}'.format(epoch + 1))

    # Randomly shuffle the training data at the beginning of each epoch 
    x_train, y_train = randomize(x_train, y_train)

    time_start = time.datetime.now()

    # Loop for iteration
    for iteration in range(num_tr_iter):
        global_step += 1    # for visualize

        # get batch data
        start = iteration * batch_size
        end = (iteration + 1) * batch_size
        x_batch, y_batch = get_next_batch(x_train, y_train, start, end)

        # Run optimization op (backprop)
        feed_dict_batch = {x: x_batch, y: y_batch}
        sess.run(optimizer, feed_dict=feed_dict_batch)

        if iteration % display_freq == 0:
            # Calculate and display the batch loss and accuracy
            loss_batch, acc_batch, summary_tr = sess.run([loss, accuracy, merged],
                                                         feed_dict=feed_dict_batch)
            train_writer.add_summary(summary_tr, global_step)

            print("iter {0:3d}:\t Loss={1:.2f},\tTraining Accuracy={2:.01%}".
                  format(iteration, loss_batch, acc_batch))

    # Run validation after every epoch
    feed_dict_valid = {x: x_valid, y: y_valid}
    loss_valid, acc_valid = sess.run([loss, accuracy], feed_dict=feed_dict_valid)

    # report result
    time_end = time.datetime.now()

    print('---------------------------------------------------------')
    print("Epoch: {0}, validation loss: {1:.2f}, validation accuracy: {2:.01%}, time cost: {3:.2f} ms".
          format(epoch + 1, loss_valid, acc_valid, (time_end - time_start).total_seconds() * 1000))
    print('---------------------------------------------------------')

# ___Step 9___:Test the network after training

# Accuracy
x_test, y_test = load_data(mode='test')
feed_dict_test = {x: x_test, y: y_test}

time_start = time.datetime.now()

loss_test, acc_test = sess.run([loss, accuracy], feed_dict=feed_dict_test)

time_end = time.datetime.now()

print('---------------------------------------------------------')
print("Test loss: {0:.2f}, test accuracy: {1:.01%}, time cost: {2:.2f} ms".
    format(loss_test, acc_test, (time_end - time_start).total_seconds() * 1000))
print('---------------------------------------------------------')

# ___Step 10___: plot result

def plot_images(images, cls_true, cls_pred=None, title=None):
    """
    Create figure with 3x3 sub-plots.
    :param images: array of images to be plotted, (9, img_h*img_w)
    :param cls_true: corresponding true labels (9,)
    :param cls_pred: corresponding true labels (9,)
    """
    fig, axes = plt.subplots(3, 3, figsize=(9, 9))
    fig.subplots_adjust(hspace=0.3, wspace=0.3)

    for i, ax in enumerate(axes.flat):
        # Plot image.
        ax.imshow(np.squeeze(images[i]), cmap='binary')

        # Show true and predicted classes.
        if cls_pred is None:
            ax_title = "True: {0}".format(cls_true[i])
        else:
            ax_title = "True: {0}, Pred: {1}".format(cls_true[i], cls_pred[i])

        ax.set_title(ax_title)

        # Remove ticks from the plot.
        ax.set_xticks([])
        ax.set_yticks([])

    if title:
        plt.suptitle(title, size=20)

    plt.show(block=False)

def plot_example_errors(images, cls_true, cls_pred, title=None):
    """
    Function for plotting examples of images that have been mis-classified
    :param images: array of all images, (#imgs, img_h*img_w)
    :param cls_true: corresponding true labels, (#imgs,)
    :param cls_pred: corresponding predicted labels, (#imgs,)
    """
    # Negate the boolean array.
    incorrect = np.logical_not(np.equal(cls_pred, cls_true))

    # Get the images from the test-set that have been
    # incorrectly classified.
    incorrect_images = images[incorrect]

    # Get the true and predicted classes for those images.
    cls_pred = cls_pred[incorrect]
    cls_true = cls_true[incorrect]

    # Plot the first 9 images.
    plot_images(images=incorrect_images[0:9], cls_true=cls_true[0:9], cls_pred=cls_pred[0:9], title=title)


# Plot some of the correct and misclassified examples
cls_pred = sess.run(cls_prediction, feed_dict=feed_dict_test)

cls_true = np.argmax(y_test, axis=1)

plot_images(x_test, cls_true, cls_pred, title='Correct Examples')
plot_example_errors(x_test, cls_true, cls_pred, title='Misclassified Examples')

plt.show()

# ___Step 11___: finish all
sess.close()