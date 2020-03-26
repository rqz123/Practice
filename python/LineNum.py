import fileinput

for line in fileinput.input(inplace=True):
        line = line.rstrip()
        count = len(line.split())
        num = fileinput.lineno()
        print('%-40s # %2i : %2i' % (line, num, count))
