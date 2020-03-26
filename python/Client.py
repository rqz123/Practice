import socket

SERVER = "127.0.0.1"
PORT = 8080

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.settimeout(5.0)

try:
    client.connect((SERVER, PORT))
except socket.error as msg:
    print(msg)
    client.close()

try:
    client.sendall(bytes("This is the first message from client",'UTF-8'))
except socket.error as msg:
    print(msg)
    client.close()

try:
    while True:
        try:
            in_data = client.recv(1024)
        except socket.error as msg:
            print(msg)
            client.close()
        
        print("From server response :", in_data.decode())

        out_data = input()
        client.sendall(bytes(out_data,'UTF-8'))

        if out_data == 'bye': break
finally:
    client.close()
