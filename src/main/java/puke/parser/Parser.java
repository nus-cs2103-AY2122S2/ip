package puke.parser;

import puke.exception.PukeException;
import puke.task.TaskList;
import puke.ui.Ui;

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
            if (argument == null) {
                return tasks.listTasks();
            } else {
                throw new PukeException("I don't need any argument for list..");
            }
        case "mark":
        case "unmark":
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
        case "todo":
        case "deadline":
        case "event":
            return tasks.addTask(command, argument);
        case "delete":
            return tasks.deleteTask(Integer.parseInt(argument));
        case "find":
            return tasks.findTasks(argument);
        default:
            throw new PukeException("Are you sure you're making sense?");
        }
    }
}
