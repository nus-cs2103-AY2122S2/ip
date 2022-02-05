package duke.command;

import java.util.Scanner;


/**
 * Represents the UI aspect of Duke.
 *
 * @author Jian Rong
 */
public class Ui {

    /**
     * Prints Duke logo
     *
     */
    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("__________________________________");
        System.out.println("Hello! I'm Duke\nWhat can I do for you");
        System.out.println("__________________________________");
    }

    /**
     * Asks for user input.
     *
     * @param taskList Is the list of tasks
     * @return boolean False if user says "bye", true otherwise.
     */

    public static boolean run(TaskList taskList) {
        Scanner dukeScan = new Scanner(System.in);
        String userInput = dukeScan.nextLine();
        return Parser.parseInput(userInput, taskList);
    }


}


