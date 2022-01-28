package duke.util;

import java.util.ArrayList;
import java.util.Iterator;

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
     * @return the string which will be prints out the task that has been added and the current tasks in the list.
     */
    public String addTask(Task task) {
        this.taskList.add(task);
        return "Got it. I've added this task\n" + task.printTask() + "\n" + "Now you've got " + taskList.size() +
                " tasks in the list.";
    }

    /**
     * Deletes a task from the task list.
     * @param index the index of the task to be deleted.
     * @throws InvalidIndexException if the index is out of bounds.
     */
    public void deleteTask(int index) throws InvalidIndexException {
        Task deletedTask;
        try {
            deletedTask = this.taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("1 to " + taskList.size() + " inclusive");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask.printTask());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Marks a task as completed in the task list.
     * @param index the index of the task to be marked as completed.
     * @return the completed task.
     * @throws InvalidIndexException if the index is out of bounds.
     */
    public Task mark(int index) throws InvalidIndexException {
        Task markTask;
        try {
            markTask = taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("1 to " + taskList.size() + " inclusive");
        }
        markTask.setStatus(true);
        System.out.println("Nice! I've marked this task as done");
        System.out.println(markTask.printTask());
        return markTask;
    }

    /**
     * Marks a task as incomplete in the task list.
     * @param index the index of the task to be marked as incomplete.
     * @return the incomplete task.
     * @throws InvalidIndexException if the index is out of bounds.
     */
    public Task unmark(int index) throws InvalidIndexException {
        Task unmarkTask;
        try {
            unmarkTask = taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("1 to " + taskList.size() + " inclusive");
        }
        unmarkTask.setStatus(false);
        System.out.println("Oof! I've marked this task as undone");
        System.out.println(unmarkTask.printTask());
        return unmarkTask;
    }

    /**
     * Prints a list of all the tasks currently in the task list.
     */
    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            int num = i + 1;
            System.out.print(num + ". ");
            System.out.println(taskList.get(i).printTask());
        }
    }

    /**
     * Finds a task with partial-match to taskName in task list.
     * @param taskName the task to find in task list.
     */
    public void find(String taskName) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        Iterator<Task> iter = taskList.iterator();

        if (taskName.trim().equals("")) {
            System.out.println("No matching tasks were found in your list.");
            return;
        }

        while (iter.hasNext()) {
            Task currentTask = iter.next();
            if (currentTask.getActivity().contains(taskName)) {
                foundTasks.add(currentTask);
            }
        }

        if (foundTasks.size() > 0) {
            System.out.println("Here are the marching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                int num = i + 1;
                System.out.print(num + ". ");
                System.out.println(foundTasks.get(i).printTask());
            }
        } else {
            System.out.println("No matching tasks were found in your list.");
        }
    }
}
