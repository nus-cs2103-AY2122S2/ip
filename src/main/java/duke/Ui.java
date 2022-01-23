package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    void showLoadingError() {
        System.out.println("No backup file found.");
    }

    void greet() {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
    }

    void print(String response) {
        System.out.println(response);
    }

    String readCommand() {
        return this.scanner.nextLine();
    }
}
