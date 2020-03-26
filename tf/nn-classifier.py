'''
 we'll create a simple neural network classifier in TensorFlow. The key advantage of 
 this model over the Linear Classifier trained in the previous tutorial is that it 
 can separate data which is NOT linearly separable.
'''

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
        return x_train, y_train, x_valid, y_valid
    elif mode == 'test':
        x_test, y_test = mnist.test.images, mnist.test.labels
    return x_test, y_test

def randomize(x, y):
    # Randomizes the order of data samples and their corresponding labels
    permutation = np.random.permutation(y.shape[0])
    shuffled_x = x[permutation, :]
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

# Based on the dimesnion of the arrays, for each image, we have 10 values as its label. Why? 
# This technique is called One-Hot Encoding. This means the labels have been converted from 
# a single number to a vector whose length equals the number of possible classes. 
print("One-Hot encoded labels for the first 5 images:")
print(y_valid[:5, :])

# ___Step 2___: Hyper-parameters

# batch size: the number of training examples in one forward/backward pass. 
# iteration: one forward pass and one backward pass of one batch of images the training examples.
# epoch: one forward pass and one backward pass of all the training examples. epoch(1)=55000/batch size(100)=550(iteration)
epochs = 10             # Total number of training epochs
batch_size = 100        # Training batch size
display_freq = 100      # Frequency of displaying the training results
learning_rate = 0.001   # The optimization initial learning rate
h1 = 200                # number of nodes in the 1st hidden layer
n_classes = 10          # Number of classes, one class per digit

# data dimenstion
img_h = img_w = 28             # MNIST images are 28x28
img_size_flat = img_h * img_w  # 28x28=784, the total number of pixels

# path to the folder that we want to save the logs for Tensorboard
logs_path = "./graphs"  

# ___Step 3___: Variables

def weight_variable(name, shape):
    """
    Create a weight variable with appropriate initialization
    :param name: weight name
    :param shape: weight shape
    :return: initialized weight variable
    """
    initer = tf.truncated_normal_initializer(stddev=0.01)
    return tf.get_variable('W_' + name, dtype=tf.float32, shape=shape, initializer=initer)

def bias_variable(name, shape):
    """
    Create a bias variable with appropriate initialization
    :param name: bias variable name
    :param shape: bias variable shape
    :return: initialized bias variable
    """
    initial = tf.constant(0., shape=shape, dtype=tf.float32)
    return tf.get_variable('b_' + name, dtype=tf.float32, initializer=initial)

def fc_layer(x, num_units, name, use_relu=True):
    """
    Create a fully-connected layer
    :param x: input from previous layer
    :param num_units: number of hidden units in the fully-connected layer
    :param name: layer name
    :param use_relu: boolean to add ReLU non-linearity (or not)
    :return: The output array
    """

    # define a variable scope with the name fc1 representing all the nodes in the first fully connected layer. 
    with tf.variable_scope(name):
        in_dim = x.get_shape()[1]

        W = weight_variable(name, shape=[in_dim, num_units])
        b = bias_variable(name, [num_units])

        # To visualize the parameters, we will use tf.summary class to write the summaries of parameters. 
        tf.summary.histogram('W', W)
        tf.summary.histogram('b', b)

        layer = tf.matmul(x, W)
        layer += b

        if use_relu:
            layer = tf.nn.relu(layer)
        return layer

# ___Step 4___: Create the newwork graph

# Placeholders for inputs (x) and outputs(y). Where None means that the tensor may hold 
# an arbitrary number of images with each image being a vector of length img_size_flat.
with tf.variable_scope('Input'):
    x = tf.placeholder(tf.float32, shape=[None, img_size_flat], name='X')
    tf.summary.image('input_image', tf.reshape(x, (-1, img_w, img_h, 1)), max_outputs=5)    # to visualize image

    y = tf.placeholder(tf.float32, shape=[None, n_classes], name='Y')

# Since we have a neural network, we can stack multiple fully-connected layers using 
# fc_layer method. 

# Create a fully-connected layer with h1 nodes as hidden layer
fc1 = fc_layer(x, h1, 'Hidden_layer', use_relu=True)

# ___Step 5___: Create the model structure

# Create a fully-connected layer with n_classes nodes as output layer
#  Note that we will not use any activation function (use_relu=False) in the last layer. 
# The reason is that we can use tf.nn.softmax_cross_entropy_with_logits to calculate the loss.
output_logits = fc_layer(fc1, n_classes, 'Out_layer', use_relu=False)

# ___Step 6___: Define the loss function, optimizer, accuracy, and predicted class

