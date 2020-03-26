""" Assignment Extra Credit: Graphical User Interface - Joseph Zhang
"""
from PyQt5.QtWidgets import QApplication, QWidget, QGridLayout, QLabel, QPushButton
from PyQt5.QtCore import Qt, pyqtSlot, QTimer
from functools import partial
import matplotlib.pyplot as plt
from matplotlib.backends.backend_qt5agg import FigureCanvasQTAgg as FigureCanvas
from collections import OrderedDict
import numpy as np
import math

current_unit = 0
UNITS = {
    0: ("Celsius", "C"),
    1: ("Fahrenheit", "F"),
    2: ("Kelvin", "K")
}

DAYS = {
    0: "SUN",
    1: "MON",
    2: "TUE",
    3: "WED",
    4: "THU",
    5: "FRI",
    6: "SAT"
}

HOURS = {
    0: "Mid-1AM  ",
    1: "1AM-2AM  ",
    2: "2AM-3AM  ",
    3: "3AM-4AM  ",
    4: "4AM-5AM  ",
    5: "5AM-6AM  ",
    6: "6AM-7AM  ",
    7: "7AM-8AM  ",
    8: "8AM-9AM  ",
    9: "9AM-10AM ",
    10: "10AM-11AM",
    11: "11AM-NOON",
    12: "NOON-1PM ",
    13: "1PM-2PM  ",
    14: "2PM-3PM  ",
    15: "3PM-4PM  ",
    16: "4PM-5PM  ",
    17: "5PM-6PM  ",
    18: "6PM-7PM  ",
    19: "7PM-8PM  ",
    20: "8PM-9PM  ",
    21: "9PM-10PM ",
    22: "10PM-11PM",
    23: "11PM-MID ",
}


def print_header():
    """ Prints the header """
    print("STEM Center Temperature Project")
    print("Joseph Zhang")


def print_menu():
    """ Prints the menu """
    print("\nMain Menu\n"
          "---------")
    print("1 - Process a new data file")
    print("2 - Choose units")
    print("3 - Edit room filter")
    print("4 - Show summary statistics")
    print("5 - Show temperature by date and time")
    print("6 - Show histogram of temperatures")
    print("7 - Quit\n")


def recursive_sort(list_to_sort, key):
    """ Sorts the list using bubble sort recursively """
    list_ready = list_to_sort.copy()
    for i in range(0, len(list_ready) - 1):
        if list_ready[i][key] > list_ready[i + 1][key]:
            (list_ready[i + 1], list_ready[i]) = (list_ready[i],
                                                  list_ready[i + 1])
    if len(list_ready) == 1:
        return list_to_sort
    else:
        list_sorted = recursive_sort(list_ready[0:len(list_ready) - 1], key) \
                      + [list_ready[-1]]
    return list_sorted


def print_filter(sensor_list, active_sensors):
    """ Prints the filter list """
    print("")
    for x in range(0, len(sensor_list), 1):
        print(f"{sensor_list[x][0]}: {sensor_list[x][1]}", end='')
        # Prints the state
        if sensor_list[x][2] in active_sensors:
            print(" [ACTIVE]")
        else:
            print("")


def new_file(dataset):
    """ Process the file, by default: Temperatures2017-08-06.csv """
    answer = input("Please enter the filename of the new dataset: ")
    # Open and read file
    result = dataset.process_file(answer)
    if not result:
        print("Unable to load a file")
        return False
    # else
    print(f"Loaded {dataset.get_loaded_temps()} samples")
    # Report the number of samples
    while True:
        """Gives a "name" to the object """
        new_name = input("Please provide a 3 to 20 "
                         "character name for the dataset: ")
        try:
            dataset.name = new_name
            break
        except ValueError:
            print("Sorry but your name is not valid")


