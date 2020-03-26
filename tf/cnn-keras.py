'''
Trains a simple convnet on the MNIST dataset.
'''

# imports
import numpy as np
import matplotlib.pyplot as plt

from keras.models import Sequential
from keras.layers.core import Dense, Dropout, Activation
from keras.utils import np_utils

# ___Step 1___: Load MNIST data
from keras.datasets import mnist

# the data, shuffled and split between tran and test sets
(x_train, y_train), (x_test, y_test) = mnist.load_data()

print("Original shape of MNIST:")
print('- x_train:\t{}'.format(x_train.shape))
print('- y_train:\t{}'.format(y_train.shape))
print('- x_test:\t{}'.format(x_test.shape))
print('- y_test:\t{}'.format(y_test.shape))

# let's look at some examples of the training data
plt.rcParams['figure.figsize'] = (7,7) # Make the figures a bit bigger

for i in range(9):
    plt.subplot(3,3,i+1)
    plt.imshow(x_train[i], cmap='gray', interpolation='none')
    plt.title("Class {}".format(y_train[i]))
plt.show()

# ___Step 2___: Hyper-parameters
nb_classes = 10

x_train = x_train.reshape(60000, 784)
x_train = x_train.astype('float32')
x_train /= 255
y_train = np_utils.to_categorical(y_train, nb_classes)

x_test = x_test.reshape(10000, 784)
x_test = x_test.astype('float32')
x_test /= 255
y_test = np_utils.to_categorical(y_test, nb_classes)

print("Training matrix shape of MNIST:")
print('- x_train:\t{}'.format(x_train.shape))
print('- y_train:\t{}'.format(y_train.shape))
print('- x_test:\t{}'.format(x_test.shape))
print('- y_test:\t{}'.format(y_test.shape))

# ___Step 4___: Create the newwork graph

# Build the neural-network with a simple 3 layer fully connected network
model = Sequential()

model.add(Dense(512, input_shape=(784,)))
# An "activation" is just a non-linear function applied to the output
# of the layer above. Here, with a "rectified linear unit", we clamp all values below 0 to 0.
model.add(Activation('relu'))
# Dropout helps protect the model from memorizing or "overfitting" the training data                           
model.add(Dropout(0.2))   
model.add(Dense(512))
model.add(Activation('relu'))
model.add(Dropout(0.2))
model.add(Dense(10))
# This special "softmax" activation among other things, ensures the output is a valid 
# probaility distribution, that is that its values are all non-negative and sum to 1.
model.add(Activation('softmax')) 

# ___ Step 5___: Compile the model

# When compiing a model, Keras asks you to specify your loss function and your optimizer
model.compile(loss='categorical_crossentropy', optimizer='adam')

# ___Step 6___: Training

model.fit(x_train, y_train, batch_size=128, epochs=4, verbose=1, validation_data=(x_test, y_test))

# ___Step 7___: Test the network after training
score = model.evaluate(x_test, y_test, verbose=0)
print('Test score:', score)

# ___Step 8___: Inspecting the output

# The predict_classes function outputs the highest probability class
# according to the trained classifier for each input example.
predicted_classes = model.predict_classes(x_test)

# Check which items we got right / wrong
correct_indices = np.nonzero(predicted_classes == y_test)[0]
incorrect_indices = np.nonzero(predicted_classes != y_test)[0]

plt.figure()
for i, correct in enumerate(correct_indices[:9]):
    plt.subplot(3,3,i+1)
    plt.imshow(x_test[correct].reshape(28,28), cmap='gray', interpolation='none')
    plt.title("Predicted {}, Class {}".format(predicted_classes[correct], y_test[correct]))
    
plt.figure()
for i, incorrect in enumerate(incorrect_indices[:9]):
    plt.subplot(3,3,i+1)
    plt.imshow(x_test[incorrect].reshape(28,28), cmap='gray', interpolation='none')
    plt.title("Predicted {}, Class {}".format(predicted_classes[incorrect], y_test[incorrect]))
plt.show()
