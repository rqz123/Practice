from PyQt5.QtWidgets import QApplication, QWidget, QGridLayout, QLabel, QPushButton
from PyQt5.QtCore import Qt, pyqtSlot, QTimer

@pyqtSlot()
def good_message():
    app_message.setText("Good to hear!")


@pyqtSlot()
def bad_message():
    app_message.setText("Sorry about that")


my_app = QApplication([])
layout = QGridLayout()
win = QWidget()
win.setLayout(layout)
"""
timer = QTimer(win)
timer.timeout.connect(win.update)
timer.start(1000)
"""
top_label = QLabel('Hello Foothill!')
top_label.setAlignment(Qt.AlignCenter)
second_label = QLabel('How are you?')
second_label.setAlignment(Qt.AlignCenter)
good_response = QPushButton('5 x 5')
bad_response = QPushButton('Not So Good')
app_message = QLabel()

layout.addWidget(top_label, 0, 0, 1, 2)
layout.addWidget(second_label, 1, 0, 1, 2)
layout.addWidget(good_response, 2, 0, 1, 1)
layout.addWidget(bad_response, 2, 1, 1, 1)
layout.addWidget(app_message, 3, 0, 1, 2)

good_response.clicked.connect(good_message)
bad_response.clicked.connect(bad_message)

win.show()
my_app.exec()