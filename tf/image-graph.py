import tensorflow as tf

# To clear the defined variables and operations of the previous cell
tf.reset_default_graph()   

# create the variables
'''
The tensor that you feed to tf.summary.image must be a 4-D tensor of shape [batch_size, height, width, channels] 
where batch_size is the number of images in the batch, height and width determines the size of the image and 
channel is: 1: for Grayscale images. 3: for RGB (i.e. color) images. 4: for RGBA images (where A stands for alpha; see RGBA). 

Let's define two variables: 
Of size 30x10 as 3 grayscale images of size 10x10 ~~ 3 x 10x10 x 1
Of size 50x30 as 5 color images of size 10x10 ~~ 5 x 10x10 x 3
'''
w_gs = tf.get_variable('W_Grayscale', shape=[30, 10], initializer=tf.truncated_normal_initializer(mean=0, stddev=1))
w_c = tf.get_variable('W_Color', shape=[50, 30], initializer=tf.truncated_normal_initializer(mean=0, stddev=1))

# ___step 0:___ reshape it to 4D-tensors
w_gs_reshaped = tf.reshape(w_gs, (3, 10, 10, 1))
w_c_reshaped = tf.reshape(w_c, (5, 10, 10, 3))

# ____step 1:____ create the summaries
'''
where name is the name for the generated node (i.e. operation), and max_outputs is the maximum number of elements 
from tensor to generate images for. 
'''
gs_summary = tf.summary.image('Grayscale', w_gs_reshaped, max_outputs=1)
c_summary = tf.summary.image('Color', w_c_reshaped, max_outputs=2)

# ____step 2:____ merge all summaries
merged = tf.summary.merge_all()

# create the op for initializing all variables
init = tf.global_variables_initializer()

# launch the graph in a session
with tf.Session() as sess:
    # ____step 3:____ creating the writer inside the session
    writer = tf.summary.FileWriter('./graphs', sess.graph)

    # initialize all variables
    sess.run(init)

    # ____step 4:____ evaluate the merged op to get the summaries
    summary = sess.run(merged)
    # ____step 5:____ add summary to the writer (i.e. to the event file) to write on the disc
    writer.add_summary(summary)

print('Done writing the summaries')
