package Duke.util;

import java.util.Scanner;

/**
 * Represents a UI interface that deals with user inputs.
 */
public class Ui {
	Scanner sc;

	/**
	 * Constructor for Ui class which allows user inputs.
	 */
	public Ui() {
		sc = new Scanner(System.in);
	}

	/**
	 * Reads the user input.
	 *
	 * @return User input.
	 */
	public String readCommand() {
		String command = sc.nextLine();
		return command;
	}

	/**
	 * Prints the welcome message of the chatbot.
	 */
	public void welcome() {
		System.out.println("\t____________________________________________________________");
		System.out.println("\t Woof! I'm Wonka!\n\t How may I be of service?");
		System.out.println("\t____________________________________________________________");
	}

	/**
	 * Prints a line separator.
	 */
	public void showLine() {
		System.out.println("\t____________________________________________________________");
	}

	/**
	 * Prints exit message.
	 */
	public void showExitMessage() {
		System.out.println("\t Woof woof! Hope to see you again soon!");
	}

	/**
	 * Closes the scanner.
	 */
	public void close() {
		sc.close();
	}

	/**
	 * Prints a message to request for user to try again.
	 */
	public void tryAgain() {
		System.out.println("\t Try again.");
	}
}
