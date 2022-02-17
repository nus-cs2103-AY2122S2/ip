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
    public static String greet() {
        String logo = "DUKE";
        String greeting = "Hello! I'm JiaMing aka\n" + logo + "\nWhat can I do for you?\nTip: use 'help' for help\n";
        System.out.println(greeting);
        return greeting;
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
    public static String bye() {
        String bye = "Bye. Hope to see you again soon!\n";
        System.out.println(bye);
        return bye;
    }

    /**
     * Prints out the list of tasks
     *
     * @param arrlst List of tasks
     */
    public static String showList(ArrayList<Task> arrlst) {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < arrlst.size(); i++) {
            output = output + String.format("%d.%s\n", i + 1, arrlst.get(i));
            System.out.println(output);
        }
        return output;
    }

    /**
     *  Find keyword in every task inside task bank.
     * @param keyword The word that is being searched.
     */
    public static String find(String keyword) {
        boolean hasMatches = false;
        String output = "";
        if (keyword.equals("")) {
            output = "find description cannot be empty!\n";
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < TaskBank.getSize(); i++) {
                Task t = TaskBank.getBank().get(i);
                if (t.getDescription().contains(keyword)) {
                    hasMatches = true;
                    output += String.format("%d. %s\n", i + 1, t);
                }
            }
        }

        if (hasMatches) {
            System.out.println(output);
            return output;
        } else {
            System.out.println("no results found");
            return output;
        }
    }

}
