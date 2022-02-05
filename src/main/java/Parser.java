public class Parser {

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
                throw new DukeException("Please specify the description and time of the " + command + " you want to add.");
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
