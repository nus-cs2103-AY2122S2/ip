package duke;

import java.util.ArrayList;

/**
 * Represents an Action
 */
public class Action {

    Action() {}

    /**
     * greets the user
     */
    public static void greet() {
        String logo  = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = "Hello! I'm JiaMing aka\n"+ logo + "\nWhat can I do for you?\nTip: use 'help' for help\n";
        System.out.println(greet);
    }

    /**
     * prints out the input by the user.
     *
     * @param phrase The input by the user
     */
    public static void echo(String phrase) {
        System.out.println(phrase);
    }

    /**
     * farewell to user.
     */
    public static void bye() {
        String bye = "Bye. Hope to see you again soon!\n";
        System.out.println(bye);
    }

    /**
     * Prints out the list of tasks
     *
     * @param arrlst List of tasks
     */
    public static void showList(ArrayList<Task> arrlst) {
        System.out.println("Here are the tasks in your list:");
        for (int i =  0; i < arrlst.size(); i++) {
            String output = String.format("%d.%s\n", i + 1, arrlst.get(i));
            System.out.println(output);
        }
    }
}
