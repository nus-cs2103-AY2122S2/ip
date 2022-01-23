package duke.operations;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a TaskList of Duke. A <code>TaskList</code> corresponds to
 * containing the tasklist operations. e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    protected static final ArrayList<Task> taskArr = new ArrayList<>();
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
        Task currTask = taskArr.get(num - 1);
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
        Task currTask = taskArr.get(num - 1);
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
        taskArr.add(task);
        System.out.println("       " + task.toString());
        totalTasks(taskArr.size());
        Storage.updateTextFile();
        Ui.line();
    }

    /**
     * Adds a Task to the ArrayList without print statements.
     *
     * @param task task to be added into the ArrayList.
     */
    public static void addToListNoPrint(Task task) {
        taskArr.add(task);
        Storage.updateTextFile();
    }

    /**
     * Deletes a Task from the ArrayList.
     *
     * @param task task to be deleted from the ArrayList.
     */
    public void deleteFromList(Task task) {
        taskArr.remove(task);
        Ui.line();
        System.out.println("     Hmm... kinda sus you deleted this task...");
        System.out.println("       " + task);
        totalTasks(taskArr.size());
        Storage.updateTextFile();
        Ui.line();
    }

    /**
     * Prints all the Tasks in the ArrayList in sequential order.
     */
    public void printList() {
        Ui.line();
        System.out.println("     Here are the tasks in your device:");
        for (int i = 0; i < taskArr.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskArr.get(i).toString());
        }
        Ui.line();
    }

    public void findTaskInList(String keyword) {
        Ui.line();
        int counter = 1;
        for (int i = 0; i < taskArr.size(); i++) {
            if(taskArr.get(i).getDescription().contains(keyword)) {
                System.out.println("     " + counter + ": " + taskArr.get(i).toString());
                counter++;
            }
        }
        if(counter == 1) {
            System.out.println("     I can't understand this SUSSY language! Please use proper english!");
        }
        Ui.line();
    }
}
