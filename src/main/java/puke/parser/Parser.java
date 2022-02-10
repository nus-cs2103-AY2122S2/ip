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
            argument = inputs[1].trim(); // remaining input (if any)
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
                throw new PukeException("Tell me which task you want to delete..");
            }
            return tasks.deleteTask(Integer.parseInt(argument));
        case "find":
            return tasks.findTasks(argument);
        case "sort":
            return sortTask(argument, tasks);
        case "help":
            return getHelp(argument);
        default:
            throw new PukeException("You are not making sense...\n\nType 'help' to view available commands!");
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
            throw new PukeException("I'll need a valid task number for it..\n\nType 'help' for more info!");
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
            throw new PukeException("So.. what do you want to sort by?\n\nType 'help sort' for more info!");
        }

        switch (argument.trim()) {
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
            throw new PukeException("This is NOT the correct format for sort...\n\nType 'help sort' for more info!");
        }
    }

    /**
     * Returns a help text for the specific commands requested.
     *
     * @param argument Input from the user.
     * @return Help text according to the command.
     * @throws PukeException When the command to help is invalid.
     */
    public String getHelp(String argument) throws PukeException {
        String commands = "todo | deadline | event | mark | unmark | list | delete | find | sort";

        if (argument == null) {

            return "Here are the commands you can do,\ntype 'help [command]' to get more info!\n"
                    + "Note that commands are case-sensitive\n\n" + commands;
        }

        switch (argument.trim()) {
        case "todo":
            return "Type 'todo [task name]' to add a to-do task.\n\nTo-do tasks do not have any date/time!";
        case "deadline":
            return "Type 'deadline [task name] /by [datetime]' to add a deadline.\n\n"
                    + "The format of datetime should be 'yyyy-mm-dd hh:mm'";
        case "event":
            return "Type 'event [task name] /at [datetime]' to add an event.\n\n"
                    + "The format for datetime should be 'yyyy-mm-dd hh:mm'";
        case "mark":
            return "Type 'mark [task num]' to mark as done!\n\n"
                    + "The task number can be checked with 'list'";
        case "unmark":
            return "Type 'unmark [task num]' to unmark as done!\n\n"
                    + "The task number can be checked with 'list'";
        case "list":
            return "Type 'list' to view the list of tasks you have!";
        case "delete":
            return "Type 'delete [task num]' to delete a task!\n\n"
                    + "The task number can be checked with 'list'";
        case "find":
            return "Type 'find [keyword] to find tasks containing the keyword in the task name!\n\n"
                    + "Note that it does not have to match by the whole word";
        case "sort":
            return "Type 'sort [by] [asc/dsc]' to sort the tasks!\n\n[by] can either be date | name | type\n\n"
                    + "For date/name, you can specify either ascending/descending by typing asc or dsc";
        default:
            throw new PukeException("That is not a valid command...\n\nAvailable commands are:\n" + commands);
        }
    }
}
