import tensorflow as tf

# delete the current graph
tf.reset_default_graph()

# import the graph from the file
try:
    imported_graph = tf.train.import_meta_graph('./saved/vars.meta')
except Exception as e:
    print(str(e))

# list all the tensors in the graph
for tensor in tf.get_default_graph().get_operations():
    print (tensor.name)

# run the session
try:
    with tf.Session() as sess:
        # restore the saved vairable
        imported_graph.restore(sess, './saved/vars')

        # print the loaded variable
        a_out, b_out = sess.run(['A:0','B:0'])

        print('a = ', a_out)
        print('b = ', b_out)
except Exception as e:
    print(str(e))
