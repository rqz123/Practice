print("Let's study")

# manual tuple
tup_1 = (3, "three exactly", 2.1, "last")
print("type of tup_1: ", type(tup_1))
#tup_1[0] = 1
print(tup_1)

# tuple comprehension?
tup_2 = (x ** 2 for x in range(3, 9))
print("type of tup_2: ", type(tup_2)) 

print()

list_2 = [x ** 2 for x in range(3, 9) ]
print("type of list_2: ", type(list_2))
print ("\nLIST 2 (comprehension) --------------\n"
       "whole shebang: ", list_2,
       "\nsecond element =", list_2[0])
list_2[0] = 0
print(list_2)

print()

 # tuple comprehension - now it works
tup_2 = tuple(x ** 2 for x in range(3, 9))
print("type of tup_2: ", type(tup_2))
print ("\nTUPLE 2?\n"
       "whole shebang: ", tup_2,
       "\nsecond element =", tup_2[1] )

def func1(i):
    print(i)
    i = 100
    print(i)

num = 10
print(num)
func1(num)
print(num)

def func2(i):
    #i[0] = 100
    pass

nums = (10, 20, 30)
print(nums)
func2(nums)
print(nums)

def func3(i):
    i[0] = 100

lst = [10,20,30]


print(lst)
func1(lst)
print(lst)

class Patient:
 
    ORIGINAL_DEFAULT_NAME = "(no name)"
    default_name = ORIGINAL_DEFAULT_NAME
    
    @classmethod
    def get_default_name(self):
        return self.default_name

person1 = Patient()
person1.default_name = "richard"
print(person1.default_name);

print(person1.get_default_name())
print(Patient.get_default_name())

while True:
    try:
        user_choice = int(input("Enter a number ")) 
        twice_number = user_choice **2
        print(f"Twice your number is {twice_number}")
    except ValueError:
        print("Hey, that's not a number!")
    """
    else:
        twice_number = user_choice * 2
        print(f"Twice your number is {twice_number}")
    """