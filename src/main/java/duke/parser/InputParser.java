package duke.parser;

import duke.TaskList;
import duke.exception.DeleteIndexException;
import duke.exception.EmptyDescriptionException;
import duke.exception.IndexOutOfStoreException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingDateException;
import duke.exception.RonException;
import duke.exception.ToggleException;
import duke.exception.UnidentifiedException;
import duke.exception.WrongDateSyntaxException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Parser type: Input parser
 * Parse all user inputs for addition into tasklist
 */
public class InputParser extends Parser {
    public String parseInput(String input, TaskList tasks) throws RonException {
        String trimmedText = input.trim();
        if (input.contains("unmark")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index >= tasks.size() || index < 0) {
                throw new IndexOutOfStoreException();
            }
            if (!tasks.get(index).getIsDone()) {
                throw new ToggleException(false);
            }
            tasks.get(index).toggleStatus();
            return "No problem! The following task is marked as not done yet:\n" + tasks.get(index);
        } else if (input.contains("mark")) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            if (index >= tasks.size() || index < 0) {
                throw new IndexOutOfStoreException();
            }
            if (tasks.get(index).getIsDone()) {
                throw new ToggleException(true);
            }
            tasks.get(index).toggleStatus();
            return "Good job! The following task is marked as done:\n" + tasks.get(index);
        } else if (input.contains("delete")) {
            if (trimmedText.length() == "delete".length()) {
                throw new DeleteIndexException();
            }
            int index = Integer.parseInt(input.substring(7));
            if (index > tasks.size()) {
                throw new InvalidIndexException();
            }
            return "OK, the following task is removed:\n" + tasks.remove(index - 1)
                    + "\n" + "There are " + tasks.size() + " task(s) in the list.";
        } else if (input.equals("list")) {
            if (tasks.size() == 0) {
                return "You have no pending tasks on your list :)";
            } else {
                return "The tasks on your list are as follows:\n" + tasks.printTasks();
            }
        } else if (input.contains("todo")) {
            if (input.replace("todo", "").trim().length() == 0) {
                throw new EmptyDescriptionException("todo");
            }
            Task task = new Todo(input);
            tasks.add(task);
            return "Task added!\n" + task + "\n" + "There are " + tasks.size() + " task(s) in the list.";
        } else if (input.contains("deadline")) {
            if (input.replace("deadline", "").trim().length() == 0) {
                throw new EmptyDescriptionException("deadline");
            } else if (trimmedText.charAt(trimmedText.length() - 1) == "/".charAt(0)) {
                throw new MissingDateException();
            } else if (!input.contains("/")) {
                throw new WrongDateSyntaxException();
            }
            Task task = new Deadline(input);
            if (task.getDescription().trim().length() == 0) {
                throw new EmptyDescriptionException("deadline");
            }
            tasks.add(task);
            return "Task added!\n" + task + "\n" + "There are " + tasks.size() + " task(s) in the list.";
        } else if (input.contains("event")) {
            if (input.replace("event", "").trim().length() == 0) {
                throw new EmptyDescriptionException("event");
            } else if (trimmedText.charAt(trimmedText.length() - 1) == "/".charAt(0)) {
                throw new MissingDateException();
            } else if (!input.contains("/")) {
                throw new WrongDateSyntaxException();
            }
            Task task = new Event(input);
            tasks.add(task);
            return "Task added!\n" + task + "\n" + "There are " + tasks.size() + " task(s) in the list.";
        } else if (input.contains("find")) {
            if (input.replace("find", "").trim().length() == 0) {
                throw new EmptyDescriptionException("find");
            }
            return "Here are some tasks that match your request:\n" + tasks.findTasks(input.substring(5));
        } else {
            throw (new UnidentifiedException());
        }
    }
}
