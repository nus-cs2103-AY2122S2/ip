package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;

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
                System.out.printf("%d. " + taskArray.get(i) + "\n", i + 1);
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
        taskArray.get(index - 1).setChecked();
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
        taskArray.get(index - 1).setUnchecked();
        System.out.println("__________________________________");
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(taskArray.get(index - 1));
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


    public void addTodo(String title) {
        System.out.println("__________________________________");
        taskArray.add(new Todo(title));
    }

    public void addDeadline(String title, LocalDate date, LocalTime time) {
        System.out.println("__________________________________");
        taskArray.add(new Deadline(title, date, time));
    }

    public void addEvent(String title, LocalDate date, LocalTime time) {
        System.out.println("__________________________________");
        taskArray.add(new Event(title, date, time));
    }


    public int getSize() {
        return taskArray.size();
    }

    /**
     * Prints the tasks that contains the search term.
     * @param term User wants to find tasks with this term
     * @throws DukeException If user gives an empty string as a search term.
     */
    public void findItem(String term) throws DukeException {
        StringBuilder result = new StringBuilder();
        if (term.isEmpty()){
            throw new DukeException("Tell me what you're searching for");
        }
        System.out.println("__________________________________");
        for (int i = 0; i < taskArray.size(); i++) {
            if (taskArray.get(i).titleContains(term)) {
                result.append(String.format("%d. " + taskArray.get(i).toString() + "\n", i + 1));
            }
        }
        if (result.length() == 0) {
            System.out.println("There are no tasks containing that term.");
        } else {
            System.out.print(result);
            System.out.println("__________________________________");
        }
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
                String line = String.format("%d. " + taskArray.get(i)+ "\n", i + 1);
                list.append(line);
            }
        }
        return list.toString();
    }
}
