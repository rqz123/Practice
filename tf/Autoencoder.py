"""
Autoencoder help us dealing with noisy data. Since the latent space only keeps the important information, 
the noise will not be preserved in the space and we can reconstruct the cleaned data. 
"""

# imports
import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt

# ___Step 1___: Load MNIST data
from tensorflow.examples.tutorials.mnist import input_data

mnist = input_data.read_data_sets("MNIST_data/", one_hot=True)

# helper function for the MNIST data loading
# modes:    train (which loads the training and validation images and their corresponding labels)
#           test (which loads the test images and their corresponding labels)
def load_data(mode='train'):
    """
    Function to (download and) load the MNIST data
    :param mode: train or test
    :return: images and the corresponding labels
    """
    
    if mode == 'train':
        x_train, y_train, x_valid, y_valid = mnist.train.images, mnist.train.labels, \
                                             mnist.validation.images, mnist.validation.labels
        return x_train, y_train, x_valid, y_valid
    elif mode == 'test':
        x_test, y_test = mnist.test.images, mnist.test.labels
    return x_test, y_test

def get_next_batch(batch_size):
    return mnist.train.next_batch(batch_size)

# load data
x_train, y_train, x_valid, y_valid = load_data(mode='train')
x_test, y_test = load_data(mode='test')

print("Size of MNIST:")
print("- Train labels:\t\t{}".format(len(y_train)))
print("- Test labels:\t\t{}".format(len(y_test)))
print("- validation labels:\t{}".format(len(y_valid)))

# ___Step 2___: Hyper-parameters

learning_rate = 0.001   # The optimization learning rate
epochs = 10             # Total number of training epochs
batch_size = 100        # Training batch size
display_freq = 100      # Frequency of displaying the training results
h1 = 100                # number of units in the hidden layer
noise_level = 0.6       # level of the noise in noisy data

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
        return layer, W

# ___Step 4___: Create the newwork graph

# Placeholders for inputs (x), outputs(y)
with tf.variable_scope('Input'):
    x_original = tf.placeholder(tf.float32, shape=[None, img_size_flat], name='X_original')
    x_noisy = tf.placeholder(tf.float32, shape=[None, img_size_flat], name='X_noisy')

# ___Step 5___: Create the model structure

fc1, W1 = fc_layer(x_noisy, h1, 'Hidden_layer', use_relu=True)
out, W2 = fc_layer(fc1, img_size_flat, 'Output_layer', use_relu=False)

# calculate the activation 
h_active = W1 / tf.sqrt(tf.reduce_sum(tf.square(W1), axis=0)) # [784, 100]

# ___Step 6___: Define the loss function, optimizer, accuracy, and predicted class
with tf.variable_scope('Train'):
    with tf.variable_scope('Loss'):
        loss = tf.reduce_mean(tf.losses.mean_squared_error(x_original, out), name='loss')
        tf.summary.scalar('loss', loss)
    with tf.variable_scope('Optimizer'):
        optimizer = tf.train.AdamOptimizer(learning_rate=learning_rate, name='Adam-op').minimize(loss)

# ___Step 7___: Initialize all variables

# Creating the op for initializing all variables
init = tf.global_variables_initializer()

