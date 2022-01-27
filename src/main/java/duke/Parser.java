package duke;

import duke.command.*;
import duke.task.Tasks;
import duke.exception.DukeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    private static void checkIfUserInputValid(String userInput) throws DukeException {
        String str = userInput.trim();
        if (str.equals("todo") || str.equals("deadline") || str.equals("event")) {
            throw new DukeException(("OOPS!!! The description of a " + str + " cannot be empty."));
        }

        if (!(userInput.startsWith("list") || userInput.startsWith("mark") || userInput.startsWith("unmark")
            || userInput.startsWith("delete") || userInput.startsWith("todo") || userInput.startsWith("event")
            || userInput.startsWith("deadline") || userInput.equals("bye"))) {
            throw new DukeException("\"OOPS!!! I'm sorry, but I don't know what that means :-(\"");
        }
    }

    public static Command parseUserInput(String userInput) throws DukeException {
        checkIfUserInputValid(userInput);

        if (userInput.equals("list")) {
            return new ListCommand();
        }
        else if (userInput.startsWith("mark")) {
            String str = userInput.substring(5);
            int number = Integer.parseInt(str) - 1;
            return new MarkDoneCommand(number);
        }
        else if (userInput.startsWith("unmark")) {
            String str = userInput.substring(7);
            int number = Integer.parseInt(str) - 1;
            return new UnmarkDoneCommand(number);
        }

        else if (userInput.startsWith("delete")) {
            String indexStr = userInput.substring(7);
            int index = Integer.parseInt(indexStr);
            return new DeleteCommand(index);
        }
        else if (userInput.startsWith("todo")) {
            String description = userInput.substring(5);
            Tasks taskType = Tasks.TODO;
            return new AddCommand(taskType, description);
        }
        else if (userInput.startsWith("deadline")) {
            int start = userInput.indexOf("/");
            String timing = userInput.substring(start + 3);
            String description = userInput.substring(9, start - 1);
            DateTimeFormatter sourceFormat = DateTimeFormatter.ofPattern(" yyyy-MM-dd HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(timing, sourceFormat);
            Tasks taskType = Tasks.DEADLINE;
            return new AddCommand(taskType, description, dateTime);
        }
        else if (userInput.startsWith("event")) {
            int start = userInput.indexOf("/");
            String timing = userInput.substring(start + 3);
            String description = userInput.substring(6, start - 1);
            Tasks taskType = Tasks.EVENT;
            return new AddCommand(taskType, description, timing);
            }
        else if (userInput.startsWith("bye")) {
            return new ByeCommand();
        }
        else {
            return new ListCommand();
        }
    }
}
