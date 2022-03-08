package duke;

/**
 * Helps the software to make sense of the commands that the user has sent in.
 */
public class Parser {

    public Parser() {

    }

    /**
     * To process the command that has been read by the system.
     *
     * @param command the string that the user has entered into the system.
     * @return an instance of a command for the system to run.
     * @throws DukeException when the user types a command that is not recognised.
     */
    public Command processCommand(String command) throws DukeException {
        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.contains("unmark")) {
            int index = Integer.parseInt(command.split(" ")[1]);
            return new MarkCommand(MarkCommand.MarkTypes.Unmark, index - 1);
        } else if (command.contains("mark")) {
            int index = Integer.parseInt(command.split(" ")[1]);
            return new MarkCommand(MarkCommand.MarkTypes.Mark, index - 1);
        } else if (command.contains("delete")) {
            int index = Integer.parseInt(command.substring(7));
            return new DeleteCommand(index - 1);
        } else if (command.contains("todo")) {
            return new TaskCommand(TaskCommand.TaskType.TODO, command);
        } else if (command.contains("event")) {
            return new TaskCommand(TaskCommand.TaskType.EVENT, command);
        } else if (command.contains("deadline")) {
            return new TaskCommand(TaskCommand.TaskType.DEADLINE, command);
        } else if (command.contains("/help")) {
            return new HelpCommand();
        } else if (command.contains("find")) {
            return new FindCommand(command.substring(5));
        } else {
            throw new DukeException("Invalid Command.");
        }
    }

}
