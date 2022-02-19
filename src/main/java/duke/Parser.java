package duke;
import java.io.IOException;

import duke.commands.Command;
import duke.exceptions.CorruptedSaveException;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidItemNumberException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Encapsulates the logic to parse and understand inputs by the user.
 */
public class Parser {

    private TaskList tasks;

    public Parser() {
        tasks = new TaskList();
    }

    /**
     * Instantiate a parser using the saved file.
     *
     * @return Parser resumed from the previous session.
     * @throws CorruptedSaveException if savefile is corrupted.
     */
    public static Parser fromSave() throws CorruptedSaveException {
        Parser p = new Parser();
        p.tasks = Storage.readSaveFile();
        return p;
    }

    /**
     * Handles user commands and delegates them to their corresponding methods.
     *
     * @param input The text input by the user.
     */
    public String inputHandler(String input) {
        assert input != null;
        String[] commandArgs = input.split(" ", 2);
        String command = commandArgs[0];
        String commandDetails = commandArgs.length == 2 ? commandArgs[1] : null;
        String replyMessage = "";

        try {
            switch (command) {
            case Command.BYE_COMMAND:
                return byeMessage();
            case "list":
                return tasks.listItems();

            case "mark": //index number operations
            case "unmark":
            case "delete":
                replyMessage = indexNumberOperation(command, commandDetails);
                Storage.updateTaskFile(tasks);
                break;

            case "todo": //create task operations
            case "deadline":
            case "event":
                Task task = createTask(command, commandDetails);
                replyMessage = tasks.addTask(task);
                Storage.updateTaskFile(tasks);
                break;

            case "find":
                replyMessage = tasks.find(commandDetails);
                break;

            default:
                replyMessage = Ui.UNKNOWN_COMMAND_MESSAGE;
            }
        } catch (DukeException e) {
            replyMessage = e.getMessage();
        } catch (IOException ioException) {
            replyMessage = Ui.mergeMessages(ioException.toString(), Ui.READ_WRITE_ERROR_MESSAGE);
        }

        return replyMessage;
    }

    /**
     * Creates a Task based on user-specified parameters.
     * @param command The type of task to be created.
     * @param commandDetails The user-specified parameters for different types of tasks.
     * @return A Task.
     * @throws DukeException if user-specified parameters do not meet the expected format.
     */
    public Task createTask(String command, String commandDetails) throws DukeException {
        assert command != null;
        assertNonEmptyDetails(commandDetails);
        String[] taskArgs = null;

        if (command.equals("todo")) {
            return new ToDo(commandDetails);
        } else if (command.equals("deadline")) {
            taskArgs = commandDetails.split(" /by ", 2);
        } else if (command.equals("event")) {
            taskArgs = commandDetails.split(" /at ", 2);
        }

        if (taskArgs.length < 2) {
            throw new DukeException(String.format(
            "Missing details for %s!", command));
        }

        return command.equals("deadline")
            ? new Deadline(taskArgs[0], taskArgs[1])
            : new Event(taskArgs[0], taskArgs[1]);
    }

    /**
     * Handles index-related operations on tasks.
     * @param command The type of operation to be carried out.
     * @param commandDetails The user-specified parameters.
     * @return A success message for the user.
     */
    private String indexNumberOperation(String command, String commandDetails) throws InvalidItemNumberException {
        assertValidItemNumber(commandDetails);
        int index = Integer.parseInt(commandDetails);

        switch (command) {
        case "mark":
            return tasks.markItem(index);
        case "unmark":
            return tasks.unmarkItem(index);
        case "delete":
            return tasks.deleteItem(index);
        default:
            return "";
        }
    }


    /**
     * The action to be taken when a bye command is issued.
     * <p> Sets the parser to stop accepting user input. </p>
     * @return The bye message.
     */
    private String byeMessage() {
        return Ui.BYE_MESSAGE;
    }

    private void assertNonEmptyDetails(String details) throws DukeException {
        if (details == null) {
            throw new DukeException("Missing details!");
        }
    }

    private boolean isNumeric(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }

    private void assertValidItemNumber(String str) throws InvalidItemNumberException {
        if (str == null) {
            throw new InvalidItemNumberException("Missing item number!");
        }

        if (!isNumeric(str)) {
            throw new InvalidItemNumberException(
            "Please specify a numerical value for the item number instead of \"" + str + "\"!");
        }

        int itemNumber = Integer.parseInt(str);

        if (!tasks.isValidItemNumber(itemNumber)) {
            throw new InvalidItemNumberException("Please specify a valid item number");
        }
    }
}
