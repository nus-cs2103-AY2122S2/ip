package ui;

import exception.DukeException;

import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void outputMessage(String message) {
        System.out.println("____________________________________________________________\n"
                + message + "\n____________________________________________________________");
    }

    public static void outputError(String errorMessage) {
        System.out.println("____________________________________________________________\n"
                + "ERROR!!! " + errorMessage + "\n____________________________________________________________");
    }

    public void sayHello() {
        outputMessage("Hello! I'm Duke\nWhat can I do for you?");
    }

    public String getCommand() {
        return sc.nextLine(); //figure out how to return split version
        // make a Command interface/class too later!
    }

    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

    public void exit() {
        outputMessage("Bye. Hope to see you again soon!");
        sc.close();
    }
}
