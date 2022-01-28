package duke;

import java.util.Scanner;

public class Parser {


    Scanner scanner;
    String command;

    Parser() {
        this.scanner = new Scanner(System.in);
        this.command = " ";
    }

    public Command readCommand() throws DukeException {
        this.command = scanner.nextLine();
        return processCommand(command);
    }

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
