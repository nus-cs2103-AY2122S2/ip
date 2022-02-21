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
     * Returns list of tasks currently added. as a String
     *
     * @return String Representing list of tasks.
     */
    public String listItem() {
        StringBuilder result;
        if (taskArray.size() == 0) {
            result = new StringBuilder("No items in the list");
        } else {
            result = new StringBuilder();
            for (int i = 0; i < taskArray.size(); i++) {
                result.append(String.format("%d. ", i + 1)).append(taskArray.get(i)).append("\n");
            }
        }
        return String.valueOf(result);
    }


    /**
     * Marks a specified task as done.
     *
     * @param command Commmand is the input given by the user
     * @throws IndexOutOfBoundsException If user gives a number that exceeds the length of the list
     * @throws NumberFormatException     If user gives a string instead of a number
     */
    public String markItem(String[] command) throws IndexOutOfBoundsException, NumberFormatException {
        StringBuilder result = new StringBuilder();
        int index = Integer.parseInt(command[1]);
        taskArray.get(index - 1).setChecked();
        result.append("Nice! I've marked this task as done:\n");
        result.append(taskArray.get(index - 1));
        return String.valueOf(result);
    }

    /**
     * Unmarks a specified task as done.
     *
     * @param command Commmand is the input given by the user
     * @throws IndexOutOfBoundsException If user gives a number that exceeds the length of the list
     * @throws NumberFormatException     If user gives a string instead of a number
     */
    public String unmarkItem(String[] command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command[1]);
        taskArray.get(index - 1).setUnchecked();
        return String.valueOf("OK, I've marked this task as not done yet:\n" + taskArray.get(index - 1));
    }

    /**
     * Deletes an item from the list of tasks.
     *
     * @param command Command is the input that the user gives
     * @throws IndexOutOfBoundsException If user gives a number that exceeds the length of the list
     * @throws NumberFormatException     If user gives a string instead of a number
     */
    public String deleteItem(String[] command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command[1]);
        Task task = taskArray.remove(index - 1);
        String result = "Noted, I've removed this task from the list: " + task + "\n";
        result += String.format("You have %d tasks left\n", taskArray.size());
        return String.valueOf(result);
    }


    public String addTodo(String title) {
        Todo todo = new Todo(title);
        taskArray.add(todo);
        return "added: " + todo.toString() + "\n";
    }

    public String addDeadline(String title, LocalDate date, LocalTime time) {
        Deadline deadline = new Deadline(title, date, time);
        taskArray.add(deadline);
        return "added: " + deadline.toString() + "\n";
    }

    public String addEvent(String title, LocalDate date, LocalTime time) {
        Event event = new Event(title, date, time);
        taskArray.add(event);
        return "added: " + event.toString() + "\n";
    }


    public int getSize() {
        return taskArray.size();
    }

    /**
     * Prints the tasks that contains the search term.
     *
     * @param term User wants to find tasks with this term
     * @throws DukeException If user gives an empty string as a search term.
     */
    public void findItem(String term) throws DukeException {
        StringBuilder result = new StringBuilder();
        if (term.isEmpty()) {
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

}

