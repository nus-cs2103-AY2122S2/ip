package doge;

import doge.command.Command;

import java.util.Scanner;

public class Ui {

    public Ui() {

    }

    public void showLine() {
        System.out.println("--------------------------------------------------------------------");
    }

    public void greet() {
        System.out.println("Doge: Oh it's you again...");
        System.out.println("Doge: " + "What kind of trouble would you " +
                "inconvenience me with this time?");
    }

    public void bye() {
        System.out.println("Doge: Please don't ever bother me again, bye");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showError(String message) {
        System.out.println("<ERROR> " + message);
    }

    public void respond(Command c) {
        System.out.println(c);
        if (c.haveTask()) {
            System.out.println(c.getTask());
        }
    }
}