# Define the loss function, optimizer, and accuracy
with tf.variable_scope('Train'):
    with tf.variable_scope('Loss'):
        loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(labels=y, logits=output_logits), name='loss')
        tf.summary.scalar('loss', loss) # to visualize scalar values
    with tf.variable_scope('Optimizer'):
        optimizer = tf.train.AdamOptimizer(learning_rate=learning_rate, name='Adam-op').minimize(loss)
    with tf.variable_scope('Accuracy'):
        correct_prediction = tf.equal(tf.argmax(output_logits, 1), tf.argmax(y, 1), name='correct_pred')
        accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32), name='accuracy')
        tf.summary.scalar('accuracy', accuracy) # to visualize scalar values
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

        # sess.run(optimizer, feed_dict=feed_dict_batch)
        _, summary_tr = sess.run([optimizer, merged], feed_dict=feed_dict_batch)
        train_writer.add_summary(summary_tr, global_step)   

        if iteration % display_freq == 0:
            # Calculate and display the batch loss and accuracy
            loss_batch, acc_batch = sess.run([loss, accuracy], feed_dict=feed_dict_batch)

            print("iter {0:3d}:\t Loss={1:.2f},\tTraining Accuracy={2:.01%}".
                  format(iteration, loss_batch, acc_batch))

    # Run validation after every epoch
    feed_dict_valid = {x: x_valid[:1000], y: y_valid[:1000]}
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
feed_dict_test = {x: x_test[:1000], y: y_test[:1000]}

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
        ax.imshow(images[i].reshape(28, 28), cmap='binary')

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
    plot_images(images=incorrect_images[0:9], cls_true=cls_true[0:9], cls_pred=cls_pred[0:9],title=title)


# Plot some of the correct and misclassified examples
cls_pred = sess.run(cls_prediction, feed_dict=feed_dict_test)

cls_true = np.argmax(y_test[:1000], axis=1)

plot_images(x_test, cls_true, cls_pred, title='Correct Examples')
plot_example_errors(x_test[:1000], cls_true, cls_pred, title='Misclassified Examples')

plt.show()

"""
Create embedding visualization
"""
import os
from tensorflow.contrib.tensorboard.plugins import projector

# Load the test set (re-load)
x_test, y_test = load_data(mode='test')

# Initialize the embedding variable with the shape of our desired tensor
tensor_shape = (x_test.shape[0] , fc1.get_shape()[1].value) # [test_set , h1] = [10000 , 200]
embedding_var = tf.Variable(tf.zeros(tensor_shape), name='fc1_embedding')

# Create a config object to write the configuration parameters
config = projector.ProjectorConfig()

# Add embedding variable
embedding = config.embeddings.add()
embedding.tensor_name = embedding_var.name

# Link this tensor to its metadata file (e.g. labels) -> we will create this file later
embedding.metadata_path = 'metadata.tsv'

# Specify where you find the sprite. -> we will create this image later
embedding.sprite.image_path = 'sprite_images.png'
embedding.sprite.single_image_dim.extend([img_w, img_h])

# Write a projector_config.pbtxt in the logs_path.
# TensorBoard will read this file during startup.
projector.visualize_embeddings(train_writer, config)

# assign the tensor that we want to visualize to the embedding variable
embedding_assign = embedding_var.assign(fc1) 

# Run session to evaluate the tensor
x_test_fc1 = sess.run(embedding_assign, feed_dict={x: x_test})

# Save the tensor in model.ckpt file
saver = tf.train.Saver()
saver.save(sess, os.path.join(logs_path, "model.ckpt"), global_step)

def write_sprite_image(filename, images):
    """
        Create a sprite image consisting of sample images
        :param filename: name of the file to save on disk
        :param shape: tensor of flattened images
    """

    # Invert grayscale image
    images = 1 - images

    # Calculate number of plot
    n_plots = int(np.ceil(np.sqrt(images.shape[0])))

    # Make the background of sprite image
    sprite_image = np.ones((img_h * n_plots, img_w * n_plots))

    for i in range(n_plots):
        for j in range(n_plots):
            img_idx = i * n_plots + j
            if img_idx < images.shape[0]:
                img = images[img_idx]
                sprite_image[i * img_h:(i + 1) * img_h,
                j * img_w:(j + 1) * img_w] = img

    plt.imsave(filename, sprite_image, cmap='gray')
    print('Sprite image saved in {}'.format(filename))

def write_metadata(filename, labels):
    """
            Create a metadata file image consisting of sample indices and labels
            :param filename: name of the file to save on disk
            :param shape: tensor of labels
    """
    with open(filename, 'w') as f:
        f.write("Index\tLabel\n")
        for index, label in enumerate(labels):
            f.write("{}\t{}\n".format(index, label))

    print('Metadata file saved in {}'.format(filename))

# Reshape images from vector to matrix
x_test_images = np.reshape(np.array(x_test), (-1, img_w, img_h))
# Reshape labels from one-hot-encode to index
x_test_labels = np.argmax(y_test, axis=1)

write_sprite_image(os.path.join(logs_path, 'sprite_images.png'), x_test_images)
write_metadata(os.path.join(logs_path, 'metadata.tsv'), x_test_labels)

# ___Step 11___: finish all
sess.close()