# Add 5 images from original, noisy and reconstructed samples to summaries
tf.summary.image('original', tf.reshape(x_original, (-1, img_w, img_h, 1)), max_outputs=5)
tf.summary.image('noisy', tf.reshape(x_noisy, (-1, img_w, img_h, 1)), max_outputs=5)
tf.summary.image('reconstructed', tf.reshape(out, (-1, img_w, img_h, 1)), max_outputs=5)

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

    time_start = time.datetime.now()

    for iteration in range(num_tr_iter):
        # get batch data
        batch_x, _ = get_next_batch(batch_size)
        batch_x_noisy = batch_x + noise_level * np.random.normal(loc=0.0, scale=1.0, size=batch_x.shape)

        global_step += 1    # for visualize

        # Run optimization op (backprop)
        feed_dict_batch = {x_original: batch_x, x_noisy: batch_x_noisy}

        _, summary_tr = sess.run([optimizer, merged], feed_dict=feed_dict_batch)
        train_writer.add_summary(summary_tr, global_step)

        if iteration % display_freq == 0:
            # Calculate and display the batch loss and accuracy
            loss_batch = sess.run(loss, feed_dict=feed_dict_batch)

            print("iter {0:3d}:\t Reconstruction loss={1:.3f}".
                  format(iteration, loss_batch))

    # Run validation after every epoch
    x_valid_original  = x_valid
    x_valid_noisy = x_valid_original + noise_level * np.random.normal(loc=0.0, scale=1.0, size=x_valid_original.shape)

    feed_dict_valid = {x_original: x_valid_original, x_noisy: x_valid_noisy}
    loss_valid = sess.run(loss, feed_dict=feed_dict_valid)

    # report result
    time_end = time.datetime.now()

    print('---------------------------------------------------------')
    print("Epoch: {0}, validation loss: {1:.3f}, time cost: {2:.2f} ms".
          format(epoch + 1, loss_valid, (time_end - time_start).total_seconds() * 1000))
    print('---------------------------------------------------------')

# ___Step 9___:Test the network after training

#  visualize the activation of the hidden units
def plot_max_active(x):
    """
    Plots the images that are maximally activating the hidden units
    :param x: numpy array of size [input_dim, num_hidden_units]
    """
    fig, axes = plt.subplots(nrows=10, ncols=10, figsize=(17, 17))
    fig.subplots_adjust(hspace=.1, wspace=0)
    img_h = img_w = np.sqrt(x.shape[0]).astype(int)
    for i, ax in enumerate(axes.flat):
        # Plot image.
        ax.imshow(x[:, i].reshape((img_h, img_w)), cmap='gray')
        ax.set_xticks([])
        ax.set_yticks([])
        ax.set_yticklabels([])
        ax.set_xticklabels([])
    plt.show()

plot_max_active(sess.run(h_active))

# Make a noisy image
x_test_5 = x_test[:5]   # 5 samples
x_test_noisy = x_test_5 + noise_level * np.random.normal(loc=0.0, scale=1.0, size=x_test_5.shape)

time_start = time.datetime.now()

# Reconstruct a clean image from noisy image
x_reconstruct = sess.run(out, feed_dict={x_noisy: x_test_noisy})

# Calculate the loss between reconstructed image and original image
loss_test = sess.run(loss, feed_dict={x_original: x_test_5, x_noisy: x_test_noisy})

time_end = time.datetime.now()

print('---------------------------------------------------------')
print("Test loss of original image compared to reconstructed image : {0:.3f}, , time cost: {1:.2f} ms".
        format(loss_test, (time_end - time_start).total_seconds() * 1000))
print('---------------------------------------------------------')

# ___Step 10___: plot result

def plot_images(original_images, noisy_images, reconstructed_images):
    """
    Create figure of original and reconstructed image.
    :param original_image: original images to be plotted, (?, img_h*img_w)
    :param noisy_image: original images to be plotted, (?, img_h*img_w)
    :param reconstructed_image: reconstructed images to be plotted, (?, img_h*img_w)
    """
    num_images = original_images.shape[0]
    fig, axes = plt.subplots(num_images, 3, figsize=(9, 9))
    fig.subplots_adjust(hspace=.1, wspace=0)

    img_h = img_w = np.sqrt(original_images.shape[-1]).astype(int)

    for i, ax in enumerate(axes):
        # Plot image.
        ax[0].imshow(original_images[i].reshape((img_h, img_w)), cmap='gray')
        ax[1].imshow(noisy_images[i].reshape((img_h, img_w)), cmap='gray')
        ax[2].imshow(reconstructed_images[i].reshape((img_h, img_w)), cmap='gray')

        # Remove ticks from the plot.
        for sub_ax in ax:
            sub_ax.set_xticks([])
            sub_ax.set_yticks([])

    for ax, col in zip(axes[0], ["Original Image", "Noisy Image", "Reconstructed Image"]):
        ax.set_title(col)

    fig.tight_layout()
    plt.show()

# Plot original image, noisy image and reconstructed image
plot_images(x_test_5, x_test_noisy, x_reconstruct)

# ___Step 11___: finish all
sess.close()