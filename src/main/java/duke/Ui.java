package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {

    public void showLine() {
        System.out.println("__________________________________________________");
    }

    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void showLoadingError() {
        System.out.println("No pre-existing data found!");
    }

    public String readCommand() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
