package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a class that parses the users' commands.
 */
public class Parser {

    /**
     * Returns the command that needed to be executed.
     *
     * @param fullCommand the user's full input to the chatbot.
     * @param taskList a list of the tasks that the user have.
     * @return the command to be executed.
     * @throws DukeException if there is an error in the command.
     */
    public static Command parse(String fullCommand, TaskList taskList) throws DukeException {
        String[] commandAndDetails = fullCommand.split(" ", 2);
        String command = commandAndDetails[0];
        switch (command) {
        case "bye":
            if (commandAndDetails.length > 1) {
                throw new DukeException("There's too many input!");
            }
            return new ByeCommand();
        case "list":
            if (commandAndDetails.length > 1) {
                throw new DukeException("There's too many input!");
            }
            return new ListCommand();
        case "help":
            if (commandAndDetails.length > 1) {
                throw new DukeException("There's too many input!");
            }
            return new HelpCommand();
        case "find":
            if (commandAndDetails.length == 1) {
                throw new DukeException("Enter the keyword of the tasks you want to find.");
            }
            return new FindCommand(commandAndDetails);
        case "mark":
        case "unmark":
        case "delete":
            if (commandAndDetails.length == 1) {
                throw new DukeException("Please specify the task number to " + command + ".");
            }
            int taskNumber = Integer.parseInt(commandAndDetails[1]);
            if (taskNumber > taskList.size() || taskNumber <= 0) {
                throw new DukeException("Invalid task number! You have " + taskList.size() + " task(s).");
            }
            return new ModifyCommand(commandAndDetails);
        case "todo":
            if (commandAndDetails.length == 1) {
                throw new DukeException("Please specify the task you want to do.");
            }
            return new AddCommand(commandAndDetails);
        case "deadline":
        case "event":
            if (commandAndDetails.length == 1) {
                throw new DukeException("Please specify the description and time of the "
                        + command + " you want to add.");
            }
            String[] taskAndTime = commandAndDetails[1].split("/");
            if (taskAndTime.length < 2 || taskAndTime[0].equals("") || taskAndTime[1].length() < 3) {
                throw new DukeException("Invalid input. Please specify the description/time of the " + command + ".");
            }
            return new AddCommand(commandAndDetails);
        default:
            return new InvalidCommand();
        }
    }
}
