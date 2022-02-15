package aeromon;

import aeromon.command.*;

import java.time.LocalDate;

/**
 * CommandManager class that manages the command input.
 */
public class CommandManager {

    /**
     * Reads the command input and returns the relevant Command object.
     * @param fullCommand the unprocessed command input.
     * @return command object to be executed.
     * @throws AeromonException when an error occurs during the reading process.
     */
    public static Command read(String fullCommand) throws AeromonException {
        assert fullCommand != null : "Command is null";

        String[] arr = fullCommand.split(" ", 2);
        String command = arr[0];

        switch (command) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "mark": {
            int taskNum = getTaskNum(arr[1]);
            return new EditCommand(EditCommand.EditType.MARK, taskNum);
        }

        case "unmark": {
            int taskNum = getTaskNum(arr[1]);
            return new EditCommand(EditCommand.EditType.UNMARK, taskNum);
        }

        case "delete": {
            int taskNum = getTaskNum(arr[1]);
            return new EditCommand(EditCommand.EditType.DELETE, taskNum);
        }

        case "todo": {
            return new AddCommand(AddCommand.TaskType.TODO, new String[] { checkDescription("Todo", arr[1]) });
        }

        case "deadline": {
            String description = checkDescription("Deadline", arr[1]);
            String[] tokens = description.split(" /by ");
            return new AddCommand(AddCommand.TaskType.DEADLINE, tokens);
        }

        case "event": {
            String description = checkDescription("Event", arr[1]);
            String[] tokens = description.split(" /at ");
            return new AddCommand(AddCommand.TaskType.EVENT, tokens);
        }

        case "find": {
            return new FindCommand(arr[1]);
        }

        default:
            throw new AeromonException("Nani? Me no understand what you say .-.");
        }
    }

    /**
     * Checks if the description of the task is valid.
     * @param taskType the type of the Task object.
     * @param description the description of the Task object, for Deadline and Event Tasks,
     *                    the method also checks if the date format is valid.
     * @return the description of the Task.
     * @throws AeromonException if the description or date is empty, or if the date format is wrong.
     */
    public static String checkDescription(String taskType, String description) throws AeromonException {
        assert description != null : "Description is null";

        String s = description.trim();

        if (s.isEmpty()) {
            throw new AeromonException(String.format("What do you want to do? The description of %s cannot be empty!\n", taskType));
        } else if (taskType.equals("Deadline")) {

            try {
                String[] arr = description.split(" /by ");
                String by = arr[1].trim();
                LocalDate.parse(by);
            } catch (Exception e) {
                throw new AeromonException(String.format("I need a new date with the correct format please :/"));
            }
        } else if (taskType.equals("Event")) {

            try {
                String[] arr = description.split(" /at ");
                String by = arr[1].trim();
                LocalDate.parse(by);
            } catch (Exception e) {
                throw new AeromonException(String.format("I need a new date with the correct format please :/"));
            }
        }
        return description;
    }

    /**
     * Gets the task number from the command.
     * @param string The command to read from.
     * @return The task number.
     * @throws AeromonException when the task number is an invalid number.
     */
    public static int getTaskNum(String string) throws AeromonException {
        assert string != null : "Task Number is null";

        String taskNum = string.trim();

        if (taskNum.isEmpty()) {
            throw new AeromonException("Why is the task number empty?");
        } else {
            return Integer.parseInt(taskNum);
        }
    }
}
