package duke;

import java.util.ArrayList;

/**
 * TaskList class that contains an ArrayList of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class, initialises a new ArrayList
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * prints out the list
     */
    public void printList() {
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            System.out.print(i + ": ");
            task.printTask();
        }
    }

    public String guiPrintList() {
        String output = "";
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            System.out.print(i + ": ");
            output += i + ": ";
            output += task.toString() + "\n";
        }
        return output;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Clears the list of tasks
     */
    public void reset() {
        tasks.clear();
    }

    /**
     * Takes in a task to be added
     *
     * @param task a task from user
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. The task has been added:");
        task.printTask();
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public String guiAddTask(Task task) {
        String output = "";
        tasks.add(task);
        output += "Got it. The task has been added:\n";
        output += task.toString() + "\n";
        output += "Now you have " + tasks.size() + " tasks in the list.";
        return output;
    }

    /**
     * Takes in a task to be added and adds it to the ArrayList, without the print statements
     * Used when loading from data.txt
     *
     * @param task a task from data.txt
     */
    public void addTaskNoPrint(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the ArrayList at a specific index
     *
     * @param index index of the task to be deleted (1-based indexing)
     */
    public void deleteTask(int index) {
        if (index > tasks.size() || index <= 0) {
            System.out.println("Index out of bounds, please try again");
        } else {
            tasks.remove(index - 1);
            printList();
        }
    }

    public String guiDeleteTask(int index) {
        if (index > tasks.size() || index <= 0) {
            return "Index out of bounds, please try again";
        } else {
            tasks.remove(index - 1);
            return guiPrintList();
        }
    }

    /**
     * Marks a task from the ArrayList as done
     *
     * @param index index of the task to be marked as done (1-based indexing)
     */
    public void markTask(int index) {
        if (index > tasks.size() || index <= 0) {
            System.out.println("Index out of bounds, please try again");
        } else {
            tasks.get(index - 1).setDone(true);
            printList();
        }
    }

    public String guiMarkTask(int index) {
        if (index > tasks.size() || index <= 0) {
            return "Index out of bounds, please try again";
        } else {
            tasks.get(index - 1).setDone(true);
            return guiPrintList();
        }
    }

    /**
     * Marks a task from the ArrayList as not done
     *
     * @param index index of the task to be marked as not done (1-based indexing)
     */
    public void unmarkTask(int index) {
        if (index > tasks.size() || index <= 0) {
            System.out.println("Index out of bounds, please try again");
        } else {
            tasks.get(index - 1).setDone(false);
            printList();
        }
    }

    public String guiUnmarkTask(int index) {
        if (index > tasks.size() || index <= 0) {
            return "Index out of bounds, please try again";
        } else {
            tasks.get(index - 1).setDone(false);
            return guiPrintList();
        }
    }

    /**
     * find any matching keyword(s) in text to any tasks in the taskList and print them out
     *
     * @param text keyword(s) to find
     * @throws DukeException if there are no matches for the keyword
     */
    public void find(String text) throws DukeException {
        ArrayList<String> toPrint = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskName = task.getTaskName().toLowerCase();
            if (taskName.contains(text.toLowerCase())) {
                String result = "Index in taskList: " + (i + 1) + " || Task Details: " + task.toString();
                toPrint.add(result);
            }
        }
        if (toPrint.size() == 0) {
            throw new DukeException("no matches found for this keyword(s)");
        } else {
            System.out.println("Here are the matching tasks in your list: ");
            for (String s: toPrint) {
                System.out.println(s);
            }
        }
    }

    public String guiFind(String text) throws DukeException {
        String output = "";
        ArrayList<String> toPrint = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskName = task.getTaskName().toLowerCase();
            if (taskName.contains(text.toLowerCase())) {
                String result = "Index in taskList: " + (i + 1) + " || Task Details: " + task.toString();
                toPrint.add(result);
            }
        }
        if (toPrint.size() == 0) {
            throw new DukeException("no matches found for this keyword(s)");
        } else {
            output = String.join("\n", toPrint);
            output = "Here are the matching tasks in your list: \n" + output;
        }
        return output;
    }
}