def choose_units():
    """ Lets user choose the units for the rest of the program """
    global current_unit

    # Checks the current unit
    print(f"Current units in {UNITS[current_unit][0]}")

    # Asks the user for a new unit
    while True:
        try:
            # Prints the dictionary for the new units
            print("Choose new units:")
            for i in range(0, 3, 1):
                print(f"{i} - {UNITS[i]}")
            current_unit = int(input("Which unit?\n"))
        except ValueError:
            print("*** Please enter a number only ***")
        else:
            if current_unit not in UNITS:
                print("Please choose a unit from the list")
            else:
                break


def change_filter(sensor_list, active_sensors):
    """ Lets the user change the filter list """
    sensors = {
        sensor_list[0][0]: sensor_list[0][2],
        sensor_list[1][0]: sensor_list[1][2],
        sensor_list[2][0]: sensor_list[2][2],
        sensor_list[3][0]: sensor_list[3][2],
        sensor_list[4][0]: sensor_list[4][2],
        sensor_list[5][0]: sensor_list[5][2]
    }
    while True:
        print_filter(sensor_list, active_sensors)
        key = input("\nType the sensor number to toggle "
                    "(e.g.4201) or x to end ")
        if key == "x":
            break

        # Add or remove active states in active sensors
        if key in sensors:
            if sensors[key] in active_sensors:
                active_sensors.remove(sensors[key])
            else:
                active_sensors.append(sensors[key])
        else:
            print("Invalid Sensor")


def print_summary_statistics(dataset, active_sensors):
    """ Prints the summary statistics of the temperature on specific day and time """
    # Checks if the data is loaded and at least one sensor is active
    if dataset.name == "Unnamed" or len(active_sensors) == 0:
        print("Please load data file and "
              "make sure at least one sensor is active")
        return False

    # Checks if the received tuple is empty
    value_tuple = dataset.get_summary_statistics(active_sensors)
    if value_tuple is None:
        return False

    # Gets minimum, maximum, and average temperature
    minimum = value_tuple[0]
    maximum = value_tuple[1]
    avg_temperature = value_tuple[2]

    # Converts the minimum, maximum, and the avg_temperature into appropriate units
    minimum = convert_units(minimum, current_unit)
    maximum = convert_units(maximum, current_unit)
    avg_temperature = convert_units(avg_temperature, current_unit)

    # Prints the summary statistics
    print(f"Summary statistics for {dataset.name}")
    print(f"Mininum Temperature: {minimum:0.2f} {UNITS[current_unit][1]}")
    print(f"Maximum Temperature: {maximum:0.2f} {UNITS[current_unit][1]}")
    print(f"Average Temperature: "
          f"{avg_temperature:0.2f} {UNITS[current_unit][1]}")


def print_temp_by_day_time(dataset, active_sensors):
    """ Prints a table of all the average temperature at a specific day/time """
    if dataset.get_loaded_temps() == 0:
        print("Please load data file")
        return False
    # else

    # Prints information about the table
    print(f"Average Temperatures for {dataset.name}")
    print(f"Units are in {UNITS[current_unit][0]}")

    # Prints table
    # Days(top display)
    print("             ", end="")
    for x in range(0, len(DAYS), 1):
        print(f"{DAYS[x]:6}", end="")
        # Prepares to go to next row
        if x == 6:
            print("")

    # Hours
    for a in range(0, len(HOURS), 1):
        print(f"{HOURS[a]} ", end="")
        # Days(actual info)
        for b in range(0, len(DAYS), 1):
            # Gets data
            temperature = dataset.get_avg_temperature_day_time(active_sensors, b, a)

            # If data is none
            if temperature is None:
                empty = "   ---"
                print(empty, end="")
            else:
                # Unit conversion
                temperature = convert_units(temperature, current_unit)
                print(f"{temperature:6.1f}", end="")

            # Prepares to go to next row
            if b == 6:
                print("")


