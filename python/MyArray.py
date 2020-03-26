import copy  # needed by MyArray - details covered late in CS 3B/3M


# beginning of class MyArray definition -------------------------
class MyArray:
    # class ("static") members and intended constants
    MAX_SIZE = 100000
    DEFAULT_SIZE = 10
    ARRAY_INDEX_ERROR = None
    ORIG_DEFAULT_ITEM = ""
    default_item = ORIG_DEFAULT_ITEM

    # initializer ("constructor") method -------------------
    def __init__(self, size=DEFAULT_SIZE, default_item=None):

        # instance attributes
        if not self.set_size(size):
            self.size = MyArray.DEFAULT_SIZE

        if default_item is not None:
            self.default_item = default_item

        # initialize an array of size elements for our array, all to default_item
        # force array to be empty by setting self.tos to 0
        # (all done in clear())
        self.clear()

    # mutators -------------------------------
    def set_size(self, size):
        if not self.valid_size(size):
            return False

        # else
        self.size = size
        # re-initialize an array (list) of size default items
        self.clear()
        return True

    def set(self, index, item_to_set):
        if not self.valid_index(index):
            raise IndexError
        elif not isinstance(item_to_set, type(self.default_item)):
            raise TypeError
        self.data[index] = item_to_set
        return True

    def clear(self):
        """  reallocate list of size default_items """
        # deepcopy() for mutable defaults - details in cs 3B/3M
        self.data = [copy.deepcopy(self.default_item) \
                     for _ in range(self.size)]

    # accessors -------------------------------
    def get_size(self):
        return self.size

    def get(self, index):
        if not self.valid_index(index):
            raise IndexError
        # else
        return self.data[index]

    # instance helpers ----------------------------
    def valid_index(self, test_index):
        if not (0 <= test_index < self.size):
            return False
        else:
            return True

    # static/class methods ------------------------
    @classmethod
    def valid_size(cls, test_size):
        if not (0 <= test_size <= cls.MAX_SIZE):
            return False
        else:
            return True

    @classmethod
    def set_default_item(cls, new_default):
        """ this will change the default of newly instantiated arrays """
        cls.default_item = new_default


# client --------------------------------------------
# instantiate two empty arrays, one of 50 ints, another of 15 strings
arr_1 = MyArray(50, -1)
arr_2 = MyArray(15, "undefined")
# and one more with bad argument
arr_3 = MyArray(-100)

# how about a class of lists?
arr_4 = MyArray(4, ["top"])

# confirm the array capacities
print("------ Array Sizes -------\n "
      "arr_1: {}   arr_2: {}   arr_3: {}   arr_4: {}\n".
      format(arr_1.get_size(), arr_2.get_size(), arr_3.get_size(),
             arr_4.get_size()))

# test the array -----
print("arr_1[3] =", arr_1.get(3))
print()

arr_1.set(3, 44)
arr_1.set(12, 123)
arr_1.set(9, 99)
arr_2.set(5, "bank")
arr_2.set(6, "-34")
arr_1.set(2, 10)
arr_1.set(0, 1000)

arr_4.get(0)[0] = "bottom"

# try to put a square peg into a round hole
try:
    arr_1.set(2, "should not be allowed into an int array")
except TypeError:
    print("Successfully rejected due to type incompatibility")

try:
    arr_2.set(2, 444)
except TypeError:
    print("Successfully rejected due to type incompatibility")

try:
    arr_1.set(2, 44.4)
except TypeError:
    print("Successfully rejected due to type incompatibility")

# out of range error works?
try:
    arr_1.get(2000)
except IndexError:
    print("Successfully rejected due to index bounds")

# and here test return type if good arguments
if arr_2.set(2, "should be okay"):
    print("Successfully accepted a good type and index")

arr_2.set(3, "a penny earned")
arr_2.set(4, "item #9277")
arr_2.set(1, "where am i?")
arr_2.set(5, "4")

print("\n--------- First Array 0-9 ---------\n")
for k in range(9):
    print(str(k) + ": " + str(arr_1.get(k)))

print("\n--------- Second Array 0-9 ---------\n")
for k in range(9):
    print(str(k) + ": " + str(arr_2.get(k)))

print("\n--------- Fourth Array whole thing ---------\n")
for k in range(arr_4.get_size()):
    print(str(k) + ": " + str(arr_4.get(k)))