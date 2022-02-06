package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import duke.DukeException;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList represents a list of Tasks. Includes methods to modify the TaskList and the Tasks in the list.
 *
 * @author Jian Rong
 */
public class TaskList {
    public List<Task> taskArray = new ArrayList<>();

    /**
     * Prints the list of tasks currently added.
     */
    public void listItem() {
        System.out.println("__________________________________");
        if (taskArray.size() == 0) {
            System.out.println("No items in the list");
        } else {
            for (int i = 0; i < taskArray.size(); i++) {
                System.out.printf("%d. " + taskArray.get(i) + "\n", i+1);
            }
        }
        System.out.println("__________________________________");
    }


    /**
     * Marks a specified task as done.
     *
     * @param command Commmand is the input given by the user
     * @throws IndexOutOfBoundsException If user gives a number that exceeds the length of the list
     * @throws NumberFormatException If user gives a string instead of a number
     */
    public void markItem(String[] command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command[1]);
        taskArray.get(index-1).setChecked();
        System.out.println("__________________________________");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(taskArray.get(index-1));
        System.out.println("__________________________________");
    }

    /**
     * Unmarks a specified task as done.
     *
     * @param command Commmand is the input given by the user
     * @throws IndexOutOfBoundsException If user gives a number that exceeds the length of the list
     * @throws NumberFormatException If user gives a string instead of a number
     */
    public void unmarkItem(String[] command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command[1]);
        taskArray.get(index-1).setUnchecked();
        System.out.println("__________________________________");
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(taskArray.get(index-1));
        System.out.println("__________________________________");
    }

    /**
     * Deletes an item from the list of tasks.
     *
     * @param command Command is the input that the user gives
     * @throws IndexOutOfBoundsException If user gives a number that exceeds the length of the list
     * @throws NumberFormatException If user gives a string instead of a number
     */
    public void deleteItem(String[] command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command[1]);
        Task task = taskArray.remove(index - 1);
        System.out.println("__________________________________");
        System.out.println("Noted, I've removed this task from the list: ");
        System.out.println(task);
        System.out.printf("You have %d tasks left\n", taskArray.size());
        System.out.println("__________________________________");
    }


    /**
     * Adds an item from the list of tasks.
     *
     * @param command Command is the input that the user gives
     * @throws DukeException If syntax of input given by user is wrong
     */
    public void addItem(String[] command) throws DukeException {
        String input = command[0];
        System.out.println("__________________________________");
        switch (input) {
        case "todo":
            taskArray.add(new Todo(command[1]));
            break;
        case "deadline":
            taskArray.add(new Deadline(command[1]));
            break;
        case "event":
            taskArray.add(new Event(command[1]));
            break;
        }
        System.out.printf("You have %d tasks in your list\n", taskArray.size());
        System.out.println("__________________________________");
    }

    /**
     * Returns the list of tasks in a readable format.
     * @return String Representing the list of tasks
     */
    public String writeItem() {
        StringBuilder list = new StringBuilder();
        if (taskArray.size() == 0) {
            list = new StringBuilder("No items in the list");
        } else {
            for (int i = 0; i < taskArray.size(); i++) {
                String line = String.format("%d. " + taskArray.get(i)+ "\n", i+1);
                list.append(line);
            }
        }
        return list.toString();
    }
}
