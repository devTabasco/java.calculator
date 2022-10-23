# java.calculator

This project is a command line calculator, which is my first project while studying Java.

I used the following knowledge:

1. Array
2. Class
3. Conditional statements (if & switch-case)
4. StringBuffer
5. Create method
6. MVC pattern
7. Other basic grammar

Among them, this project aimed to learn the concepts of Array and Class (especially, distinguishing the backend from the frontend), so I tried to utilize these concepts even if it was inefficient overall.

Also, for clean code, I've used the following ideas:

1. Synchronize the menu array configuration and screen configuration

Using the menu configuration, which is a two-dimensional array, when the user clicks 0, it moves to the next row, and when the user clicks a number other than 0, it moves to the previous row. In other words, it synchronizes the row of the menu array and the process of the screen the user sees.

Also, index 0 of each row consists of menu titles, and the last index consists of number 0 button that moves all to the previous row.

~~~java
String[][] menu = {
		{ "EXIT", "Exit the program.~" }, //row 0
		{ "MAIN", "To calculate", "Exit" }, //row 1 : Click 0 > previous row
		{ "CALCULATE", "New calculate", "Then calculate", "Previous screen" } // row 2
       		};
~~~


2. Flattening the code that receives numbers and operators for calculations

The calculation requires 2 numbers and 1 operator.
I use a size 4 one-dimensional array (int[] data) to input 2 numbers and 1 operator to the user.
And the last index of the array stores the result of the calculation.

While receiving the information from the user 3 times, I write all the codes in the order of title - subtitle - userinput .
Also, when showing the operation result, the result is shown after providing the title - subtitle.

So I was able to pattern a total of 4 cycles of title - subtitle - userinput(or show result).

This can be expressed graphically as follows.

~~~
	1		2		3

1	title		subtitle	userInput(data[0])


2	title		subtitle	userInput(data[1])


3	title		subtitle	userInput(data[2])


4	title		subtitle	show result(data[3])
~~~

Using this matrix structure, a total of 12 cases can be structured using the number of rows and columns.
(For example, the task of outputting subtitles to receive information of data[1] becomes case22.)

Using this structure, case11 to case43 can be distinguished.

However, if you think about it a little more, we can repeat the same code without dividing the case because all titles output the same content.

In conclusion, when distinguishing from case 21 to case 43,
I designed the code work by dividing case21 ~ case 24 into 1 bundle and case31 ~ case43 into 1 bundle.

Using this structure, the operator method work was completed.

Regarding the current exception handling,

1. Whether the input data being input is a number or not
2. Whether to enter a number within the selection range or not

I handled the above exceptions.

yet,

1. Exception when dividing by zero
2. so that the decimal point is output

I did not deal with the above issues.

If you have any other problems,
Please send an email to the address below.

changyong0605@gmail.com
