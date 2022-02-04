package duke.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import duke.exception.InvalidIndexException;
import duke.task.Task;

/**
 * The class which contains the array list of tasks and functions that can be done to modify it.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList.
     * @param taskList an ArrayList which is abstracted into the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task into the task list.
     * @param task the input task which will be added into the task list.
     * @return the String which will be prints out the task that has been added and the current tasks in the list.
     */
    public String addTask(Task task) {
        this.taskList.add(task);
        return "Got it. I've added this task\n" + task.printTask() + "\n" + "Now you've got " + taskList.size()
                + " tasks in the list.";
    }

    /**
     * Deletes a task from the task list.
     * @param index the index of the task to be deleted.
     * @return the String to be printed.
     * @throws InvalidIndexException if the index is out of bounds.
     */
    public String deleteTask(int index) throws InvalidIndexException {
        Task deletedTask;
        try {
            deletedTask = this.taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("1 to " + taskList.size() + " inclusive");
        }
        return "Noted. I've removed this task:\n" + deletedTask.printTask() + "\n" + "Now you have " + taskList.size()
                + " tasks in the list.";
    }

    /**
     * Marks a task as completed in the task list.
     * @param index the index of the task to be marked as completed.
     * @return Pair containing the marked Task and the String to be printed.
     * @throws InvalidIndexException if the index is out of bounds.
     */
    public Map.Entry<Task, String> mark(int index) throws InvalidIndexException {
        Task markTask;
        try {
            markTask = taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("1 to " + taskList.size() + " inclusive");
        }
        markTask.setMarked(true);
        return Map.entry(markTask, "Nice! I've marked this task as done\n"
                + markTask.printTask());
    }

    /**
     * Marks a task as incomplete in the task list.
     * @param index the index of the task to be marked as incomplete.
     * @return Pair containing the unmarked Task and the String to be printed.
     * @throws InvalidIndexException if the index is out of bounds.
     */
    public Map.Entry<Task, String> unmark(int index) throws InvalidIndexException {
        Task unmarkTask;
        try {
            unmarkTask = taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("1 to " + taskList.size() + " inclusive");
        }
        unmarkTask.setMarked(false);
        return Map.entry(unmarkTask, "Oof! I've marked this task as undone\n"
                + unmarkTask.printTask());
    }

    /**
     * List of all the tasks currently in the task list.
     * @return the String to be printed.
     */
    public String list() {
        StringBuilder printStr = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            int num = i + 1;
            printStr.append(num);
            printStr.append(". ");
            printStr.append(taskList.get(i).printTask());
            printStr.append("\n");
        }
        return printStr.toString();
    }

    /**
     * Finds a task with partial-match to taskName in task list.
     * @param taskName the task to find in task list.
     * @return the String to be printed.
     */
    public String find(String taskName) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        Iterator<Task> iter = taskList.iterator();
        StringBuilder printStr = new StringBuilder();

        if (taskName.trim().equals("")) {
            return "No matching tasks were found in your list.\n";
        }

        while (iter.hasNext()) {
            Task currentTask = iter.next();
            if (currentTask.getActivity().contains(taskName)) {
                foundTasks.add(currentTask);
            }
        }

        if (foundTasks.size() > 0) {
            printStr.append("Here are the marching tasks in your list:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                int num = i + 1;
                printStr.append(num);
                printStr.append(". ");
                printStr.append(foundTasks.get(i).printTask());
                printStr.append("\n");
            }
        } else {
            printStr.append("No matching tasks were found in your list.");
        }
        return printStr.toString();
    }
}
