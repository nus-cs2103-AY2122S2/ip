package duke;
import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    ArrayList<Task> tasks;

    /** Instantiates an empty task list */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /** Instantiates a list of tasks */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the TaskList.
     * 
     * @param task The task to be added.
     * @return A success message.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %s task(s) in the list",
                task, tasks.size());
    }

    /**
     * Deletes a task from the TaskList.
     * 
     * @param itemNumber The index of the item in the list (1-based index).
     * @return A success message for the user.
     */
    public String deleteItem(int itemNumber) {
        Task task = tasks.remove(itemNumber - 1);
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %s task(s) in the list",
                task, tasks.size());
    }

    /**
     * Marks a task in the TaskList as complete.
     * 
     * @param itemNumber The index of the item in the list (1-based index).
     * @return A success message for the user.
     */
    public String markItem(int itemNumber) {
        Task task = tasks.get(itemNumber - 1);
        task.markAsDone();
        return "Nice! I've marked this as done:\n  " + task;
    }

    /**
     * Marks a task in the TaskList as incomplete.
     * 
     * @param itemNumber The index of the item in the list (1-based index).
     * @return A success message for the user.
     */
    public String unmarkItem(int itemNumber) {
        Task task = tasks.get(itemNumber - 1);
        task.unmarkAsDone();
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Checks if a given number points an existing item in the TaskList.
     * 
     * @param itemNum The index of the item in the list (1-based index).
     * @return boolean indicating whether item is valid or not.
     */
    public boolean isValidItemNumber(int itemNum) {
        return itemNum > 0 && itemNum <= tasks.size();
    }

    /**
     * Returns the number of tasks in the list.
     * 
     * @return Size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Finds the list of tasks that contains the given keyword and lists them out.
     * 
     * @param searchText The keyword to search for.
     * @return A list of tasks
     */
    public String find(String searchText) {
        TaskList matchedTasks = new TaskList();
        
        for(Task task : tasks) {
            if (task.toFileFormat().contains(searchText)) {
                matchedTasks.addTask(task);
            }
        }

        String message = "Here are the matching tasks in your list:\n";

        return Ui.mergeMessages(message, matchedTasks.listItems());
    }

    /**
     * Loops through the list and lists out all items in the list.
     * 
     * @return A string representation of all items in the list.
     */
    public String listItems() {
        StringBuilder sb = new StringBuilder();
        if (tasks.isEmpty()) {
            sb.append("There is nothing in the list!");
        }
        
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(i + ". ").append(tasks.get(i - 1)).append("\n");
        }

        return sb.toString();
    }

    
}
