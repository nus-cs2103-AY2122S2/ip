package duke.commands;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Find the tasks that have matching keywords.
 */
public class Find {
    public static String LINE_BREAK = "---------------";
    public static String FIND_INTRO = "Here are the matching tasks in your list:";

    /**
     * Constructor to instantiate Find.
     */
    public Find() {
    }

    /**
     * Prints out the list of tasks that have matching keywords from
     * findRelevantTasks.
     * 
     * @param tasks
     */
    private void printResults(ArrayList<Task> tasks) {
        System.out.println(LINE_BREAK);
        System.out.println(" " + FIND_INTRO);
        int internalCounter = 1;
        // iterate through the list
        for (Task task : tasks) {
            if (task != null) {
                System.out.println(" " + internalCounter + ". " + task);
                ++internalCounter;
            } else {
                break;
            }
        }
        System.out.println(LINE_BREAK);
    }

    /**
     * Returns tasks that contain the given keyword.
     * 
     * @param keyword input word to search for
     * @param tasks   ArrayList that we will search through for matching keywords
     */
    public void findRelevantTasks(String keyword, ArrayList<Task> tasks) {

        // result arrayList
        ArrayList<Task> result = new ArrayList<Task>();

        // will have a for loop to iterate through the tasks
        for (Task t : tasks) {
            if (t.getDescription().contains(keyword)) {
                result.add(t);
            }
        }
        // method to print the matching results
        printResults(result);
    }
}
