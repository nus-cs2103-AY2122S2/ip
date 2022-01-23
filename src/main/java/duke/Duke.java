package duke;

import java.util.Scanner;

import duke.handler.FileHandler;
import duke.handler.Handlers;
import duke.task.Tasklist;

/**
 * Our very own task manager, Duke!
 */
public class Duke {

    /**
     * Main function to execute the application.
     *
     * @param args Arguments, if any, to be passed for execution.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm Duke!");
        System.out.println("What can I do for you?\n");

        Tasklist list = new Tasklist();
        Scanner scn = new Scanner(System.in);
        FileHandler.readFromFile(list);
        System.out.println(list.toString());
        Handlers.commandHandler(list, scn);
    }
}
