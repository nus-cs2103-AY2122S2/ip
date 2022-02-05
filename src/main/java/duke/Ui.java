package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {

    public Ui() {}

    public void showLine() {
        System.out.println("------------------------------------------------------------------------");
    }

    public void showWelcome() {
        this.showLine();
        System.out.println("Hi! I'm Duke.Duke\nWhat can I do for you?");
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
        this.showLine();
    }

    public void showLeadingSymbol() {
        System.out.print("> ");
    }

    public String readCommand() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
}
