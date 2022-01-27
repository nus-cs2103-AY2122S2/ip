package Duke;

import java.util.Scanner;

public class Ui {
	Scanner sc;

	Ui() {
		sc = new Scanner(System.in);
	}

	public String readCommand() {
		String command = sc.nextLine();
		return command;
	}

	public void welcome() {
		System.out.println("\t____________________________________________________________");
		System.out.println("\t Woof! I'm Wonka!\n\t How may I be of service?");
		System.out.println("\t____________________________________________________________");
	}

	public void showLine() {
		System.out.println("\t____________________________________________________________");
	}

	public void showExitMessage() {
		System.out.println("\t Woof woof! Hope to see you again soon!");
	}

	public void close() {
		sc.close();
	}
}
