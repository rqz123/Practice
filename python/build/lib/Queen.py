def conflict(state, next_x):
        next_y = len(state)
##        print('    next_x = ', next_x)
##        print('    next_y = ', next_y)
        for y in range(next_y):
##                print('      y = ', y)
##                print('      state[%d] = ' % y, state[y])
##                x = state[y] - next_x
##                print('      state[%d] - next_x = ' % y, x)
##                d = next_y - y
##                print('      next_y - y = ', d)
                if abs(state[y] - next_x) in (0, next_y - y):
##                        print('      conflict yes')
                        return True
##        print('      conflict no')
        return False

def queens(num = 8, state=()):
        '''
        Program calculates how many queens can be arrange in the chess
        '''
##        print('num = ', num)
##        print(state)
        for pos in range(num):
##                print('pos = ', pos) 
                if not conflict(state, pos):
                        if len(state) == num - 1:
##                                print('trying the last one ...')
                                yield (pos,)
                        else:
##                                print('going deeper ...')
                                for result in queens(num, state + (pos,)):
                                        yield (pos,) + result

def pretty_print(solution):
        def line(pos, length = len(solution)):
                return '[ ]' * (pos) + ' X ' + '[ ]' * (length - pos - 1)
        for pos in solution:
                print(line(pos))
                
import random

if __name__ == '__main__':
        import doctest, Queen
        doctest.testmod(Queen)
        pretty_print(random.choice(list(queens(8))))
