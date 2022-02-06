package duke.ui;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

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
     * @param command the first word of the input sent to the chatbot
     * @return command object
     * @throws DukeException if an input containing todo only contains
     * todo or if the input contains unacceptable commands
     */
    public static Command parse(String command) throws DukeException {
        if (command.equals("todo") || command.equals("todo ")) {
            throw new DukeException("Todo cannot be empty");
        }
        if (command.equals("deadline") || command.equals("deadline ")) {
            throw new DukeException("Deadline cannot be empty");
        }
        if (command.equals("event") || command.equals("event ")) {
            throw new DukeException("Event cannot be empty");
        }

        if (command.equals("list")) {
            return new ListCommand();
        }
        if (command.equals("bye")) {
            return new ExitCommand();
        }

        String[] twoWords = command.split(" ", 2);
        String firstWord = twoWords[0];
        if (firstWord.equals("delete") || firstWord.contains("mark")) {
            int number = Integer.parseInt(twoWords[1]);
            if (firstWord.equals("delete")) {
                return new DeleteCommand(number);
            }

            if (firstWord.equals("mark")) {
                return new MarkCommand(number);
            }

            if (firstWord.equals("unmark")) {
                return new UnmarkCommand(number);
            }
        }

        if (firstWord.equals("find")) {
            String task = twoWords[1];
            return new FindCommand(task);
        }

        if (!firstWord.equals("deadline") && !firstWord.equals("todo") && !firstWord.equals("event")) {
            throw new DukeException("Unknown Command");
        }

        String details = twoWords[1];
        if (firstWord.equals("deadline")) {
            try {
                String date = details.split("/by ")[1];
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m");
                LocalDateTime.parse(date, format);
            }
            catch (DateTimeParseException e) {
                throw new DukeException("Wrong date format: Please re-enter using yyyy-mm-dd format");
            }
        }
        if (firstWord.equals("event")) {
            try {
                String date = details.split("/at ")[1];
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m");
                LocalDateTime.parse(date, format);
            }
            catch (DateTimeParseException e) {
                throw new DukeException("Wrong date format: Please re-enter using yyyy-mm-dd format");
            }
        }

        return new AddCommand(firstWord, details);
    }
}
