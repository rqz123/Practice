import scipy

'''
a = scipy.array([[1, 2, 3, 4, 5],
                  [6, 7, 8, 9, 10]])
# scipy.savetxt('ndarray.txt', a, fmt='%d')
# scipy.save('ndarray.npy', a)
scipy.savetxt('ndarray.csv', a, fmt='%d', delimiter=',')
# b = scipy.loadtxt('ndarray.txt', dtype=int)
# b = scipy.load('ndarray.npy')
# b = scipy.loadtxt('ndarray.csv', dtype=int, delimiter=',')
# print(a)
# print(b)
d = {'abc' : 'sample.mp3',
     'edf' : 2048, 
     'ghi' : 0.3}
with open('dictionary.csv', 'w') as fp:
    fp.write('%s,%s\n' % ('abc',d.get('abc')))
    fp.write('%s,%d\n' % ('edf',d.get('edf')))
    fp.write('%s,%f\n' % ('ghi',d.get('ghi')))

with open('sample.dat', 'wb') as data_file:
    # Data file header
    file_header = 'Music visualized data file\n'
    data_file.write(len(file_header))
    data_file.write(bytearray(file_header, 'utf-8'))
    # Music
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

class Dog:
    """ example of class (static) member data """

    # class intended constants
    DEFAULT_LICENSE_NUM = 0
    MIN_LIC_NUM = 10000
    MAX_LIC_NUM = 99999

    # class attribute that will change over time
    population = 0

    def __init__(self, the_lic=DEFAULT_LICENSE_NUM):
        try:
            self.lic_num = the_lic
        except ValueError:
            self._lic_num = Dog.DEFAULT_LICENSE_NUM

        # wrong attempt to initialize
        # not dereferenced via anything, so this is a "local" var
        # population = 100

        # wrong attempt to initialize
        # resets the population to 0 every time an object is created
        # Dog.population = 100

        # wrong attempt to increment
        # again, this is a "local" var, so it won't even "compile"
        # because a local population var never defined
        # population += 1   

        # next two also wrong.  "self" makes these instance vars,
        #  so now each object gets a "population"
        #  disconnected from the class "population"
        # self.population = 0   # or
        self.population += 100

    @property
    def lic_num(self):
        return self._lic_num

    @lic_num.setter
    def lic_num(self, lic):
        if type(lic) != int or not (Dog.MIN_LIC_NUM <= lic <= Dog.MAX_LIC_NUM):
            raise ValueError
        self._lic_num = lic

    def show_me(self, client_var_name):
        print(f"client var name: {client_var_name}"
              f"\n    license num: {self.lic_num}")


# client --------------------------------------------

# instantiate some doggies ...
print(f"population is {Dog.population}")
watson = Dog()  # default
print(f"population is {Dog.population}")
fido = Dog(33)  # bad arg
print(f"population is {Dog.population}")
lucy = Dog(23232)  # good arg
print(f"population is {Dog.population}")

# show all the dogs
watson.show_me("watson")
fido.show_me("fido")
lucy.show_me("lucy")

print("population accessed through instances:")
print(f"   (population is {watson.population})")
print(f"   (population is {fido.population})")
print(f"   (population is {lucy.population})")


class Rectangle:
    """ example of class and static methods/functions """
   
    # class ("static") intended constants
    ORIGINAL_DEFAULT_DIMENSION = 1.
    ORIGINAL_DEFAULT_LABEL = "(no label)"
    MIN_DIM = 0.
    MAX_DIM = 1000000.
    MIN_STRING_LENGTH = 2

    # class attribute that will change over time
    default_dimension = ORIGINAL_DEFAULT_DIMENSION
    default_label = ORIGINAL_DEFAULT_LABEL 

    @classmethod
    def valid_string(cls, string_to_test):
        if (type(string_to_test) != str
             or
             len(string_to_test) < cls.MIN_STRING_LENGTH):
            return False
        # else
        return True

    @classmethod
    def set_default_dim(cls, new_dimension):
        if not cls.valid_dimension(new_dimension): 
            return False
        # else
        cls.default_dimension = new_dimension
        return True

    @classmethod
    def show_me(cls, client_var_name):
        print(f"client var name: {client_var_name}")
        cls.default_dimension += 5.0
        print(f"default_dimension = {cls.default_dimension}")
        print(f"default_label = {cls.default_label}")

    @staticmethod              
    def show_me2(self, client_var_name):
        print(f"client var name: {client_var_name}")
        self.default_dimension += 3.0
        print(f"default_dimension = {self.default_dimension}")
        print(f"default_label = {self.default_label}")

    def show_me3(client_var_name):
        print(f"client var name: {client_var_name}")
        Rectangle.default_dimension += 9.0
        print(f"default_dimension = {Rectangle.default_dimension}")
        print(f"default_label = {Rectangle.default_label}")
                  
rect = Rectangle()
rect.show_me("class method")
rect.show_me2("instance method")
Rectangle.show_me3("static method")