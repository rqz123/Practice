import copy  # needed by MyStack - details covered late in CS 3B/3M


# beginning of class MyStack definition -------------------------
class MyStack:
    # class ("static") members and intended constants
    MAX_SIZE = 100000
    DEFAULT_SIZE = 10
    ORIG_DEFAULT_ITEM = ""
    default_item = ORIG_DEFAULT_ITEM

    # initializer ("constructor") method -------------------
    def __init__(self, capacity=DEFAULT_SIZE, default_item=None):

        # instance attributes
        try:
            self.capacity = capacity
        except ValueError:
            self._capacity = MyStack.DEFAULT_SIZE

        if default_item is not None:
            self.default_item = default_item

        # initialize an array of size=capacity for our stack, all to default_item
        # force stack to be empty by setting self.tos to 0
        # (all done in clear())
        self.clear()

    # accessors -------------------------------
    @property
    def capacity(self):
        return self._capacity

    # mutators -------------------------------
    @capacity.setter
    def capacity(self, new_capacity):
        if not self.valid_capacity(new_capacity):
            raise ValueError
        self._capacity = new_capacity
        self.clear()

    # instance methods -----------------------

    def push(self, item_to_push):
        if self.tos == self.capacity:
            raise OverflowError
        elif not isinstance(item_to_push, type(self.default_item)):
            raise TypeError

        self.stk[self.tos] = item_to_push
        self.tos += 1

    def pop(self):
        if self.tos == 0:
            raise IndexError
        self.tos -= 1
        return self.stk[self.tos]

    def clear(self):
        """  remove all items from stack """
        # deepcopy() for mutable defaults - details in cs 3B/3M
        self.stk = [copy.deepcopy(self.default_item)
                    for _ in range(self.capacity)]
        self.tos = 0



    # static/class methods ------------------------
    @classmethod
    def valid_capacity(cls, test_capacity):
        if not (0 <= test_capacity <= cls.MAX_SIZE):
            return False
        else:
            return True

    @classmethod
    def set_default_item(cls, new_default):
        """ this will change the default of newly instantiated stacks """
        cls.default_item = new_default

      

# client --------------------------------------------

# instantiate two empty stacks, one of 50 ints, another of 15 strings
s1 = MyStack(50, -1)
s2 = MyStack(15, "undefined")
# and one more with bad argument
s3 = MyStack(-100)

# confirm the stack capacities
print(f"------ Stack Sizes -------\n"
      f"  s1: {s1.capacity}   s2: {s2.capacity}   s3: {s3.capacity}\n")

# test the stack -----
try:
    print(s1.pop())
except IndexError:
    print("Tried to pop from an empty stack")
print()

s1.push(44)
s1.push(123)
s1.push(99)
s2.push("bank")
s2.push("-34")
s1.push(10)
s1.push(1000)

# try to put a square peg into a round hole
try:
    s1.push("should not be allowed into an int statck")
except TypeError:
    print("Successfully rejected due to type incompatibility")

try:
    s2.push(444)
except TypeError:
    print("Successfully rejected due to type incompatibility")

try:
    s1.push(44.4)
except TypeError:
    print("Successfully rejected due to type incompatibility")

# and here test return type if good argument
s2.push("should be okay")
print("Successfully accepted a good type")

s2.push("a penny earned")
s2.push("item #9277")
s2.push("where am i?")
s2.push("4")

print("\n--------- First Stack ---------\n")
for k in range(0, 10):
    try:
        print("[" + str(s1.pop()) + "]")
    except IndexError:
        print("Tried to pop from an empty stack")

print("\n--------- Second Stack ---------\n")
for k in range(0, 10):
    try:
        print("[" + str(s2.pop()) + "]")
    except IndexError:
        print("Tried to pop from an empty stack")