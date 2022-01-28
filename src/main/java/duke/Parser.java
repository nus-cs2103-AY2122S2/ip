package duke;

import java.util.Scanner;

/**
 * Helps the software to make sense of the commands that the user has sent in.
 */
public class Parser {

    Scanner scanner;
    String command;

    /**
     * Constructor to create a new instance of a parser.
     */
    public Parser() {
        this.scanner = new Scanner(System.in);
        this.command = " ";
    }

    /**
     * To read the command that the user has entered into the system.
     * @return a instance of Command that the system will run.
     * @throws DukeException when the user types a command that is not recognised.
     */
    public Command readCommand() throws DukeException {
        this.command = scanner.nextLine();
        return processCommand(command);
    }

    /**
     * To process the command that has been read by the system.
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
            return new DeleteCommand(index-1);
        } else if (command.contains("todo")) {
            return new TaskCommand(TaskCommand.TaskType.todo, command);
        } else if (command.contains("event")) {
            return new TaskCommand(TaskCommand.TaskType.event, command);
        } else if (command.contains("deadline")) {
            return new TaskCommand(TaskCommand.TaskType.deadline, command);
        } else if (command.contains("/help")) {
            return new HelpCommand();
        } else {
            throw new DukeException("Invalid Command.");
        }
    }

}
