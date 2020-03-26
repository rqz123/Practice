import tensorflow as tf

# create variables a and b
a = tf.get_variable("A", initializer=tf.constant(3, shape=[2]))
b = tf.get_variable("B", initializer=tf.constant(5, shape=[3]))

# initialize all of the variables
init_op = tf.global_variables_initializer()

# create saver object
saver = tf.train.Saver()

# list of all variables in _var_list
for i, var in enumerate(saver._var_list):
    print('Var {}: {}'.format(i, var))

# run the session
try:
    with tf.Session() as sess:
        # initialize all of the variables in the session
        sess.run(init_op)

        # run the session to get the value of the variable
        a_out, b_out = sess.run([a, b])

        print('a = ', a_out)
        print('b = ', b_out)

        # save the variable in the disk
        saved_path = saver.save(sess, './saved/vars')
except Exception as e:
    print(str(e))

print('Model saved in {}'.format(saved_path))

# check file list
import os

for file in os.listdir('./saved'):
    if 'vars' in file:
        print(file)
