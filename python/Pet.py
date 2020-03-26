class Pet:
    """ a class used to represent a pet

    Attributes
    pets_name : str
        a string containing the pet's name
    ownders_name : str
        a string containing the owner's name
    weight : float
        weight of the pet in pounds
    number_of_limbs : integer
        the number of limbs the pet actually has

    """

    def __init__(self):
        self._pets_name = "(undefined)"
        self._owners_name = "(undefined)"
        self._weight = 0.0
        self._number_of_limbs = 0

    @property
    def pets_name(self):
        return self._pets_name

    @pets_name.setter
    def pets_name(self, new_name):
        self._pets_name = new_name

    @property
    def weight(self):
        return self._weight

    @weight.setter
    def weight(self, new_weight):
        if new_weight < 0:
            raise ValueError
        self._weight = new_weight

    @property
    def number_of_limbs(self):
        return self._number_of_limbs

    @number_of_limbs.setter
    def number_of_limbs(self, new_limbs):
        if new_limbs < 0:
            raise ValueError
        self._number_of_limbs = new_limbs

    @property
    def owners_name(self):
        return self._owners_name

    @owners_name.setter
    def owners_name(self, new_owners_name):
        self._owners_name = new_owners_name

def main():
    # define a Pet object
    my_dog = Pet()

    # fill in ONLY SOME of the fields (or attributes) of my_dog
    my_dog.pets_name = "Jerry of Kipling"
    print(my_dog.pets_name)

    my_dog.owners_name = "Mike Byers"
    my_dog.weight = 8.5

    my_dog.hair = "yellow"
    del my_dog.hair

    # show my_dog to the user
    print(f"{my_dog.pets_name} is owned by {my_dog.owners_name}, has "
          f"{my_dog.number_of_limbs} limbs and weighs {my_dog.weight} lbs.")

    # assign one object's data to another reference
    noisy_dog = my_dog

    # del my_dog
    
    # modify individual attributes (using noisy_dog reference)
    noisy_dog.pets_name = "chloe"
    noisy_dog.weight = 31.2

    # use noisy_dog reference to view data of the same object
    print(f"{noisy_dog.pets_name} is owned by {noisy_dog.owners_name}, has "
          f"{noisy_dog.number_of_limbs} limbs and weighs "
          f"{noisy_dog.weight} lbs.")

    print(f"{my_dog.pets_name} is owned by {my_dog.owners_name}, has "
          f"{my_dog.number_of_limbs} limbs and weighs {my_dog.weight} lbs.")

if __name__ == "__main__":
    main()