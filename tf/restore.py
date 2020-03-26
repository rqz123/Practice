import os

if os.name != "nt":
    print(os.name)

import tensorflow as tf

# create variables a and b
a = tf.get_variable("A", initializer=tf.constant(3, shape=[2]))
b = tf.get_variable("B", initializer=tf.constant(5, shape=[3]))

# initialize all of the variables
init_op = tf.global_variables_initializer() # create the graph

# create saver object
saver = tf.train.Saver()

# delete the current graph
# tf.reset_default_graph()

# run the session
try:
    with tf.Session() as sess:
        # Notice that this time we did not initialize the variables in our session. 
        # Instead, we restored them from the disk. 
        # sess.run(tf.global_variables_initializer())

        # restore the saved vairable
        saver.restore(sess, './saved/vars')

        # print the loaded variable
        a_out, b_out = sess.run([a, b])

        print('a = ', a_out)
        print('b = ', b_out)
except Exception as e:
    print(str(e))

print('Model restored')