@pyqtSlot()
def button_clicked(dataset, sensor_id):
    """Button click function"""
    temperature_list = dataset.get_temperature_by_sensor(sensor_id)
    #print(temperature_list)

    # Draws histogram for the specified sensor
    dataset.axes[0].remove()
    dataset.axes[0] = plt.axes()
    if temperature_list is not None:
        dataset.axes[0].hist(temperature_list)
        dataset.axes[0].set_xlabel("Temperatures (Celsius)")
        dataset.axes[0].set_ylabel("Number of times the temperature "
                                   "appeares in the data")
    dataset.plot_space.draw_idle()
    
    
def print_histogram(dataset, sensor_list, active_sensors):
    """A GUI shows histogram for all the data points of the sensors"""
    # Check cases
    if dataset.get_loaded_temps() == 0:
        print("Please load data file")
        return False

    if active_sensors is None or len(active_sensors) == 0:
        print("No active sensor to display")
        return False

    my_app = QApplication([])
    layout = QGridLayout()
    win = QWidget()
    win.setLayout(layout)
    
    # Sensor buttons
    sensor_buttons = [QPushButton(sensor_list[i][1])
                        for i in range(0, len(sensor_list))]
    sensor_ids = [sensor_list[i][2] for i in range(0, len(sensor_list))]
    
    top_label = QLabel("Histogram of all sensor data points")
    top_label.setAlignment(Qt.AlignCenter)
    layout.addWidget(top_label, 0, 0, 1, len(sensor_buttons))
    second_label = QLabel("Select the sensor to display histogram")
    second_label.setAlignment(Qt.AlignCenter)
    layout.addWidget(second_label, 1, 0, 1, len(sensor_buttons))
                  
    for x in range(0, len(sensor_buttons)):
        layout.addWidget(sensor_buttons[x], 2, x, 1, 1)
        sensor_buttons[x].clicked.connect(
            partial(button_clicked, dataset, sensor_ids[x]))

    # plot space
    dataset.plot_space = None
    dataset.axes = [None]

    figure, dataset.axes[0] = plt.subplots()
    dataset.plot_space = FigureCanvas(figure)
    layout.addWidget(dataset.plot_space, 3, 0, 1, len(sensor_buttons))

    # Display window
    win.show()
    my_app.exec()

    # Remove instance variable
    del dataset.plot_space
    del dataset.axes
        

def quit_program():
    print("Thank you for using the STEM Center Temperature Project")


