package duke.commands;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Find the tasks that have matching keywords.
 */
public class Find extends Commands {

    /**
     * Constructor to instantiate Find.
     */
    public Find(ArrayList<Task> tasks) {
        super(tasks);
    }

    /**
     * Prints out the list of tasks that have matching keywords from
     * findRelevantTasks.
     * @param tasks
     */
    private String printResults(ArrayList<Task> tasks) {
        String res = "";
        res += super.LINE_BREAK + "\n";
        res += " " + super.FIND_INTRO + "\n";

        int internalCounter = 1;
        // iterate through the list
        for (Task task : tasks) {
            if (task != null) {
                res += " " + internalCounter + ". " + task + "\n";
                ++internalCounter;
            } else {
                break;
            }
        }
        res += super.LINE_BREAK;
        return res;
    }

    /**
     * Returns tasks that contain the given keyword.
     * @param keyword input word to search for
     * @param tasks   ArrayList that we will search through for matching keywords
     * @return
     */
    public String findRelevantTasks(String keyword, ArrayList<Task> tasks) {

        // result arrayList
        ArrayList<Task> result = new ArrayList<Task>();

        // will have a for loop to iterate through the tasks
        for (Task t : tasks) {
            if (t.getDescription().contains(keyword)) {
                result.add(t);
            }
        }
        // method to print the matching results
        return printResults(result);
    }
}
