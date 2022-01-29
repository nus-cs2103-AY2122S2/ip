package duke.operations;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a TaskList of Duke. A <code>TaskList</code> corresponds to
 * containing the tasklist operations. e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    protected static final ArrayList<Task> TASK_ARRAY_LIST = new ArrayList<>();
    /**
     * Prints the total number of tasks.
     *
     * @param num total number of tasks.
     */
    public void totalTasks(int num) {
        if (num == 1) {
            System.out.println("     You currently have " + num + " task in your device.");
        } else {
            System.out.println("     You currently have " + num + " tasks in your device.");
        }
    }

    /**
     * Marks the respective task with a tick.
     *
     * @param num the index of the task to be marked.
     */
    public void mark(int num) {
        Ui.line();
        Task currTask = TASK_ARRAY_LIST.get(num - 1);
        currTask.mark();
        System.out.println("     The bar on the top left of your screen just increased! Keep going!");
        System.out.println("     " + currTask);
        Storage.updateTextFile();
        Ui.line();
    }

    /**
     * Unmarks the respective task with a tick.
     *
     * @param num the index of the task to be unmarked.
     */
    public void unmark(int num) {
        Ui.line();
        Task currTask = TASK_ARRAY_LIST.get(num - 1);
        currTask.unmark();
        System.out.println("     Surely you aren't the imposter... right??");
        System.out.println("     " + currTask);
        Storage.updateTextFile();
        Ui.line();
    }

    /**
     * Adds a Task to the ArrayList with print statements.
     *
     * @param task task to be added into the ArrayList.
     */
    public void addToList(Task task) {
        TASK_ARRAY_LIST.add(task);
        System.out.println("       " + task.toString());
        totalTasks(TASK_ARRAY_LIST.size());
        Storage.updateTextFile();
        Ui.line();
    }

    /**
     * Adds a Task to the ArrayList without print statements.
     *
     * @param task task to be added into the ArrayList.
     */
    public static void addToListNoPrint(Task task) {
        TASK_ARRAY_LIST.add(task);
        Storage.updateTextFile();
    }

    /**
     * Deletes a Task from the ArrayList.
     *
     * @param task task to be deleted from the ArrayList.
     */
    public void deleteFromList(Task task) {
        TASK_ARRAY_LIST.remove(task);
        Ui.line();
        System.out.println("     Hmm... kinda sus you deleted this task...");
        System.out.println("       " + task);
        totalTasks(TASK_ARRAY_LIST.size());
        Storage.updateTextFile();
        Ui.line();
    }

    /**
     * Prints all the Tasks in the ArrayList in sequential order.
     */
    public void printList() {
        Ui.line();
        System.out.println("     Here are the tasks in your device:");
        for (int i = 0; i < TASK_ARRAY_LIST.size(); i++) {
            System.out.println("     " + (i + 1) + "." + TASK_ARRAY_LIST.get(i).toString());
        }
        Ui.line();
    }

    /**
     * Checks and prints all tasks in the task arraylist if the keyword is contained in it.
     *
     * @param keyword the word to be searched for.
     */
    public void findTaskInList(String keyword) {
        Ui.line();
        int counter = 1;
        if (keyword.equals("")) {
            System.out.println("     You sussy baka! What are you trying to find?");
        }

        for (int i = 0; i < TASK_ARRAY_LIST.size(); i++) {
            if (TASK_ARRAY_LIST.get(i).getDescription().contains(keyword)) {
                System.out.println("     " + counter + ": " + TASK_ARRAY_LIST.get(i).toString());
                counter++;
            }
        }

        if (counter == 1) {
            System.out.println("     I can't understand this SUSSY language! Use the AMOGUS language!");
        }
        Ui.line();
    }
}
