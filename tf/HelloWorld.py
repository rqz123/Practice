'''
HelloWorld example using TensorFlow library.

Basically, all Tensorflow codes contain two important parts: 
Part 1: building the GRAPH, it represents the data flow of the computations 
Part 2: running a SESSION, it executes the operations in the graph 
'''

from __future__ import print_function

import tensorflow as tf

# Simple hello world using TensorFlow

# Create a Constant op
# The op is added as a node to the default graph.
#
# The value returned by the constructor represents the output
# of the Constant op.
hello = tf.constant('Hello, TensorFlow!')

# Start tf session
sess = tf.Session()

# Run the op
print(sess.run(hello))

import sys
print("Python version is %s.%s.%s" % sys.version_info[:3])
print("Tensorflow version is %s" % tf.__version__)

'''
The graph is composed of a series of nodes connected to each other by edges (from the image above). 
Each node in the graph is called op (short for operation). So we'll have one node for each operation; 
either for operations on tensors (like math operations) or generating tensors (like variables and constants). 
Each node takes zero or more tensors as inputs and produces a tensor as an output.

*Note: Tensor is a multi-dimensional array. (0-D tensor: scalar, 1-D tensor: vector, 2-D tensor: matrix, and so on) 
'''

# Creating a graph with multiple math operations 
x = 2
y = 3
add_op = tf.add(x, y, name='Add')
mul_op = tf.multiply(x, y, name='Multiply')
pow_op = tf.pow(add_op, mul_op, name='Power')
useless_op = tf.multiply(x, add_op, name='Useless')

with tf.Session() as sess:
    pow_out, useless_out = sess.run([pow_op, useless_op])
    print(pow_out, useless_out)

'''
Constant
'''
# create graph
a = tf.constant(200)
b = tf.constant(300)
m = tf.constant([[1, 2], [3, 4]], name='matrix')
c = a + b
# launch the graph in a session
with tf.Session() as sess:
    print(sess.run(c))
    print(sess.run(m))

'''
Variable
Note: Variables are usually used for weights and biases in neural networks. 
* Weights are usually initialized from a normal distribution using tf.truncated_normal_initializer(). 
* Biases are usually initialized from zeros using tf.zeros_initializer(). 
'''
# a = 2
# a = tf.constant(2, name='a')
a = tf.get_variable(name="a", initializer=2)
# b = 3
# b = tf.constant(3, name='b')
b = tf.get_variable(name="b", initializer=3)
# c = tf.add(a, b, name='Add')
c = tf.add(a, b, name='add')
# Add an Op to initialize variables
init_op = tf.global_variables_initializer()
print(c)
with tf.Session() as sess:
    # run the variable initializer
    sess.run(init_op)
    # now we can run the desired operation
    print(sess.run(c))

# create graph
weights = tf.get_variable(name="Weights", shape=[2,3], initializer=tf.truncated_normal_initializer(stddev=0.01))
biases = tf.get_variable(name="Biases", shape=[3], initializer=tf.zeros_initializer())

# add an Op to initialize global variables
init_op = tf.global_variables_initializer()

# launch the graph in a session
with tf.Session() as sess:
    # run the variable initializer
    sess.run(init_op)
    # now we can run our operations
    W, b = sess.run([weights, biases])
    print(weights)
    print('Weights = {}'.format(W))

    print(biases)
    print('Biases = {}'.format(b))

'''
Placeholder
It is simply a variable that we asign data in a future time. Placeholders are nodes whose value is fed in at execution time.
'''
# create a placeholder that takes three values (vector of size 3) and type float32
a = tf.placeholder(tf.float32, shape=[3], name="a")
# create a constant the same size and type as "a"
b = tf.constant([5, 5, 5], tf.float32, name="b")
# Add them together
c = tf.add(a, b, name="Add")
# launch the graph in a session
with tf.Session() as sess:
    # create the dictionary:
    d = {a: [1, 2, 3]}
    # feed it to placeholder a via the dict 
    print(sess.run(c, feed_dict=d))

'''
TensorBoard

python3 -m tensorboard.main --logdir=~/my/training/dir
'''
# To clear the defined variables and operations of the 
tf.reset_default_graph()

# create graph
a = tf.constant(2)
b = tf.constant(3)
c = a + b
# launch the graph in a session
with tf.Session() as sess:
    # creating the writer inside the session
    # writer = tf.summary.FileWriter('c:/Users/rzhang/OneDrive/Work/tf/graphs', sess.graph)
    writer = tf.summary.FileWriter('./graphs', sess.graph)
    # execute 
    print(sess.run(c))

'''
Self test
'''
import numpy as np
import datetime

start = datetime.datetime.now()

d1 = np.random.randint(0,9,10)
print(d1)
# print(d1[:5])   # part of array
d2 = np.random.randint(0,9,10)
print(d2)
dlt = np.logical_not(np.equal(d1, d2))
print(dlt)
d3 = d1[dlt]
print(d3)

d = np.random.rand(5,3)
print(d)

permutation = np.random.permutation(d.shape[0])

# shuffled_d = d[permutation, :]
shuffled_d = d[permutation]
print(shuffled_d)

end = datetime.datetime.now()
print("Time cost: {:.2f} ms".format((end - start).total_seconds() * 1000))

"""
Display image
"""
""" method 1
import matplotlib.pyplot as plt
import matplotlib.image as mpimg

img=mpimg.imread('./graphs/sprite_images.png')
imgplot = plt.imshow(img)

plt.show()
"""
""" method 2
from PIL import Image

image = Image.open('./graphs/sprite_images.png')
image.show()
"""
