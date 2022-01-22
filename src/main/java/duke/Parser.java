package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.UnmarkCommand;
import duke.command.MarkCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

//deals with making sense of the user duke.command
public class Parser {

    public static Command parse(String s) throws DukeException {
        if (s.equals("todo") || s.equals("todo ")) {
            throw new DukeException("Todo cannot be empty");
        }

        if (s.equals("list")) {
            return new ListCommand();
        }

        if (s.equals("bye")) {
            return new ExitCommand();
        }

        String[] twoWords = s.split(" ", 2);
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

        if (!firstWord.equals("deadline") && !firstWord.equals("todo") && !firstWord.equals("event")) {
            throw new DukeException("Unknown Command");
        }

        String details = twoWords[1];
        return new AddCommand(firstWord, details);
    }
}
