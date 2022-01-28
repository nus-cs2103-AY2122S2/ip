package Duke.util;

import Duke.command.AddCommand;
import Duke.command.ByeCommand;
import Duke.command.Command;
import Duke.command.DeleteCommand;
import Duke.command.ListCommand;
import Duke.command.MarkCommand;
import Duke.command.UnmarkCommand;

import Duke.exception.DukeException;

import Duke.task.Event;
import Duke.task.Deadline;
import Duke.task.Todo;

public class Parser {

    public static Command parseCommand(String userInput) throws DukeException {

        String[] splitCommand = userInput.split(" ", 2);
        String commandWord = splitCommand[0].toLowerCase();
        Command command = null;

        switch (commandWord) {
        case (ByeCommand.COMMAND_WORD):
            command = new ByeCommand();
            break;
        case (DeleteCommand.COMMAND_WORD):
            if (splitCommand.length == 1) {
                throw new DukeException("\tAaaarrrrgggghhhh you can't delete nuthin' matey!\n");
            }
            int taskIndex = Integer.parseInt(splitCommand[1]) - 1;
            command = new DeleteCommand(taskIndex);
            break;
        case (ListCommand.COMMAND_WORD):
            command = new ListCommand();
            break;
        case (MarkCommand.COMMAND_WORD):
            if (splitCommand.length == 1) {
                throw new DukeException("\tAaaarrrrgggghhhh you can't mark nuthin' matey!\n");
            }
            taskIndex = Integer.parseInt(splitCommand[1]) - 1;
            command = new MarkCommand(taskIndex);
            break;
        case (UnmarkCommand.COMMAND_WORD):
            if (splitCommand.length == 1) {
                throw new DukeException("\tAaaarrrrgggghhhh you can't unmark nuthin' matey!\n");
            }
            taskIndex = Integer.parseInt(splitCommand[1]) - 1;
            command = new UnmarkCommand(taskIndex);
            break;
        case ("deadline"):
            if (splitCommand.length == 1) {
                throw new DukeException("\tAaaarrrrgggghhhh deadline description can't be empty matey!\n");
            }
            String[] furtherSplitCommand = splitCommand[1].split(" /by ", 2);
            try {
                Deadline deadline = new Deadline(furtherSplitCommand[0], furtherSplitCommand[1]);
                command = new AddCommand(deadline);
            } catch (DukeException e) {
                command = new AddCommand(null);
            }
            break;
        case ("event"):
            if (splitCommand.length == 1) {
                throw new DukeException("\tAaaarrrrgggghhhh event description can't be empty matey!\n");
            }
            furtherSplitCommand = splitCommand[1].split(" /at ", 2);
            try {
                Event event = new Event(furtherSplitCommand[0], furtherSplitCommand[1]);
                command = new AddCommand(event);
            } catch (DukeException e) {
                command = new AddCommand(null);
            }
            break;
        case ("todo"):
            if (splitCommand.length == 1) {
                throw new DukeException("\tAaaarrrrgggghhhh todo description can't be empty matey!\n");
            }
            command = new AddCommand(new Todo(splitCommand[1]));
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry matey, but I don't know what that means :-(\n");
        }
        return command;
    }
}
