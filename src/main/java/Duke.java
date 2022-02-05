package duke.duke;

import duke.ui.DukeException;
import duke.ui.InputHandler;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String dukeGreeting = "Hello! I'm Duke \nWhat can I do for you?";
        String endMessage = "Bye. Hope to see you again soon!";

        System.out.println(dukeGreeting);
        Scanner sc = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler();
        boolean isChatEnded = false;
        while (!isChatEnded) {
            try {
                String input = sc.nextLine();
                isChatEnded = inputHandler.handleInput(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(endMessage);
    }


}