class TempDataset:
    """Modifying Temperature datasets that are inputed"""
    tempDataSet_objects = 0

    def __init__(self):
        self._data_set = []
        self._set_name = "Unnamed"

        TempDataset.tempDataSet_objects += 1

    """ Getter - returns the name of the data set """
    @property
    def name(self):
        return self._set_name

    """ Setter - sets the name of the data set """
    @name.setter
    def name(self, new_name):
        if type(new_name) != str or len(new_name) < 3 or len(new_name) > 20:
            raise ValueError
        # else
        self._set_name = new_name

    """ Processes the file through file IO """
    def process_file(self, filename):
        # Open file
        try:
            my_file = open(filename, 'r')
        except FileNotFoundError:
            return False

        # Reinitialize this as an empty list
        self._data_set.clear()

        for next_line in my_file:
            # Read in each line from the file
            copy_list = next_line.split(",")
            # Modifies the copy list and turns it into a tuple to add to the list
            if copy_list[3] == 'TEMP':
                # Convert Time of Day
                day = int(copy_list[0])
                time = math.floor((float(copy_list[1]) * 24))
                sensor = int(copy_list[2])
                # Convert temperature
                temp = float(copy_list[4])
                # Store in a tuple
                processed_tuple = (day, time, sensor, temp)
                # Add the tuple to the list_data
                self._data_set.append(processed_tuple)

        # close file
        my_file.close()

        return True

    """ Gets the summary statistics of active_sensors """
    def get_summary_statistics(self, active_sensors):
        if active_sensors is None:
            return None
        # else
        # Get a list of all the compatible temperatures
        temperature_list = [self._data_set[i][3]
                            for i in range(0, len(self._data_set))
                            if self._data_set[i][2] in active_sensors]

        # If all sensors are off
        if len(temperature_list) == 0:
            return None

        # Calculate min and max
        minimum = min(temperature_list)
        maximum = max(temperature_list)

        # Calculate average
        avg_temperature = 0
        for x in range(0, len(temperature_list)):
            avg_temperature += temperature_list[x]
        avg_temperature = avg_temperature / len(temperature_list)

        # minimum, maximum, average temp
        return minimum, maximum, avg_temperature

    """ Gets the average temperature day time """
    def get_avg_temperature_day_time(self, active_sensors, day, time):
        #Check cases
        # None if no dataset loaded
        # None if no sensors active
        # None if active sensors have no readings
        if active_sensors is None \
                or len(active_sensors) == 0 \
                or [self._data_set[i][3]
                    for i in range(0, len(self._data_set))
                    if self._data_set[i][2] in active_sensors
                    and self._data_set[i][0] == day
                    and self._data_set[i][1] == time] == []:
            return None
        # else
        # Get a list of all the compatiable temperatures
        temperature_list = [self._data_set[i][3]
                            for i in range(0, len(self._data_set))
                            if self._data_set[i][2] in active_sensors
                            and self._data_set[i][0] == day
                            and self._data_set[i][1] == time]

        # If all sensors are off
        if len(temperature_list) == 0:
            return None

        # Calculate average
        avg_temperature = 0
        for x in range(0, len(temperature_list)):
            avg_temperature += temperature_list[x]

        return avg_temperature / len(temperature_list)

    """ Gets number of temperatures """
    def get_num_temps(self, active_sensors, lower_bound, upper_bound):
        if active_sensors is None:
            return None
        # else
        return 0

    """ Returns the data set """
    def get_loaded_temps(self):
        if self._data_set is None:
            return None
        # else
        return len(self._data_set)

    """ Returns the number of objects created """
    @classmethod
    def get_num_objects(cls):
        return cls.tempDataSet_objects

    """ Gets the temperature list by the specified sensor """
    def get_temperature_by_sensor(self, sensor_id):
        # Get a list of all the compatible temperatures
        temperature_list = [self._data_set[i][3]
                            for i in range(0, len(self._data_set))
                            if self._data_set[i][2] == sensor_id]

        # If no data for the specified sensor
        if len(temperature_list) == 0:
            return None

        return temperature_list


def main():
    """ The main """
    # Initiating sensor_list
    sensor_list = [
        ("4213", "STEM Center", 0),
        ("4201", "Foundations Lab", 1),
        ("4204", "CS Lab", 2),
        ("4218", "Workshop Room", 3),
        ("4205", "Tiled Room", 4),
        ("Out", "Outside", 10)
    ]

    # Initiating active_sensors
    active_sensors = [sensor_list[x][2]
                      for x in range(0, len(sensor_list))]

    # Sort sensor_list
    sensor_list = recursive_sort(sensor_list, 0)

    # Creates an object of the class
    current_set = TempDataset()
    #current_set.process_file("Temperatures2017-08-06.csv")

    # Calls the print header function
    print_header()

    while True:
        # Calls the print menu function
        print_menu()
        #print(current_set.get_avg_temperature_day_time(active_sensors, 5, 7))

        # Checks if the input is an integer
        try:
            choice = int(input("What is your choice? "))

            # Checks if the input is between 1 to 7
            if choice < 1 or choice > 7:
                print("Invalid Choice")
                continue

            if choice == 1:
                new_file(current_set)
            elif choice == 2:
                choose_units()
            elif choice == 3:
                change_filter(sensor_list, active_sensors)
            elif choice == 4:
                print_summary_statistics(current_set, active_sensors)
            elif choice == 5:
                print_temp_by_day_time(current_set, active_sensors)
            elif choice == 6:
                print_histogram(current_set, sensor_list, active_sensors)
            elif choice == 7:
                quit_program()
                break
        except ValueError:
            print("*** Please enter a number only ***")


