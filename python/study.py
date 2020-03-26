# Method 1 
##print ("Hello world!")
##name = input("What's your name? ")
##print ("Welcome " + name + ", let's start to learn Python from now!")

# Method 2
a = "Hello "
b = "Master "
print (a[2:5:2] + b + r"\ Yuda!")

temp = 25
#temp = input("What is temp? ")
temp += 10
print ("The temperature is " + repr(temp))

##temp = input("What is temp? ")
##print ("The temperature is " + temp)
##print ("The temperature is " + repr(temp))

users = [
	['richard', '1234'],
	['kelly', '5678'],
	['joseph', '9000']
]
##name = input("Enter your last name: ")
##print("Max char in name: " + max(name))
##pwd = input("PIN code: ")
##print("Min number in PIN: " + min(pwd))
##if [name, pwd] in users : print ("Access granted")
##print(list(users))

name = list('richard')
name[4:] = list('ver')
print(name)
name[4:4] = list('ard')
print(name)
name[7:] = []
print(name)

name = ['richard', 'kelly', 'joseph']
print(name)
print(name.index('kelly'))

format = "Pi with six decimals: %.6f"
from math import pi
print(format % pi)
i = 1
print('%d plus %d equals %d' % (i, 2, 3))

name = ['richard', 'kelly', 'joseph']
sep = ' and '
name_new = sep.join(name)
print(name_new)
print(name_new.split(' and '))
for nam in name:
        print(nam)
for num in range(len(name)):
        print(num)

blk = 'richard'
print([x for x in name if x not in blk]) # super!!

book = {'richard' :'1234', 'kelly' : '000000001', 'joseph' :'9000'}
for key in book:
        print(key, book[key])
print(len(book))
book_cp = book.copy()
##print(book['kelly'])
print(book.get('kelly'))
book['kelly'] = '8765'
##print(book['kelly'])
##del book['kelly']
##print(book)
##book[8765] = 'kelly'    # mixed database
print(book_cp)  # orignial db
print(book)     # modified db
##print('kelly\'s name and pwd has exchanged, %(8765)s.' % book)
print('popitem call')
nam, pwd = book.popitem()
print(nam, pwd)

name_list = {'richard', 'kelly', 'joseph'}
pwd_list = [1234, 5678, 3853]
for nam, pwd in zip(name_list, pwd_list):
        print(nam, pwd)
        break

for index, nam in enumerate(name_list):
        print(index, nam)

ybook = {
        'richard' : {
                'account': '00133455',
                'password': 3452
        },
        'kelly' : {
                'account': '02399540',
                'password': 1387
        },
        'joseph' : {
                'account': '00903734',
                'password': 2048
        }
}

nam = 'kelly'
act = 'account'
print(ybook[nam][act])

print('kelly\'s info %(kelly)s.' % ybook)
print('   act is %(account)s.' % ybook['kelly'])
print('   pwd is %(password)s.' % ybook['kelly'])
del nam, act

# print function
x, y, z = 42, 51, 0x63
print('Age:' + '42')
print('Age:', 51, 0x51)
print(x, y, z)

##for loop and else
##from math import sqrt
##for n in range(99, 81, -1):
##        root = sqrt(n)
##        if root == int(root):
##                print(n)
##                break
##        else:
##                print('Didnt find it')
print([x*x for x in range(10)])
print([(x, y) for x in range(3) for y in range(3)])

girls =['alice', 'bernice', 'clarice']
boys = ['chris', 'arnold', 'bob']
#print([b + '+' + g for b in boys for g in girls if b[0] == g[0]])
letter_girls = {}
for girl in girls:
        letter_girls.setdefault(girl[0], []).append(girl)
print(letter_girls)
print([b + '+' + g for b in boys for g in letter_girls[b[0]]])

##def square(x):
##        'Calculates the square of the number x'
##        x += 1
##        return x*x
##x = 10
##print(square(x))
##print(x)
def init(data_base):
        'Initial data-base with three keys'
        data_base['first'] = {}
        data_base['middle'] = {}
        data_base['last'] = {}

def lookup(data_base, label, name):
        #print(data_base, label, name)
        return data_base[label].get(name)

def store(data_base, full_name):
        'Store a name in database by first, middle and last name'
        names = full_name.split()
        #print(names)

        if len(names) == 2: names.insert(1, 'n/a')
        labels = 'first', 'middle', 'last'

        for label, name in zip(labels, names):
                people = lookup(data_base, label, name)
                #print(people)
                if people:
                        people.append(full_name)
                else:
                        data_base[label][name] = [full_name]

storage = {}
init(storage)
store(storage, 'richard qing zhang')
store(storage, 'kelly xuemei yang')
store(storage, 'joseph zhang')
print(storage)

def var_param(*params):
        print(params)
        return params

params = var_param('abc', 1, 2)
print(params[0])
print(params[1])
print(params[2])

def var_param1(**params):
        print(params)
        print('%(abc)s equal %(deff)s: ' % params)
var_param1(abc=1, deff=2)
var_param2 = var_param1
var_param2(deff=2, abc=1)


def multiplier(factor):
        def multiplyByFactor(number):
                return number*factor
        return multiplyByFactor
double = multiplier(2)
print(double(6))

class myClass:
        name_count = 0  # this is different with self.name_count
        def method(self):
                print('I am in myClass')
                
        def set_name(self, name):
                myClass.name_count += 1
                self.name = name
        def get_name(self):
                print('name_count has been changed ', myClass.name_count)
                return self.name
def myFunc():
        print('I am a function')

inst = myClass()
inst.method()
inst.method = myFunc
inst.method()   # strange, allow class member pointed to outside func

