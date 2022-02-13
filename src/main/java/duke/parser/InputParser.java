package duke.parser;

import duke.TaskList;
import duke.exception.DeleteIndexException;
import duke.exception.DuplicateException;
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
            assert tasks.size() > 0 : "Task list is empty";
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index >= tasks.size() || index < 0) {
                throw new IndexOutOfStoreException();
            } else if (!tasks.get(index).getIsDone()) {
                throw new ToggleException(false);
            }
            tasks.get(index).toggleStatus();
            return "No problem! The following task is marked as not done yet:\n" + tasks.get(index);
        } else if (input.contains("mark")) {
            assert tasks.size() > 0 : "Task list is empty";
            int index = Integer.parseInt(input.substring(5)) - 1;
            if (index >= tasks.size() || index < 0) {
                throw new IndexOutOfStoreException();
            } else if (tasks.get(index).getIsDone()) {
                throw new ToggleException(true);
            }
            tasks.get(index).toggleStatus();
            return "Good job! The following task is marked as done:\n" + tasks.get(index);
        } else if (input.contains("delete")) {
            assert tasks.size() > 0 : "Task list is empty";
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
            boolean emptyDescription = input.replace("todo", "").trim().length() == 0;
            if (emptyDescription) {
                throw new EmptyDescriptionException("todo");
            }
            Task task = new Todo(input);
            if (tasks.isDuplicate(task)) {
                throw new DuplicateException();
            }
            tasks.add(task);
            return "Task added!\n" + task + "\n" + "There are " + tasks.size() + " task(s) in the list.";
        } else if (input.contains("deadline")) {
            boolean emptyDescription = input.replace("deadline", "").trim().length() == 0;
            boolean missingDate = trimmedText.charAt(trimmedText.length() - 1) == "/".charAt(0);
            boolean wrongSyntax = !input.contains("/");
            if (emptyDescription) {
                throw new EmptyDescriptionException("deadline");
            } else if (missingDate) {
                throw new MissingDateException();
            } else if (wrongSyntax) {
                throw new WrongDateSyntaxException();
            }
            Task task = new Deadline(input);
            if (tasks.isDuplicate(task)) {
                throw new DuplicateException();
            }
            tasks.add(task);
            return "Task added!\n" + task + "\n" + "There are " + tasks.size() + " task(s) in the list.";
        } else if (input.contains("event")) {
            boolean emptyDescription = input.replace("event", "").trim().length() == 0;
            boolean missingDate = trimmedText.charAt(trimmedText.length() - 1) == "/".charAt(0);
            boolean wrongSyntax = !input.contains("/");
            if (emptyDescription) {
                throw new EmptyDescriptionException("event");
            } else if (missingDate) {
                throw new MissingDateException();
            } else if (wrongSyntax) {
                throw new WrongDateSyntaxException();
            }
            Task task = new Event(input);
            if (tasks.isDuplicate(task)) {
                throw new DuplicateException();
            }
            tasks.add(task);
            return "Task added!\n" + task + "\n" + "There are " + tasks.size() + " task(s) in the list.";
        } else if (input.contains("find")) {
            assert tasks.size() > 0 : "Task list is empty";
            boolean emptyDescription = input.replace("find", "").trim().length() == 0;
            if (emptyDescription) {
                throw new EmptyDescriptionException("find");
            }
            return "Here are some tasks that match your request:\n" + tasks.findTasks(input.substring(5));
        } else {
            throw (new UnidentifiedException());
        }
    }
}