def convert_units(celsius_value, units):
    """ Converts the celsius value into different units """
    if units == 0:
        return celsius_value
    elif units == 1:
        return celsius_value * 9 / 5 + 32
    elif units == 2:
        return celsius_value + 273.15


if __name__ == "__main__":
    main()

""" ----- SAMPLE RUN -----
/Users/josephzhang/PycharmProjects/testingOne/venv/bin/python /Users/josephzhang/PycharmProjects/testingOne/AssignmentEleven.py
STEM Center Temperature Project
Joseph Zhang

Main Menu
---------
1 - Process a new data file
2 - Choose units
3 - Edit room filter
4 - Show summary statistics
5 - Show temperature by date and time
6 - Show histogram of temperatures
7 - Quit

What is your choice? 1
Please enter the filename of the new dataset: Temperatures2017-08-06.csv
Loaded 11724 samples
Please provide a 3 to 20 character name for the dataset: Test Week

Main Menu
---------
1 - Process a new data file
2 - Choose units
3 - Edit room filter
4 - Show summary statistics
5 - Show temperature by date and time
6 - Show histogram of temperatures
7 - Quit

What is your choice? 5
Average Temperatures for Test Week
Units are in Celsius
             SUN   MON   TUE   WED   THU   FRI   SAT   
Mid-1AM     21.1  20.6  21.7  21.5  21.0  21.1  19.8
1AM-2AM     21.1  20.5  21.6  21.5  20.9  21.1  19.9
2AM-3AM     21.1  20.4  21.5  21.4  20.9  21.1  19.8
3AM-4AM     21.1  20.4  21.4  21.3  20.8  21.0  19.8
4AM-5AM     21.1  20.4  21.4  21.2  20.8  21.0  19.9
5AM-6AM     21.0  20.2  21.4  21.2  20.7  20.8  19.8
6AM-7AM     20.9  19.9  21.3  21.0  20.6  20.6  19.8
7AM-8AM     20.7  20.0  21.1  20.9  20.6  20.5  19.9
8AM-9AM     20.6  20.2  21.2  20.8  20.7  20.3  19.9
9AM-10AM    20.9  21.1  22.0  20.9  21.2  20.2  20.2
10AM-11AM   21.2  21.9  22.8  21.5  22.1  20.4  20.6
11AM-NOON   21.5  22.6  23.4  22.2  22.7  20.7  20.8
NOON-1PM    21.6  23.0  23.9  22.6  23.0  21.0  21.0
1PM-2PM     21.7  23.3  24.0  23.1  23.2  21.0  21.0
2PM-3PM     21.9  23.6  24.2  23.5  23.3  21.1  21.0
3PM-4PM     21.9  24.0  24.4  23.6  23.5  21.1  20.8
4PM-5PM     21.7  24.2  24.5  23.8  23.6  21.0  20.9
5PM-6PM     21.6  24.1  24.4  23.7  23.7  20.8  20.9
6PM-7PM     21.5  23.4  23.9  23.4  23.2  20.7  20.7
7PM-8PM     21.4  23.0  23.2  22.8  22.3  20.3  20.5
8PM-9PM     21.2  22.6  22.3  22.1  21.6  19.8  20.2
9PM-10PM    21.0  22.3  21.8  21.7  21.2  19.7  19.9
10PM-11PM   20.8  22.0  21.7  21.5  21.2  19.8  19.8
11PM-MID    20.8  21.9  21.6  21.2  21.1  19.8  19.7

Main Menu
---------
1 - Process a new data file
2 - Choose units
3 - Edit room filter
4 - Show summary statistics
5 - Show temperature by date and time
6 - Show histogram of temperatures
7 - Quit

What is your choice? 3

4201: Foundations Lab [ACTIVE]
4204: CS Lab [ACTIVE]
4205: Tiled Room [ACTIVE]
4213: STEM Center [ACTIVE]
4218: Workshop Room [ACTIVE]
Out: Outside [ACTIVE]

Type the sensor number to toggle (e.g.4201) or x to end 4201

4201: Foundations Lab
4204: CS Lab [ACTIVE]
4205: Tiled Room [ACTIVE]
4213: STEM Center [ACTIVE]
4218: Workshop Room [ACTIVE]
Out: Outside [ACTIVE]

Type the sensor number to toggle (e.g.4201) or x to end 4204

4201: Foundations Lab
4204: CS Lab
4205: Tiled Room [ACTIVE]
4213: STEM Center [ACTIVE]
4218: Workshop Room [ACTIVE]
Out: Outside [ACTIVE]

Type the sensor number to toggle (e.g.4201) or x to end 4205

4201: Foundations Lab
4204: CS Lab
4205: Tiled Room
4213: STEM Center [ACTIVE]
4218: Workshop Room [ACTIVE]
Out: Outside [ACTIVE]

Type the sensor number to toggle (e.g.4201) or x to end Out

4201: Foundations Lab
4204: CS Lab
4205: Tiled Room
4213: STEM Center [ACTIVE]
4218: Workshop Room [ACTIVE]
Out: Outside

Type the sensor number to toggle (e.g.4201) or x to end x

Main Menu
---------
1 - Process a new data file
2 - Choose units
3 - Edit room filter
4 - Show summary statistics
5 - Show temperature by date and time
6 - Show histogram of temperatures
7 - Quit

What is your choice? 2
Current units in Celsius
Choose new units:
0 - ('Celsius', 'C')
1 - ('Fahrenheit', 'F')
2 - ('Kelvin', 'K')
Which unit?
1

Main Menu
---------
1 - Process a new data file
2 - Choose units
3 - Edit room filter
4 - Show summary statistics
5 - Show temperature by date and time
6 - Show histogram of temperatures
7 - Quit

What is your choice? 5
Average Temperatures for Test Week
Units are in Fahrenheit
             SUN   MON   TUE   WED   THU   FRI   SAT   
Mid-1AM     68.8  68.4  72.7  71.3  70.6  70.7  66.8
1AM-2AM     69.0  68.3  72.5  71.1  70.3  70.5  66.9
2AM-3AM     69.1  68.3  72.3  70.9  70.0  70.4  67.0
3AM-4AM     69.2  68.1  72.2  70.8  69.8  70.3  67.0
4AM-5AM     69.2  68.1  72.1  70.6  69.7  70.1  67.1
5AM-6AM     69.2  68.0  72.1  70.5  69.6  70.0  67.1
6AM-7AM     68.8  67.9  72.1  70.1  69.4  69.6  67.1
7AM-8AM     68.1  68.1  71.8  70.0  69.5  69.2  67.1
8AM-9AM     67.4  68.1  71.1  69.5  69.7  68.3  67.1
9AM-10AM    67.3  69.1  71.5  69.4  70.6  67.1  67.2
10AM-11AM   67.1  70.4  72.3  69.9  71.5  66.6  67.2
11AM-NOON   66.9  70.9  73.2  70.4  72.2  66.6  66.6
NOON-1PM    66.8  71.2  73.1  71.3  72.1  66.3  65.9
1PM-2PM     66.7  71.9  73.6  72.3  71.9  66.1  65.5
2PM-3PM     66.9  72.8  74.3  73.1  72.3  66.1  65.2
3PM-4PM     66.7  73.3  74.7  74.0  72.7  66.1  65.0
4PM-5PM     66.7  73.8  75.1  74.4  73.4  66.0  64.9
5PM-6PM     66.7  74.2  75.7  74.9  74.0  66.0  64.9
6PM-7PM     66.7  73.5  75.1  74.6  73.5  65.8  64.8
7PM-8PM     67.2  73.4  74.0  73.4  72.5  65.7  64.8
8PM-9PM     67.8  73.4  73.0  72.6  71.7  65.4  64.7
9PM-10PM    68.1  73.3  72.2  71.7  71.1  65.5  64.9
10PM-11PM   68.3  73.2  71.8  71.3  70.9  66.3  65.5
11PM-MID    68.6  73.0  71.5  70.9  70.8  66.6  65.7

Main Menu
---------
1 - Process a new data file
2 - Choose units
3 - Edit room filter
4 - Show summary statistics
5 - Show temperature by date and time
6 - Show histogram of temperatures
7 - Quit

What is your choice? 3

4201: Foundations Lab
4204: CS Lab
4205: Tiled Room
4213: STEM Center [ACTIVE]
4218: Workshop Room [ACTIVE]
Out: Outside

Type the sensor number to toggle (e.g.4201) or x to end 4213

4201: Foundations Lab
4204: CS Lab
4205: Tiled Room
4213: STEM Center
4218: Workshop Room [ACTIVE]
Out: Outside

Type the sensor number to toggle (e.g.4201) or x to end 4218

4201: Foundations Lab
4204: CS Lab
4205: Tiled Room
4213: STEM Center
4218: Workshop Room
Out: Outside

Type the sensor number to toggle (e.g.4201) or x to end x

Main Menu
---------
1 - Process a new data file
2 - Choose units
3 - Edit room filter
4 - Show summary statistics
5 - Show temperature by date and time
6 - Show histogram of temperatures
7 - Quit

What is your choice? 5
Average Temperatures for Test Week
Units are in Fahrenheit
             SUN   MON   TUE   WED   THU   FRI   SAT   
Mid-1AM      ---   ---   ---   ---   ---   ---   ---
1AM-2AM      ---   ---   ---   ---   ---   ---   ---
2AM-3AM      ---   ---   ---   ---   ---   ---   ---
3AM-4AM      ---   ---   ---   ---   ---   ---   ---
4AM-5AM      ---   ---   ---   ---   ---   ---   ---
5AM-6AM      ---   ---   ---   ---   ---   ---   ---
6AM-7AM      ---   ---   ---   ---   ---   ---   ---
7AM-8AM      ---   ---   ---   ---   ---   ---   ---
8AM-9AM      ---   ---   ---   ---   ---   ---   ---
9AM-10AM     ---   ---   ---   ---   ---   ---   ---
10AM-11AM    ---   ---   ---   ---   ---   ---   ---
11AM-NOON    ---   ---   ---   ---   ---   ---   ---
NOON-1PM     ---   ---   ---   ---   ---   ---   ---
1PM-2PM      ---   ---   ---   ---   ---   ---   ---
2PM-3PM      ---   ---   ---   ---   ---   ---   ---
3PM-4PM      ---   ---   ---   ---   ---   ---   ---
4PM-5PM      ---   ---   ---   ---   ---   ---   ---
5PM-6PM      ---   ---   ---   ---   ---   ---   ---
6PM-7PM      ---   ---   ---   ---   ---   ---   ---
7PM-8PM      ---   ---   ---   ---   ---   ---   ---
8PM-9PM      ---   ---   ---   ---   ---   ---   ---
9PM-10PM     ---   ---   ---   ---   ---   ---   ---
10PM-11PM    ---   ---   ---   ---   ---   ---   ---
11PM-MID     ---   ---   ---   ---   ---   ---   ---

Main Menu
---------
1 - Process a new data file
2 - Choose units
3 - Edit room filter
4 - Show summary statistics
5 - Show temperature by date and time
6 - Show histogram of temperatures
7 - Quit

What is your choice? 7
Thank you for using the STEM Center Temperature Project

"""
