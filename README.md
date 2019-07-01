# PhoneBook

### The normal Bulgarian phone number is like: +359878123456, where:
* +359 is the code for Bulgarian numbers;
* The next 2 digits are the code of the operator, where the 3 operators have their own
combinations- 87, 88, 89;
* The next digit is in range from 2 to 9;
* The last 6 digits are in range from 0 to 9;

### Equivalents to +359878123456 are:
* 0878123456, where 0 replaces +359;
* 00359878123456, where 00 replaces +;
All other combinations of symbols are invalid for Bulgarian phone numbers.

Write a class PhoneBook, which models a phonebook and contains pairs (name, normal phone
number).

### PhoneBook has functions:
* Constructs new phonebook from a text file – in each row of the text document there is a
of pair of kind(name, number), where name is a random name and the number is a phone
number. When reading the text file, concern that some of the pairs may be invalid. Ignore
the invalid pairs and load only the valid ones. In PhoneBook , all phone numbers should
be saved in normalized form.
* Add a new pair
* Delete a pair by name
* Access to phone number by name
* Print all pairs sorted by name

By realizing the class , take into account that the most used operation will be Print.
Realize additional functionality , which holds the number of outgoing calls from phone book. In
effective way must print on the screen, sorted five pairs from phonebook, which are registered
with the biggest count of registered outgoing calls.

### Bonus task: write unit tests for the implemented functionalities.
