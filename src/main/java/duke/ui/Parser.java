package duke.ui;

import duke.command.AddCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.HelpCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deals with making sense of the user's input.
 */
public class Parser {

    /**
     * Returns a Command object which nature depends on
     * the type of command input.
     *
     * @param userInput the first word of the input sent to the chatbot
     * @return command object
     * @throws DukeException if an input containing todo only contains
     *                       todo or if the input contains unacceptable commands
     */
    public static Command parse(String userInput) throws DukeException {
        if (userInput.equals("list")) {
            return new ListCommand();
        }
        if (userInput.equals("bye")) {
            return new ExitCommand();
        }
        if (userInput.equals("help")) {
            return new HelpCommand();
        }
        if (userInput.equals("clear")) {
            return new ClearCommand();
        }
        checkForEmptyOrIncompleteCommands(userInput);
        String[] twoWords = userInput.split(" ", 2);
        String firstWord = twoWords[0];
        if (firstWord.equals("delete") || firstWord.contains("mark")) {
            try {
                int number = Integer.parseInt(twoWords[1]);
                return commandsWithNumbers(firstWord, number);
            } catch (NumberFormatException numberFormatException) {
                throw new DukeException("Wrong format: Number must follow the Command !");
            }
        }
        if (firstWord.equals("find")) {
            String task = twoWords[1];
            return new FindCommand(task);
        }
        checkForUnknownCommand(firstWord);
        String date = twoWords[1];
        checkForWrongDateInput(firstWord, date);
        return new AddCommand(firstWord, date);
    }

    /**
     * Checks for inputs that are either empty or are missing details.
     * Incomplete commands only applies to commands execpt for list and bye.
     *
     * @param command user input
     * @throws DukeException throws "Empty Command" or "XXX cannot be empty"
     */
    public static void checkForEmptyOrIncompleteCommands(String command) throws DukeException {
        if (command.equals("")) {
            throw new DukeException("Empty Command");
        }
        if (command.equals("todo") || command.equals("todo ")) {
            throw new DukeException("Todo cannot be empty");
        }
        if (command.equals("deadline") || command.equals("deadline ")) {
            throw new DukeException("Deadline cannot be empty");
        }
        if (command.equals("event") || command.equals("event ")) {
            throw new DukeException("Event cannot be empty");
        }
        if (command.equals("find") || command.equals("find ")) {
            throw new DukeException("Find cannot be empty");
        }
        if (command.equals("delete") || command.equals("delete ")) {
            throw new DukeException("Delete cannot be empty");
        }
        if (command.equals("mark") || command.equals("unmark")) {
            throw new DukeException("Mark/Unmark cannot be empty");
        }
    }

    /**
     * Checks for inputs that are not coded for.
     *
     * @param command User input
     * @throws DukeException throws "Unknown Command"
     */
    public static void checkForUnknownCommand(String command) throws DukeException {
        if (!command.equals("deadline") && !command.equals("todo") && !command.equals("event")) {
            throw new DukeException("Unknown Command");
        }
    }

    /**
     * Checks for incorrect date formats especially for event and deadline commands.
     *
     * @param command event or deadline commands
     * @param details date
     * @throws DukeException throws "Wrong date format: Please re-enter using yyyy-mm-dd format"
     */
    public static void checkForWrongDateInput(String command, String details) throws DukeException {
        if (command.equals("deadline")) {
            try {
                String date = details.split("/by ")[1];
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m");
                LocalDateTime.parse(date, format);
            } catch (DateTimeParseException e) {
                throw new DukeException("Wrong date format: Please re-enter using yyyy-mm-dd H:m format");
            }
        }
        if (command.equals("event")) {
            try {
                String date = details.split("/at ")[1];
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m");
                LocalDateTime.parse(date, format);
            } catch (DateTimeParseException e) {
                throw new DukeException("Wrong date format: Please re-enter using yyyy-mm-dd H:m format");
            }
        }
    }

    /**
     * Returns a 'Delete','Mark' or 'Unmark' command depending on
     * the given input
     *
     * @param command user input
     * @param number  number input beside the command
     * @return Delete/Mark/Unmark command
     * @throws DukeException throws 'Command has no number'
     */
    public static Command commandsWithNumbers(String command, int number) throws DukeException {
        switch (command) {
        case "delete":
            return new DeleteCommand(number);
        case "mark":
            return new MarkCommand(number);
        case "unmark":
            return new UnmarkCommand(number);
        default:
            throw new DukeException("Command has no number");
        }
    }
}
