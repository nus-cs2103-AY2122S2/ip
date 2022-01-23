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
        String[] commandWords = input.split(" ", 2);
        if (input.strip().equals("list")) {
            return new ListCommand();
        }
        switch (commandWords[0]) {
            case "todo":
                if (commandWords.length == 1 || commandWords[1].isBlank()) {
                    throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
                }
                return new TodoCommand(commandWords[1].trim());
            case "event":
                if (commandWords.length == 1 || commandWords[0].isBlank() || commandWords[1].isBlank()) {
                    throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
                }
                String[] messageWords = commandWords[1].split("/at ", 2);
                if (messageWords.length == 1 || messageWords[1].isBlank()) {
                    throw new DukeException("Can't find the time! Have you typed it correctly?");
                }
                return new EventCommand(messageWords[0].trim(), messageWords[1].trim());
            case "deadline":
                if (commandWords.length == 1 || commandWords[0].isBlank() || commandWords[1].isBlank()) {
                    throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
                }
                messageWords = commandWords[1].split("/by ", 2);
                if (messageWords.length == 1 || messageWords[1].isBlank()) {
                    throw new DukeException("Can't find the time! Have you typed it correctly?");
                }
                return new DeadlineCommand(messageWords[0].trim(), messageWords[1].trim());
            case "mark":
                if (commandWords.length == 1 || commandWords[1].isBlank()) {
                    throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
                }
                try {
                    int serialNumber = Integer.parseInt(commandWords[1].trim());
                    return new MarkCommand(serialNumber);
                } catch (NumberFormatException e) {
                    throw new DukeException("Have you typed in your number correctly in numerals?");
                }
            case "unmark":
                if (commandWords.length == 1 || commandWords[1].isBlank()) {
                    throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
                }
                try {
                    int serialNumber = Integer.parseInt(commandWords[1].trim());
                    return new UnmarkCommand(serialNumber);
                } catch (NumberFormatException e) {
                    throw new DukeException("Have you typed in your number correctly in numerals?");
                }
            case "delete":
                if (commandWords.length == 1 || commandWords[1].isBlank()) {
                    throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
                }
                try {
                    int serialNumber = Integer.parseInt(commandWords[1].trim());
                    return new DeleteCommand(serialNumber);
                } catch (NumberFormatException e) {
                    throw new DukeException("Have you typed in your number correctly in numerals?");
                }
            default:
                throw new DukeException("Sorry, but I don't know what that means :-(");
        }
    }
}
