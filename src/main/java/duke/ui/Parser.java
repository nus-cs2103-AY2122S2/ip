package duke.ui;

import duke.command.AddCommand;
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
     * @param userInput the first word of the input sent to the chatbot
     * @return command object
     * @throws DukeException if an input containing todo only contains
     * todo or if the input contains unacceptable commands
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
        //Checks for user inputs that are empty
        //or are todo/deadline/event commands
        //without a specified task.
        checkForEmptyCommands(userInput);

        String[] twoWords = userInput.split(" ", 2);
        String firstWord = twoWords[0];
        if (firstWord.equals("delete") || firstWord.contains("mark")) {
            int number = Integer.parseInt(twoWords[1]);
            return commandsWithNumbers(firstWord, number);
        }
        //Checks for commands not known to the
        //parser.
        checkForUnknownCommand(firstWord);

        String task = twoWords[1];
        if (firstWord.equals("find")) {
            return new FindCommand(task);
        }
        //Checks for date inputs of the wrong format
        String date = task;
        checkForWrongDateInput(firstWord, date);

        return new AddCommand(firstWord, date);
    }

    public static void checkForEmptyCommands(String command) throws DukeException {
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
    }

    public static void checkForUnknownCommand(String command) throws DukeException {
        if (!command.equals("deadline") && !command.equals("todo") && !command.equals("event")) {
            throw new DukeException("Unknown Command");
        }
    }

    public static void checkForWrongDateInput(String command, String details) throws DukeException {
        if (command.equals("deadline")) {
            try {
                String date = details.split("/by ")[1];
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m");
                LocalDateTime.parse(date, format);
            }
            catch (DateTimeParseException e) {
                throw new DukeException("Wrong date format: Please re-enter using yyyy-mm-dd format");
            }
        }
        if (command.equals("event")) {
            try {
                String date = details.split("/at ")[1];
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m");
                LocalDateTime.parse(date, format);
            }
            catch (DateTimeParseException e) {
                throw new DukeException("Wrong date format: Please re-enter using yyyy-mm-dd format");
            }
        }
    }

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
