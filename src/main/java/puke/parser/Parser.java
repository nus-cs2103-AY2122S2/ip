package puke.parser;

import puke.exception.PukeException;
import puke.task.TaskList;

/**
 * Handles the user inputs.
 */
public class Parser {
    /**
     * Processes a line of input from the user.
     *
     * @param s Line of input from the user.
     * @param tasks List of tasks in the current session.
     * @return Response from the chat-bot.
     * @throws PukeException If the line of input is invalid.
     */
    public String processInput(String s, TaskList tasks) throws PukeException {
        String[] inputs = s.split(" ", 2);
        String command = inputs[0]; // get the first word of the input

        String argument = null;
        if (inputs.length > 1) {
            argument = inputs[1]; // remaining input (if any)
        }

        switch (command) {
        case "bye":
            return null;
        case "list":
            if (argument != null) {
                throw new PukeException("I don't need any argument for list..");
            }
            return tasks.listTasks();
        case "mark":
        case "unmark":
            return markUnmarkTask(command, argument, tasks);
        case "todo":
        case "deadline":
        case "event":
            return tasks.addTask(command, argument);
        case "delete":
            if (argument == null) {
                throw new PukeException("Tell me what you want to delete..");
            }
            return tasks.deleteTask(Integer.parseInt(argument));
        case "find":
            return tasks.findTasks(argument);
        case "sort":
            return sortTask(argument, tasks);
        default:
            throw new PukeException("Are you sure you're making sense?");
        }
    }

    /**
     * Mark/unmark a task based on the command and input given.
     *
     * @param command Command to either mark/unmark.
     * @param argument Input from the user.
     * @param tasks List of tasks currently.
     * @return Response from the chat-bot.
     * @throws PukeException If the task cannot be marked/unmarked; Or the task is invalid.
     */
    public String markUnmarkTask(String command, String argument, TaskList tasks) throws PukeException {
        try {
            int taskNo = Integer.parseInt(argument);
            if (command.equals("mark")) {
                return tasks.markTask(taskNo);
            } else {
                return tasks.unmarkTask(taskNo);
            }
        } catch (NumberFormatException e) {
            throw new PukeException("I'll need a valid task number for it..");
        }
    }

    /**
     * Sorts the task list according to the command given.
     *
     * @param argument Input from the user.
     * @param tasks List of tasks currently.
     * @return Response from the chat-bot with the sorted tasks.
     * @throws PukeException If the user input is invalid.
     */
    public String sortTask(String argument, TaskList tasks) throws PukeException {
        if (argument == null) {
            throw new PukeException("So.. what do you want to sort by?");
        }

        switch (argument) {
        case "date":
        case "date asc":
            return tasks.sortByDate(true);
        case "date dsc":
            return tasks.sortByDate(false);
        case "name":
        case "name asc":
            return tasks.sortByName(true);
        case "name dsc":
            return tasks.sortByName(false);
        case "type":
            return tasks.sortByType();
        default:
            throw new PukeException("This is NOT the correct format for sort...");
        }
    }
}
