package duke;
import java.util.ArrayList;

import duke.tasks.Task;

public class TaskList {
    private ArrayList<Task> tasks;
    private static final String ADD_TASK_SUCCESS_MESSAGE = "Got it. I've added this task:\n  %s\nNow you have %s task(s) in the list";
    private static final String ADD_TASK_DUPLICATE_FAILURE_MESSAGE =
            "This task: \n %s\n is a duplicate of some other task in the list!"
            + "\nDuplicate task not added.";

    /** Instantiates an empty task list */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /** Instantiates a list of tasks */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null;
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the TaskList.
     * 
     * @param task The task to be added.
     * @return A success message.
     */
    public String addTask(Task task) {
        assert task != null;
        if (contains(task)) {
            return String.format(ADD_TASK_DUPLICATE_FAILURE_MESSAGE, task);
        }
        
        tasks.add(task);
        return String.format(ADD_TASK_SUCCESS_MESSAGE,
                task, tasks.size());
    }

    /**
     * Deletes a task from the TaskList.
     * 
     * @param itemNumber The index of the item in the list (1-based index).
     * @return A success message for the user.
     */
    public String deleteItem(int itemNumber) {
        assert itemNumber <= tasks.size() && itemNumber > 0;
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
        assert itemNumber <= tasks.size() && itemNumber > 0;
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
        assert itemNumber <= tasks.size() && itemNumber > 0;
        Task task = tasks.get(itemNumber - 1);
        task.unmarkAsDone();
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Checks if a task exists in the list or not.
     * 
     * @param task The task to be checked.
     * @return boolean indicating whether or not the task exists.
     */
    public boolean contains(Task task) {
        assert task != null;
        return tasks.contains(task);
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
     * Returns the list of tasks.
     * 
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Finds the list of tasks that contains the given keyword and lists them out.
     * 
     * @param searchText The keyword to search for.
     * @return A list of tasks
     */
    public String find(String searchText) {
        assert searchText != null;
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
