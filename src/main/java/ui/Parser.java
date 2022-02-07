package ui;

import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exception.DukeException;

/** Parses user input to find the correct command and the correct arguments. */
public class Parser {

    /**
     * Returns the appropriate Command with appropriate arguments according to user input.
     * @param input Input that determines command type and parameters.
     * @return Command that will be subsequently executed.
     * @throws DukeException If input message does not make sense.
     */
    public static Command parse(String input) throws DukeException {
        String[] words = input.split(" ", 2);
        String commandWord = words[0];
        String message = "";
        if (words.length > 1) {
            message = words[1].trim();
        }

        switch (commandWord) {

        case "list":
            return new ListCommand();

        case "todo":
            if (message.isBlank()) {
                throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
            }
            Integer priority = null;
            if (message.matches(".* :p[1-4]")) {
                priority = Integer.parseInt(message.substring(message.length() - 1));
                message = message.split(" :p[1-4]", 2)[0].trim();
            }
            return new TodoCommand(message, priority);

        case "event":
            if (message.isBlank()) {
                throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
            }

            priority = null;
            if (message.matches(".* :p[1-4]")) {
                priority = Integer.parseInt(message.substring(message.length() - 1));
                message = message.split(" :p[1-4]", 2)[0].trim();
            }
            String[] messageWords = message.split("/at ", 2);
            if (messageWords.length == 1 || messageWords[1].isBlank()) {
                throw new DukeException("Can't find the time! Have you typed it correctly?");
            }

            return new EventCommand(messageWords[0].trim(), messageWords[1].trim(), priority);

        case "deadline":
            if (message.isBlank()) {
                throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
            }

            priority = null;
            if (message.matches(".* :p[1-4]")) {
                priority = Integer.parseInt(message.substring(message.length() - 1));
                message = message.split(" :p[1-4]", 2)[0].trim();
            }
            messageWords = message.split("/by ", 2);
            if (messageWords.length == 1 || messageWords[1].isBlank()) {
                throw new DukeException("Can't find the time! Have you typed it correctly?");
            }
            return new DeadlineCommand(messageWords[0].trim(), messageWords[1].trim(), priority);

        case "mark":
            int serialNumber;
            if (message.isBlank()) {
                throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
            }
            try {
                serialNumber = Integer.parseInt(message);
            } catch (NumberFormatException e) {
                throw new DukeException("Have you typed in your number correctly in numerals?");
            }
            return new MarkCommand(serialNumber);

        case "unmark":
            if (message.isBlank()) {
                throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
            }
            try {
                serialNumber = Integer.parseInt(message);
            } catch (NumberFormatException e) {
                throw new DukeException("Have you typed in your number correctly in numerals?");
            }
            return new UnmarkCommand(serialNumber);

        case "delete":
            if (message.isBlank()) {
                throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
            }
            try {
                serialNumber = Integer.parseInt(message);
            } catch (NumberFormatException e) {
                throw new DukeException("Have you typed in your number correctly in numerals?");
            }
            return new DeleteCommand(serialNumber);

        case "find":
            if (message.isBlank()) {
                throw new DukeException("Can't find any info after your command! Have you typed it correctly?");
            }
            return new FindCommand(message);

        default:
            throw new DukeException("Sorry, but I don't know what that means :-(");
        }
    }
}
