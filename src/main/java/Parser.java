import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exception.DukeException;

public class Parser {
    public static Command parse(String input) throws DukeException {
        String[] commandSeperated = input.split(" ", 2);
        if (input.strip().equals("list")) {
            return new ListCommand();
        }
        switch (commandSeperated[0]) {
            case "todo":
                if (commandSeperated.length == 1 || commandSeperated[1].isBlank()) {
                    throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
                }
                return new TodoCommand(commandSeperated[1].trim());
            case "event":
                if (commandSeperated.length == 1 || commandSeperated[0].isBlank() || commandSeperated[1].isBlank()) {
                    throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
                }
                String[] messageSeperated = commandSeperated[1].split("/at ", 2);
                if (messageSeperated.length == 1 || messageSeperated[1].isBlank()) {
                    throw new DukeException("Can't find the time! Have you typed it correctly?");
                }
                return new EventCommand(messageSeperated[0].trim(), messageSeperated[1].trim());
            case "deadline":
                if (commandSeperated.length == 1 || commandSeperated[0].isBlank() || commandSeperated[1].isBlank()) {
                    throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
                }
                messageSeperated = commandSeperated[1].split("/by ", 2);
                if (messageSeperated.length == 1 || messageSeperated[1].isBlank()) {
                    throw new DukeException("Can't find the time! Have you typed it correctly?");
                }
                return new DeadlineCommand(messageSeperated[0].trim(), messageSeperated[1].trim());
            case "mark":
                if (commandSeperated.length == 1 || commandSeperated[1].isBlank()) {
                    throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
                }
                try {
                    int serialNumber = Integer.parseInt(commandSeperated[1].trim());
                    return new MarkCommand(serialNumber);
                } catch (NumberFormatException e) {
                    throw new DukeException("Have you typed in your number correctly in numerals?");
                }
            case "unmark":
                if (commandSeperated.length == 1 || commandSeperated[1].isBlank()) {
                    throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
                }
                try {
                    int serialNumber = Integer.parseInt(commandSeperated[1].trim());
                    return new UnmarkCommand(serialNumber);
                } catch (NumberFormatException e) {
                    throw new DukeException("Have you typed in your number correctly in numerals?");
                }
            case "delete":
                if (commandSeperated.length == 1 || commandSeperated[1].isBlank()) {
                    throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
                }
                try {
                    int serialNumber = Integer.parseInt(commandSeperated[1].trim());
                    return new DeleteCommand(serialNumber);
                } catch (NumberFormatException e) {
                    throw new DukeException("Have you typed in your number correctly in numerals?");
                }
            default:
                throw new DukeException("Sorry, but I don't know what that means :-(");
        }
    }
}
