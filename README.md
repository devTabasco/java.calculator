# java.calculator

This project is a command line calculator, which is my first project while studying Java.

We used the following knowledge:

1. Array
2. Class
3. Conditional statements (if & switch-case)
4. StringBuffer
5. Create method
6. MVC pattern
7. Other basic grammar

Among them, this project aimed to learn the concepts of Array and Class (especially, distinguishing the backend from the frontend), so I tried to utilize the concept even if it was inefficient overall.

Also, for cleaner code, I've used the following ideas:

1. Synchronize the menu array configuration and screen configuration

Using the menu configuration, which is a two-dimensional array, when the user clicks 0, it moves to the previous row, and when the user clicks a number other than 0, it moves to the next row. In other words, it synchronizes the row of the menu array and the process of the screen the user sees.

Also, index 0 of each row consists of menu titles, and the last index consists of number 0 button that moves all to the previous row.

String[][] menu = { 
				{ "EXIT", "Exit the program.~" }, //row 0
				{ "MAIN", "To calculate", "Exit" }, //row 1 : Click 0 > next row
				{ "CALCULATE", "New calculate", "Then calculate", "Previous screen" } // row 2
        };

2. ...
