package duke.command;

import java.util.Scanner;

public class Ui {

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

    public static boolean run(TaskList taskList) {
        Scanner dukeScanner = new Scanner(System.in);
        String userInput = dukeScanner.nextLine();
        return Parser.parseInput(userInput, taskList);
    }


}


