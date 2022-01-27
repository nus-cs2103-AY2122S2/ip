package duke.parser;

import duke.TaskList;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class InputParser extends Parser {
    public void parseInput(String input, TaskList tasks) throws RonException {
        String trimmedText = input.trim();
        if (input.contains("unmark")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index >= tasks.size() || index < 0) {
                throw new IndexOutOfStoreException();
            }
            if (!tasks.get(index).getIsDone()) {
                throw new ToggleException(false);
            }
            System.out.println("No problem! The following task is marked as not done yet:");
            tasks.get(index).toggleStatus();
            System.out.println(tasks.get(index));
        } else if (input.contains("mark")) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            if (index >= tasks.size() || index < 0) {
                throw new IndexOutOfStoreException();
            }
            if (tasks.get(index).getIsDone()) {
                throw new ToggleException(true);
            }
            System.out.println("Good job! The following task is marked as done:");
            tasks.get(index).toggleStatus();
            System.out.println(tasks.get(index));
        } else if (input.contains("delete")) {
            if (trimmedText.length() == "delete".length()) {
                throw new DeleteIndexException();
            }
            int index = Integer.parseInt(input.substring(7));
            if (index > tasks.size()) {
                throw new InvalidIndexException();
            }
            System.out.println("OK, the following task is removed:");
            System.out.println(tasks.remove(index - 1));
            System.out.println("There are " + tasks.size() + " task(s) in the list.");
        } else if (input.equals("list")) {
            if (tasks.size() == 0) {
                System.out.println("You have no pending tasks on your list :)");
            } else {
                System.out.println("The tasks on your list are as follows:");
                tasks.printTasks();
            }
        } else if (input.contains("todo")) {
            if (input.replace("todo", "").trim().length() == 0) {
                throw new EmptyDescriptionException("todo");
            }
            Task task = new Todo(input);
            tasks.add(task);
            System.out.println("Task added!");
            System.out.println(task);
            System.out.println("There are " + tasks.size() + " task(s) in the list.");
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
            System.out.println("Task added!");
            System.out.println(task);
            System.out.println("There are " + tasks.size() + " task(s) in the list.");
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
            System.out.println("Task added!");
            System.out.println(task);
            System.out.println("There are " + tasks.size() + " task(s) in the list.");
        } else {
            throw (new UnidentifiedException());
        }
    }
}
