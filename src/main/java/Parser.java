import java.io.IOException;

public class Parser {
    
    private TaskList tasks;
    public boolean isPolling;

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

    public Task createTask(String command, String commandDetails) throws DukeException{
        String[] taskArgs = null;

        if (command.equals("todo")) {
            return new ToDo(commandDetails);
        } else if (command.equals("deadline")) {
            taskArgs = commandDetails.split(" /by ", 2);
        } else if (command.equals("event")) {
            taskArgs = commandDetails.split(" /at ", 2);
        }

        if (taskArgs.length < 2) throw new DukeException(String.format(
            "Missing details for %s!", command));

        return command.equals("deadline") 
            ? new Deadline(taskArgs[0], taskArgs[1])
            : new Event(taskArgs[0], taskArgs[1]);
    }

    public String byeMessage() {
        isPolling = false;
        return "Bye. Hope to see you again soon!";
    }

    private void assertNonEmptyDetails(String details) throws DukeException {
        if (details == null) throw new DukeException("Missing details!");
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
        if (str == null) throw new DukeException("Missing item number!");
        
        if (!isNumeric(str)) throw new DukeException(
            "Please specify a numerical value for the item number instead of \"" + str + "\"!");

        int itemNumber = Integer.parseInt(str);

        if (!tasks.isValidItemNumber(itemNumber)) throw new DukeException(
            "Please specify a valid item number");
    }
}