inst.set_name('richard')
print(inst.get_name())
inst.name = 'kelly'
print(inst.get_name())
print('inst.name_count = ', inst.name_count)

inst2 = myClass()
inst2.set_name('joseph')
print(inst.get_name())  # strange, name_count in inst changed as well
print(inst2.get_name())
print('inst.name_count = ', inst.name_count)

inst.name_count = 'two' # seems inst.name_count != myClass.name_count
print('inst.name_count = ', inst.name_count)
print(inst.get_name())  
print(inst2.get_name())
print('inst2.name_count = ', inst2.name_count)

inst3 = myClass()
inst3.set_name('rich')
print(inst.get_name())  
print('inst.name_count = ', inst.name_count)
print(inst2.get_name())
print('inst2.name_count = ', inst2.name_count)
print(inst3.get_name())  
print('inst3.name_count = ', inst3.name_count)

class Filter:
        def init(self):
                self.blked = [3]
        def filter(self, seq):
                return [x for x in seq if x not in self.blked]
class SPAMFilter(Filter):
        def init(self):
                self.blked = ['SPAM']
f = Filter()
f.init()
print(f.filter(['SPAM', 'abc', 'eggs', 'SPAM', 1, 2, 3, 4, 5]))
s = SPAMFilter()
s.init()
print(s.filter(['SPAM', 'abc', 'eggs', 'SPAM', 1, 2, 3, 4, 5]))

##try:
##        x = (int)(input('enter x: '))
##        y = (int)(input('enter y: '))
##        print (x / y)
##except ZeroDivisionError:
##        print('The second number cannot be zero')
##except ValueError:
##        print('That was not a number')

##__metaclass__ = type
class myRect:
        def __init__(self):
                self.width = 0
                self.height = 0
                print('myClass __init__ function called')
        def getSize(self):
                return self.width, self.height
        def setSize(self, size):
                self.width, self.height = size
        size = property(getSize, setSize)
        
rc = myRect()
##rc.setSize((100, 200))
##print(rc.getSize())
rc.size = 100, 200
print(rc.size)

class Fibs:
        def __init__(self):
                self.a = 0
                self.b = 1
        def __next__(self):
                self.a, self.b = self.b, self.a + self.b
                if self.a > 1000: raise StopIteration
                return self.a
        def __iter__(self):
                return self
fibs = Fibs()
##for f in fibs:
##        if f > 1000:
##                print(f)
##                break
print(list(fibs))

def flatten(nested, value):
        for sublist in nested:
                for element in sublist:
                        yield element + value
##                        print(element)
nested = [[1, 2], [3, 4], [5]]
for num in flatten(nested, 10):
        print(num)
        break
for num in flatten(nested, 100):
        print(num)
##flatten(nested)

##import Queen

##from pprint import pprint
##values = range(1, 11) + tuple('Jack Queen King'.split())
##suits = 'diamonds clubs hearts spades'.split()
##deck = ['%s of %s' % (v, s) for v in values for s in suits]
##pprint(deck)

##import shelve
##s = shelve.open('test.dat')
####s['name'] = ['a','b','c']
####s['num'] = 999
##print(s['num'])
##s.close()
try:
        with open('test.txt') as fp:
##        fp = open('test.txt','r')
                cnt = 0
                for line in fp: # does not need to call readline()
##                        print(line)
                        print("line {} contents {}".format(cnt, line.strip()))
                        cnt += 1
##        fp.close()
except IOError as e:
        print('Open file failed: %s' % e.strerror)

import re
some_text = 'as, dfasdf, asfsadf, sfdewr,,  as kkkk ,,,,dsfsdf, sderre, 123'
ret = re.match('as(.*)', some_text)
print(ret.group(1))
print(re.split('[,]+', some_text))
pat = re.compile('From: (.*) <.*?>$')

##from urllib import request
##web = request.urlopen('C:\\Development\\Study\\Untitled-1.txt')
##header = web.inf()
##data = web.read()

from configparser import ConfigParser
CONFIGFILE='config.txt'

cfg = ConfigParser()
cfg.read(CONFIGFILE)
print(cfg.sections())
print(cfg['messages']['width'], cfg['numbers']['x'])
y = int(cfg.get('numbers', 'y'))
print(y * 2)

class ABC:
        def callback(self, name, *args):
                method = getattr(self, name, *args)
                if callable(method):
                        method(*args)
        def func1(self, arg):
                print('ABC::func1', arg)
        def func2(self, arg):
                print('ABC::func2', arg)

abc = ABC()
abc.callback('func1', 100)
call = getattr(abc, 'func2')
if callable(call): call(200)

import time
# from threading import Thread

# def printer():
#         for _ in range(3):
#                 time.sleep(1.0)
#                 print ("hello")

# thread = Thread(target=printer)
# thread.start()
# thread.join()
# if thread.is_alive: 
#         print ("thread is alive")
# else:
#         print ("thread is dead")

import threading
"""
class myThread(threading.Thread):
        def __init__(self):
                threading.Thread.__init__(self)
        def run(self):
                time.sleep(1.0)
                print("I am alive")

my = myThread()
my.start()
my.join()
if my.is_alive(): 
        print ("thread is alive")
else:
        print ("thread is dead")
"""
def thread_process(arg, id):
        time_out = 5
        while True:
                print('I am thread %d in cycle %d' % (id, time_out))
                time.sleep(1.0)
                time_out -= 1
                if time_out == 0:
                        break

arg = {'a':10, 'b':20, 'c':30}

tp_list = []
for id in range(100, 105):
        tp = threading.Thread(target = thread_process, args = (arg, id))
        tp_list.append(tp)
        tp.start()
time.sleep(4.0)
for tp in tp_list:
        if tp.is_alive(): 
                print ("thread is alive")
        else:
                print ("thread is dead")
