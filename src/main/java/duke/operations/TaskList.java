package duke.operations;

import java.util.ArrayList;

import duke.task.Tag;
import duke.task.Task;

/**
 * Represents a TaskList of Duke. A <code>TaskList</code> corresponds to
 * containing the tasklist operations. e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    protected static final ArrayList<Task> TASK_ARRAY_LIST = new ArrayList<>();

    public int getTaskArrayListSize() {
        return TASK_ARRAY_LIST.size();
    }

    /**
     * Prints the total number of tasks.
     *
     * @param num total number of tasks.
     * @return the String to be printed.
     */
    public String totalTasks(int num) {
        if (num == 1) {
            return "You currently have " + num + " task in your device.";
        } else {
            return "You currently have " + num + " tasks in your device.";
        }
    }

    /**
     * Marks the respective task with a tick.
     *
     * @param num the index of the task to be marked.
     */
    public void mark(int num) {
        Task currTask = TASK_ARRAY_LIST.get(num - 1);
        currTask.mark();
    }

    /**
     * Unmarks the respective task with a tick.
     *
     * @param num the index of the task to be unmarked.
     */
    public void unmark(int num) {
        Task currTask = TASK_ARRAY_LIST.get(num - 1);
        currTask.unmark();
    }

    /**
     * Adds a Task to the ArrayList with print statements.
     *
     * @param task task to be added into the ArrayList.
     */
    public void addToList(Task task) {
        TASK_ARRAY_LIST.add(task);
    }

    /**
     * Adds a Task to the ArrayList without print statements.
     *
     * @param task task to be added into the ArrayList.
     */
    public static void addToListNoPrint(Task task) {
        TASK_ARRAY_LIST.add(task);
    }

    /**
     * Deletes a Task from the ArrayList.
     *
     * @param task task to be deleted from the ArrayList.
     */
    public void deleteFromList(Task task) {
        TASK_ARRAY_LIST.remove(task);
    }

    /**
     * Prints all the Tasks in the ArrayList in sequential order.
     *
     * @return the list of tasks to be printed.
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < TASK_ARRAY_LIST.size(); i++) {
            if (TASK_ARRAY_LIST.get(i).getTag() == null) {
                output += (i + 1) + ". "
                        + TASK_ARRAY_LIST.get(i).toString() + " " + "\n   ";
            } else {
                output += (i + 1) + ". "
                        + TASK_ARRAY_LIST.get(i).toString() + " "
                        + "#"
                        + TASK_ARRAY_LIST.get(i).getTag() + "\n   ";
            }
        }
        return output;
    }

    /**
     * Checks and prints all tasks in the task arraylist if the keyword is contained in it.
     *
     * @param keyword the word to be searched for.
     * @return the list of tasks with the keyword contained in task.
     */
    public String findTaskInList(String keyword) {
        int counter = 1;
        String emptyMessage = "";
        if (keyword.equals("")) {
            emptyMessage = "You sussy baka! What are you trying to find?\n   ";
        }

        String output = "";
        for (int i = 0; i < TASK_ARRAY_LIST.size(); i++) {
            if (TASK_ARRAY_LIST.get(i).getDescription().contains(keyword)) {
                output += "   " + counter + ": " + TASK_ARRAY_LIST.get(i).toString() + "\n";
                counter++;
            }
        }
        String unknownMessage = "";
        if (counter == 1) {
            unknownMessage = "I can't understand this SUSSY language! Use the AMOGUS language!\n   ";
        }

        return emptyMessage + unknownMessage + output;
    }

    /**
     * Tags a task in the task arraylist with a String
     *
     * @param index the index of the task array to be tagged.
     * @param str the description of the tag to be added.
     * @return a String representation of the tagged task.
     */
    public String tagInList(int index, String str) {
        Tag tag = new Tag(str);
        TASK_ARRAY_LIST.get(index).setTag(tag);
        return "Task number " + (index + 1) + " has been tagged with #" + str;
    }

    /**
     * Untags a task in the task arraylist with an index
     *
     * @param index the index of the task array to be untagged.
     * @return a String representation of the untagged task.
     */
    public String untagInList(int index) {
        TASK_ARRAY_LIST.get(index).setTag(null);
        return "Task number " + (index + 1) + " has been untagged";
    }
}
