package taskmaster.util;
import taskmaster.task.Task;
import java.util.ArrayList;


/*
 * This class encapsulates task list which contains the list of tasks
 * that was added by the user.
 */

public class TaskList {
    /** Task list that contains all the tasks. **/
    private final ArrayList<Task> taskList;

    /** Size of the current task list. **/
    private int currentSize;
    
    /**
     * Constructor for taskList.
     */

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.currentSize = 0;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task in the task list
     *              to be returned.
     * @return task at the specified index in the task list.
     */

    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Adds the task into the existing taskList.
     * @param task task to be added.
     */

    public void add(Task task) {
        taskList.add(task);
        currentSize+=1;
    }

    /**
     * Marks the task in the task list at the specified index.
     * 
     * @param index task at the index to be marked.
     */

    public void mark(int index) {
        this.taskList.get(index).markTask();
    }

    /**
     * unmarks the task in the task list at the specified index.
     *
     * @param index task at the index to be unmarked.
     */

    public void unmark(int index) {
        this.taskList.get(index).unmarkTask();
    }

    /**
     * Deletes the task in the task list at the specified index.
     *
     * @param index task at the index to be deleted.
     */

    public void delete(int index) {
        this.taskList.remove(index);
        currentSize -= 1;
    }

    /**
     * Display all the tasks in the list.
     */

    public void list() {
        if (currentSize == 0) {
            System.out.println("    \nYou haven't added any task, brat!\n");
        } else {
             System.out.println("    \nHere are the tasks in your list:");
             for (int i = 0; i < currentSize; i++)
                 System.out.println("    " + (i + 1) + ". " + this.taskList.get(i));
            System.out.println("\n");
         
        }
    }

    /**
     * Returns the string format of all the tasks in save format.
     *
     * @return the string format of all the tasks in save format.
     */

    public String listTasksInTextFormat() {
        String result = "";
        for (Task task:taskList) {
            result = result.concat(task.saveToFileFormat());
            result = result.concat("\n");
        }

        return result.trim();
    }

    /**
     * Display all the Tasks that has the specified keyword
     * in the Task Description/name.
     *
     * @param strToFind keyword to be present in the task description.
     */

    public void find(String strToFind) {
        int count = 1;
        for (Task task:taskList) {
            if (task.containsKeyword(strToFind)) {
                System.out.println(count + ". " + task);
                count++;
            }
        }

        if (count == 1) {
            System.out.println("No task with that keyword\n");
        }
    }

    /**
     * Prints a message to inform user of the total number of
     * current tasks.
     */

    public void printCurrentSize() {
        System.out.println("Now you have " + this.currentSize + " tasks in the list.\n");
    }

    /**
     * Returns true is input is more than task list's size or
     * lesser than 0.
     * @param i the number to be checked against the size.
     * @return true if more than size of task list or lesser than 0
     *         False if it's lesser than size of task list.
     */

    public boolean isNumberOutOfRange(int i) {
        return (i < 0 || i > currentSize);
    }

    /**
     * Returns true or false depending on whether
     * the task list is empty.
     * @return true if empty, false otherwise.
     */

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Delete all tasks in the task list.
     * For testing purposes.
     */

    public void deleteAllTasks() {
        taskList.clear();
    }
}