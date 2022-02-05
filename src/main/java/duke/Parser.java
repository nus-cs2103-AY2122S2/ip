package duke;
import java.io.IOException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Encapsulates the logic to parse and understand inputs by the user.
 */
public class Parser {

    /** Represents whether or not the parser is accepting userinput. */
    public boolean isPolling;
    private TaskList tasks;

    public Parser() {
        try {
            tasks = Storage.readSaveFile();
        } catch (DukeException e) {
            Ui.printMessage(Ui.CORRUPTED_SAVE_MESSAGE);
            tasks = new TaskList();
        }

        isPolling = true;
        Ui.printMessage(Ui.GREETING_MESSAGE);
    }

    /**
     * Handles user commands and delegates them to their corresponding methods.
     * 
     * @param input The text input by the user.
     */
    public void inputHandler(String input) {
        String[] commandArgs = input.split(" ", 2);
        String command = commandArgs[0];
        String commandDetails = commandArgs.length == 2 ? commandArgs[1] : null;

        String replyMessage = "";
        try {
            switch (command) {
            case "bye":
                replyMessage = byeMessage();
                break;

            case "list":
                replyMessage = tasks.listItems();
                break;

            case "mark":
                assertValidItemNumber(commandDetails);
                replyMessage = tasks.markItem(Integer.parseInt(commandDetails));
                Storage.updateTaskFile(tasks);
                break;

            case "unmark":
                assertValidItemNumber(commandDetails);
                replyMessage = tasks.unmarkItem(Integer.parseInt(commandDetails));
                Storage.updateTaskFile(tasks);
                break;

            case "todo":
                //Fall Through
            case "deadline": 
                //Fall Through
            case "event":
                assertNonEmptyDetails(commandDetails);
                Task task = createTask(command, commandDetails);
                replyMessage = tasks.addTask(task);
                Storage.updateTaskFile(tasks);
                break;

            case "delete":
                assertValidItemNumber(commandDetails);
                replyMessage = tasks.deleteItem(Integer.parseInt(commandDetails));
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

        Ui.printMessage(replyMessage);
    }

    /**
     * Creates a Task based on user-specified parameters.
     * 
     * @param command The type of task to be created.
     * @param commandDetails The user-specified parameters for different types of tasks.
     * @return A Task.
     * @throws DukeException if user-specified parameters do not meet the expected format.
     */
    public Task createTask(String command, String commandDetails) throws DukeException {
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
     * The action to be taken when a bye command is issued.
     * 
     * <p> Sets the parser to stop accepting user input. </p>
     * 
     * @return The bye message.
     */
    public String byeMessage() {
        isPolling = false;
        return "Bye. Hope to see you again soon!";
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

    private void assertValidItemNumber(String str) throws DukeException {
        if (str == null) {
            throw new DukeException("Missing item number!");
        }
        
        if (!isNumeric(str)) {
            throw new DukeException(
            "Please specify a numerical value for the item number instead of \"" + str + "\"!");
        }

        int itemNumber = Integer.parseInt(str);

        if (!tasks.isValidItemNumber(itemNumber)) {
            throw new DukeException(
            "Please specify a valid item number");
        }
    }
}
