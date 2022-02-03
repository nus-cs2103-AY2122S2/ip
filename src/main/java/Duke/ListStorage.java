package Duke;

import java.util.ArrayList;
import java.util.List;

public class ListStorage {
    /** Single List to store Tasks */
    private List<Task> myTasks;
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String SPACING = "    ";

    /**
     * Constructor for ListStorage
     */
    public ListStorage() {
        myTasks = new ArrayList<Task>();
    }

    /**
     * Returns length of ListStorage.
     *
     * @return number of Tasks.
     */
    public int length() {
        return myTasks.size();
    }

    /**
     * Returns list of tasks.
     * @return List of tasks.
     */
    public List<Task> getMyTasks() {
        return this.myTasks;
    }
    /**
     * Adds a Task to storage.
     *
     * @param task task to be added.
     * @return message to indicate success.
     */
    public String addToList(Task task) {
        myTasks.add(task);
        return "added: " + task.description;
    }

    /**
     * Prints a list of tasks in storage.
     *
     * @return list of tasks in storage.
     */
    public String printList() {
        int i = 1;
        StringBuilder toPrint = new StringBuilder();
        for (Task item : myTasks) {
            toPrint.append(SPACING)
                    .append(i)
                    .append(".")
                    .append(item.toString())
                    .append("\n");
            i++;
        }
        return toPrint.toString();
    }

    /**
     * Prints a specific task with description.
     *
     * @param taskNumber task number with reference to list.
     * @return description of task.
     */
    public String printTask(int taskNumber) {
        Task currentTask = myTasks.get(taskNumber - 1);
        return SPACING
                + currentTask.toString()
                + "\n";
    }

    /**
     * Finds a specific task in storage.
     *
     * @param taskNumber task number with reference to list.
     * @return a specified task in list.
     */
    public Task findTask(int taskNumber) {
        return myTasks.get(taskNumber - 1);
    }

    /**
     * Deletes a task in storage
     *
     * @param taskNumber task number with reference to list.
     */
    public void deleteTask(int taskNumber) {
        myTasks.remove(taskNumber - 1);
    }

}
