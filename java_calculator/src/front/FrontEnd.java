package front;

import java.util.Scanner;

import back.BackEnd; 

public class FrontEnd { 

	public FrontEnd(int rowIndex) {
		this.controller(rowIndex);
	}

	private void controller(int rowIndex) {
		Scanner scanner = new Scanner(System.in);
		String title = this.makeTitle();
		String[][] menu = { 
				{ "EXIT", "Exit the program.~" }, 
				{ "MAIN", "To calculate", "Exit" },
				{ "CALCULATE", "New calculate", "Then calculate", "Previous screen" } };
		String userData = new String();
		String message = new String();
		int selectMenu;
		boolean loopCheck = true;

		while (true) {
			this.display(title);
			if (message != null)
				this.display(message);
			this.display(this.makeSubMenu(menu[rowIndex]));
			if (!loopCheck)
				break;

			userData = this.userInput(scanner);

			if (this.isInteger(userData)) {
				selectMenu = this.convertToInteger(userData);
				if (this.isIntegerRange(selectMenu, 0, menu[rowIndex].length - 2)) {
					message = null;
					rowIndex += (selectMenu == 0) ? -1 : 1;

					if (rowIndex == 0) {
						loopCheck = false;
					} else if (rowIndex >= 3) {
						rowIndex = 2;
						rowIndex = this.operator(title, menu[rowIndex], scanner);
					}
				} else {
					message = "[ 0~" + (menu[rowIndex].length - 2) + " Please enter a number within the range]\n\n";
				}
			} else {
				message = "[ Please enter a number ]\n\n";
			}
		}
		scanner.close();
	}

	private String makeTitle() {
		StringBuffer title = new StringBuffer();
		title.append("****************************************************\n\n");
		title.append("	    	Calculator  v1.0\n");
		title.append("		           Designed By Changyong\n\n");
		title.append("****************************************************\n\n");
		return title.toString();
	}

	//To make SubMenu
	//A method to create a submenu by receiving a 1D array of the selected menu according to the change of rowIndex.
	private String makeSubMenu(String[] menu) {
		StringBuffer subMenu = new StringBuffer();
		subMenu.append("  [ " + menu[0] + " ]");
		subMenu.append(" _____________________________________");
		for (int underBar = 0; underBar <= 4 - menu[0].length(); underBar++) {
			subMenu.append("_");
		}
		subMenu.append("\n\n");
		if (menu.length <= 2) {
			subMenu.append("    " + menu[menu.length - 1] + "  \n");
			subMenu.append("  ________________________________________________\n");
		} else {
			for (int colIdx = 1; colIdx < menu.length; colIdx++) {
				if (colIdx == menu.length - 1) {
					subMenu.append("0. " + menu[colIdx] + "  \n");
				} else {
					subMenu.append("    " + colIdx + ". " + menu[colIdx] + "     ");
				}
			}
			subMenu.append("  __________________________________ select : ");
		}
		return subMenu.toString();
	}

	//using the Scanner
	private String userInput(Scanner scanner) {
		return scanner.next();
	}

	//To check integer conversion
	private boolean isInteger(String value) {
		boolean isResult = true;
		try {
			Integer.parseInt(value);
		} catch (Exception e) {
			isResult = false;
			// e.printStackTrace();
		}
		return isResult;
	}

	//Type Casting String to integer
	private int convertToInteger(String value) {
		return Integer.parseInt(value);
	}

	// Check the range of the integer
	private boolean isIntegerRange(int value, int starting, int last) {
		return (value >= starting && value <= last) ? true : false;
	}

	// Print method
	private void display(String text) {
		System.out.print(text);
	}

	// Operator Controller
	private int operator(String title, String[] menu, Scanner scanner) {
		BackEnd backend = new BackEnd();
		int[] data = new int[4];
		String[] subTitle = { "Please enter a number", "Please select an operator", "Please enter a number", "Calculation result" };
		String temp = new String(), message = new String();
		boolean loopCheck = true, isFormula = false, run = true, subMenu = true;

		int step = 12;

		while (run) {
			while (loopCheck) {
				this.display(title);

				switch (step) {
				case 12:
				case 22:
				case 32:
				case 42:
					this.display(subTitle[(step / 10) - 1]+ "\n");
					if (message != null) {
						this.display(message + "\n");
						message = null;
					}

					if (isFormula) {
						if (step != 42)
							this.display(" [Expression] ");
						for (int dataIdx = 0; dataIdx < data.length; dataIdx++) {
							if (data[dataIdx] != 0) {
								this.display(
										" " + (dataIdx == 1 ? this.convertToOperator(data[dataIdx]) : data[dataIdx]));
							} else {
								break;
							}
						}
					}

					step++;
				case 13:
				case 23:
				case 33:
				case 43:
					isFormula = true;
					this.display(step == 23 ? "\n[Select : 1. Plus  2. Minus   3. Product   4. divide] : " : "");
					if (step != 43) {
						temp = this.userInput(scanner);
						if (this.isInteger(temp)) {
							data[(step / 10) - 1] = this.convertToInteger(temp);
							if (step == 23) {
								if (!this.isIntegerRange(data[(step / 10) - 1], 1, 4)) {
									message = "Please select from 1 to 4\n";
									data[(step / 10) - 1] = 0;
									step -= 1;
									break;
								}
							}
						} else {
							message = "Please enter an integer\n";
							step -= 1;
							break;
						}
					} else {
						if (data[1] == 1) {
							data[3] = (int) backend.plus(data[0], data[2]);
						} else if (data[1] == 2) {
							data[3] = (int) backend.minus(data[0], data[2]);
						} else if (data[1] == 3) {
							data[3] = (int) backend.multiple(data[0], data[2]);
						} else {
							data[3] = (int) backend.divide(data[0], data[2]);
						}
					}
					if (step != 43)
						step += 9;
					else
						loopCheck = false;
				}

			}
			this.display(" = " + data[3]);
			this.display("\n\n");
			subMenu = true;
			while (subMenu) {
				this.display(this.makeSubMenu(menu));
				if (this.isInteger(temp = this.userInput(scanner))) {
					int selectNumber = this.convertToInteger(temp);
					if (this.isIntegerRange(selectNumber, 0, menu.length - 2)) {
						subMenu = false;
						if (selectNumber == 1) {
							for (int dataIdx = 0; dataIdx < data.length; dataIdx++) {
								data[dataIdx] = 0;
							}
							isFormula = false;
							step = 12;
							loopCheck = true;
						} else if (selectNumber == 2) {
							data[0] = data[3];
							for (int dataIdx = 1; dataIdx < data.length; dataIdx++) {
								data[dataIdx] = 0;
							}
							step = 22;
							loopCheck = true;
						} else {
							run = false;
						}
					} else {
						this.display("\nPlease enter a number from 0 to 2\n");
						
					}
				} else {
					this.display("\nPlease enter an integer\n");
				}
			}
		}
		return 1;
	}

	// Converts the selected operation number to a operation character and returns it
	private String convertToOperator(int operatorNumber) {
		String operator = new String();
		switch (operatorNumber) {
		case 1:
			operator = "＋";
			break;
		case 2:
			operator = "－";
			break;
		case 3:
			operator = "×";
			break;
		default:
			operator = "÷";
		}
		return operator;
	}

}
