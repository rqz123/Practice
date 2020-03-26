'''
we'll create a simple linear classifier in TensorFlow. We will implement this model for 
classifying images of hand-written digits from the so-called MNIST data-set. 
'''

# imports
import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt

# helper function for the MNIST data loading
# modes:    train (which loads the training and validation images and their corresponding labels)
#           test (which loads the test images and their corresponding labels)
def load_data(mode='train'):
    """
    Function to (download and) load the MNIST data
    :param mode: train or test
    :return: images and the corresponding labels
    """
    from tensorflow.examples.tutorials.mnist import input_data
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

# ___Step 1___: Load MNIST data
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

# ___Step 4___: Create the graph for the linear model

# data dimenstion
img_h = img_w = 28             # MNIST images are 28x28
img_size_flat = img_h * img_w  # 28x28=784, the total number of pixels
n_classes = 10                 # Number of classes, one class per digit

# Placeholders for inputs (x) and outputs(y). Where None means that the tensor may hold 
# an arbitrary number of images with each image being a vector of length img_size_flat.
x = tf.placeholder(tf.float32, shape=[None, img_size_flat], name='X')
y = tf.placeholder(tf.float32, shape=[None, n_classes], name='Y')

# ___Step 5___: Create the model structure

# Create weight matrix initialized randomely from N~(0, 0.01)
W = weight_variable(shape=[img_size_flat, n_classes])

# Create bias vector initialized as zero
b = bias_variable(shape=[n_classes])

output_logits = tf.matmul(x, W) + b

# ___Step 6___: Define the loss function, optimizer, accuracy, and predicted class

# Define the loss function, optimizer, and accuracy
loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(labels=y, logits=output_logits), name='loss')
optimizer = tf.train.AdamOptimizer(learning_rate=learning_rate, name='Adam-op').minimize(loss)

correct_prediction = tf.equal(tf.argmax(output_logits, 1), tf.argmax(y, 1), name='correct_pred')
accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32), name='accuracy')

# Model predictions
cls_prediction = tf.argmax(output_logits, axis=1, name='predictions')

# ___Step 7___: Initialize all variables

# Creating the op for initializing all variables
init = tf.global_variables_initializer()

# ___Step 7.1___: create saver object
saver = tf.train.Saver()

# list of all variables in _var_list
for i, var in enumerate(saver._var_list):
    print('Var {}: {}'.format(i, var))

# ___Step 8___: Training

# Create an interactive session (to keep the session in the other cells)
sess = tf.InteractiveSession()

# Initialize all variables
sess.run(init)

# Number of training iterations in each epoch
num_tr_iter = int(len(y_train) / batch_size)

# Loop for epoch
import datetime as time

for epoch in range(epochs):
    print('Training epoch: {}'.format(epoch + 1))

    # Randomly shuffle the training data at the beginning of each epoch 
    x_train, y_train = randomize(x_train, y_train)

    time_start = time.datetime.now()

    # Loop for iteration
    for iteration in range(num_tr_iter):
        # get batch data
        start = iteration * batch_size
        end = (iteration + 1) * batch_size
        x_batch, y_batch = get_next_batch(x_train, y_train, start, end)

        # Run optimization op (backprop)
        feed_dict_batch = {x: x_batch, y: y_batch}
        sess.run(optimizer, feed_dict=feed_dict_batch)

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

# ___Step 8.9___: save the variable in the disk
saved_path = saver.save(sess, './saved/vars')
print('Model saved in {}'.format(saved_path))

# delete the current graph
# tf.reset_default_graph()

# restore the saved vairable
# saver.restore(sess, './saved/vars')

# ___Step 9___: test the network after training

# Accuracy method
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

# ___Step 11___: finish all
sess.close()