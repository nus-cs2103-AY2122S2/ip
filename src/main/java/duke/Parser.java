package duke;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;


public class Parser {
    private Ui ui;

    public Parser() {
        ui = new Ui();
    }

    /**
     * Parses user input into their respective commands.
     * Creates different Command type objects which is executed to perform its tasks.
     *
     * @param userTaskString
     * @return Command Object
     * @throws DukeException
     */
    public Command parseUserCommand(String userTaskString) throws DukeException {
        if (userTaskString.equalsIgnoreCase("bye")) {
            return new ByeCommand(userTaskString);
        } else if (userTaskString.equalsIgnoreCase("list")) {
            return new ListCommand(userTaskString);
        } else if (userTaskString.matches("^(delete|Delete|DELETE).*")) {
            try {
                int taskIdx = Integer.parseInt(userTaskString.substring(6 + 1)) - 1;
                return new DeleteCommand(userTaskString, taskIdx);
                //+1 is to take into account the " "after delete, -1 is to convert it into 0-based indexing
            } catch (StringIndexOutOfBoundsException err) { // For cases like "delete" without a number
                ui.throwDukeException("Please enter a task number to delete!");
            } catch (NumberFormatException err) {
                // For cases like "delete1" without a space in between or "delete 3b" where the term is not a number
                ui.throwDukeException("Please delete a task in the following format:\n"
                        + "delete [number]");
            }
        } else if (userTaskString.matches("^(mark|Mark|MARK).*")) {
            try {
                int taskIdx = Integer.parseInt(userTaskString.substring(4 + 1)) - 1;
                return new MarkCommand(userTaskString, taskIdx);
                //+1 is to take into account the " "after mark, -1 is to convert it into 0-based indexing
            } catch (StringIndexOutOfBoundsException err) { // For cases like "mark" without a number
                ui.throwDukeException("Please enter a task number to mark!");
            } catch (NumberFormatException err) {
                // For cases like "mark1" without a space in between or "mark 3b" where the term is not a number
                ui.throwDukeException("Please mark a task in the following format:\n"
                        + "mark [number]");
            }
        } else if (userTaskString.matches("^(unmark|Unmark|UNMARK).*")) {
            try {
                int taskIdx = Integer.parseInt(userTaskString.substring(6 + 1)) - 1;
                return new UnmarkCommand(userTaskString, taskIdx);
                //+1 is to take into account the " "after unmark, -1 is to convert it into 0-based indexing
            } catch (StringIndexOutOfBoundsException err) { // For cases like "unmark" without a number
                ui.throwDukeException("Please enter a task number to unmark!");
            } catch (NumberFormatException err) {
                // For cases like "unmark1" without a space in between or "unmark 3b" where the term is not a number
                ui.throwDukeException("Please unmark a task in the following format:\n"
                        + "unmark [number]");
            }
        } else if (userTaskString.matches("^(todo|Todo|TODO).*")) {
            try {
                Todo userTodo = new Todo(userTaskString.substring(4 + 1));
                return new AddCommand(userTaskString, userTodo);
            } catch (StringIndexOutOfBoundsException err) { //For cases like "todo" without any further description
                ui.throwDukeException("Please enter a description!");
            }
        } else if (userTaskString.matches("^(deadline|Deadline|DEADLINE).*")) {
            try {
                String userTaskStringSliced = userTaskString.substring(8 + 1);
                String[] descriptionDeadlineSplit = userTaskStringSliced.split(" /by ", 2);
                Deadline userDeadline = new Deadline(descriptionDeadlineSplit[0], descriptionDeadlineSplit[1]);
                return new AddCommand(userTaskString, userDeadline);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | DateTimeParseException err) {
                ui.throwDukeException("Please enter a Deadline in the following format:\n"
                        + "deadline [description] /by [yyyy-mm-dd]");
                //StringIndexOutOfBoundsException For cases like "deadline" without any other information
                //ArrayIndexOutOfBoundsException For cases like "deadline homework" without a "/by"
                //DateTimeParseException For cases where date cannot be recognised
            }
        } else if (userTaskString.matches("^(event|Event|EVENT).*")) {
            try {
                String userTaskStringSliced = userTaskString.substring(5 + 1);
                String[] descriptionTimingSplit = userTaskStringSliced.split(" /at ", 2);
                Event userEvent = new Event(descriptionTimingSplit[0], descriptionTimingSplit[1]);
                return new AddCommand(userTaskString, userEvent);
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException err) {
                // For cases like "event" without any other information
                ui.throwDukeException("Please enter an Event in the following format:\n"
                        + "event [description] /at [datetime]");
            }
        } else if (userTaskString.matches("^(find|Find|FIND).*")) {
            try {
                String userFindTask = userTaskString.substring(4 + 1);
                return new FindCommand(userTaskString, userFindTask);
            } catch (StringIndexOutOfBoundsException err) { //For cases like "find" without any further description
                ui.throwDukeException("Please enter a description!");
            }
        }
        return new InvalidCommand(userTaskString);
    }
}
