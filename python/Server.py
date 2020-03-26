import socket, threading
import time

class ClientThread(threading.Thread):
    def __init__(self, clientAddress, clientsocket):
        threading.Thread.__init__(self)
        self.csocket = clientsocket
        print ("New connection added: ", clientAddress)
    def run(self):
        print ("Connection from : ", clientAddress)
        #self.csocket.send(bytes("Hi, This is from Server..",'utf-8'))
        msg = ''
        while True:
            data = self.csocket.recv(2048)
            msg = data.decode()

            if msg == 'bye': break

            print ("from client <%s>: " % self.getName(), msg)

            self.csocket.send(bytes(msg,'UTF-8'))
        print ("Client <%s> disconnected" % self.getName())

LOCALHOST = "127.0.0.1"
PORT = 8080

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

print("Server started at", LOCALHOST, ":", PORT)
server.bind((LOCALHOST, PORT))
server.listen(1)

threadlist = []

while True:
    print("Waiting for client request ...")
    clientsock, clientAddress = server.accept()

    newthread = ClientThread(clientAddress, clientsock)
    threadlist.append(newthread)
    newthread.start()

    time.sleep(1.0)
    for thread in threadlist:
        if thread.is_alive(): 
            status = 'alive'
        else: 
            status = 'dead'
        print("Checking client <%s> %s" % (thread.getName(), status))
