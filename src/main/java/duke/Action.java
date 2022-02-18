package duke;

import java.util.ArrayList;

/**
 * Represents an Action
 */
public class Action {

    Action() {}

    /**
     * Greets the user
     * @return greeting
     */
    public static String greet() {
        String logo = "---------------------------------------------------\n"
                + "P P P P       A        N      N    D D D         A\n"
                + "P P P P    A A A    N  N  N    D      D    A A A\n"
                + "P           A      A    N       N    D D D    A      A\n"
                + "---------------------------------------------------";
        String greeting = "Hello! I am PinkPandaBot aka\n" + logo
                            + "\nWelcome to my ChatBot\nTip: use 'help' for help\n";
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
