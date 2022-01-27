package ann.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showFailedInitMessage() {
        System.out.println("Failed to initialise Ann :( Exiting...");
    }

    public void showGreeting() {
        System.out.println("Greetings from Ann");
    }

    public void showExitMessage() {
        System.out.println("Goodbye!");
    }

    public String getCommand() {
        System.out.println("Please enter a command:");
        return scanner.nextLine();
    }

    public void showToUser(String message) {
        System.out.println(message);
    }
}