'''
h = ReLU(Wx+b)

Let's start with the input, X. This can be an input of any type, such as images, signals, etc. 
The general approach is to feed all inputs to the network and train the trainable parameters (here, W and b) 
by backpropagating the error signal. Ideally, you need to feed all inputs together, compute the error, 
and update the parameters. This process is called "Gradient Descent". 

* Side Note: In real-world problems, we have thousands and millions of inputs which makes gradient descent computationally expensive. 
That's why we split the input set into several shorter pieces (called mini-batch) of size B (called mini-batch size) inputs, 
and feed them one by one. This is called "Stochastic Gradient Descent". The process of feeding each mini-batch of size B to the network, 
back-propagating errors, and updating the parameters (weights and biases) is called an iteration. 
'''
import tensorflow as tf
import numpy as np

# create the input placeholder

# Defining the placeholder shape as [None, 784] means that we can feed any number of images of size 784 (not B images necessarily).
X = tf.placeholder(tf.float32, shape=[None, 784], name="X")
print(X)

# create network parameters
weight_initer = tf.truncated_normal_initializer(mean=0.0, stddev=0.01)
W = tf.get_variable(name="W", dtype=tf.float32, shape=[784, 200], initializer=weight_initer)
print(W)

bias_initer = tf.constant(0., shape=[200], dtype=tf.float32)
b = tf.get_variable(name="b", dtype=tf.float32, initializer=bias_initer)
print(b)

# create MatMul node
x_w = tf.matmul(X, W, name="MatMul")
# create Add node
x_w_b = tf.add(x_w, b, name="Add")
# create ReLU node
h = tf.nn.relu(x_w_b, name="ReLU") 

# Add an Op to initialize variables
init_op = tf.global_variables_initializer()

# launch the graph in a session
with tf.Session() as sess:
    # initialize variables
    sess.run(init_op)
    # create the dictionary:
    d = {X: np.random.rand(100, 784)}
    # feed it to placeholder a via the dict 
    print(sess.run(h, feed_dict=d))
    writer = tf.summary.FileWriter('./graphs', sess.graph)
