package java.duke.ui;

import java.duke.command.AddCommand;
import java.duke.command.Command;
import java.duke.command.DeleteCommand;
import java.duke.command.ExitCommand;
import java.duke.command.FindCommand;
import java.duke.command.ListCommand;
import java.duke.command.MarkCommand;
import java.duke.command.UnmarkCommand;

//deals with making sense of the user duke.command
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
        return new AddCommand(firstWord, details);
    }
}
