package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import duke.command.AddCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DukeCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.MarkCommand;
import duke.command.OutputCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Contains method to check validity of user input.
 */
public class Parser {
    /**
     * Checks the validity of the user input. If the user input is valid,
     * returns a Command which is then executed to perform its respective function.
     *
     * @param input User input.
     * @return Command which is then executed.
     * @throws DukeException If input is not valid.
     */
    public static Command parse(String input) throws DukeException {
        // If user did not input anything
        if (input.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please input a command");
        }
        assert !input.trim().isEmpty();
        String[] splitInput = input.split(" ", 2);
        // Check if the first word in the input is a valid command
        if (!DukeCommand.isDukeCommand(splitInput[0])) {
            throw new DukeException("I'm so very sorry,"
                    + " please make sure you enter a valid Ekud command");
            // Check if the command requires a description
        } else if (splitInput.length == 1) {
            if (DukeCommand.isDukeCommand(splitInput[0])
                    && DukeCommand.isDukeDescriptionCommand(splitInput[0])) {
                throw new DukeException("I'm so very sorry, the description of a "
                        + splitInput[0] + " cannot be empty.");
            }
            // Check correct usage of keywords for adding of event task
        } else if (splitInput[0].equals("event")) {
            if (!input.contains(" /at ")) {
                throw new DukeException("I'm so very sorry, "
                        + "please use the /at command for an event input");
            }
            if (input.split("event ")[1]
                    .split(" ", 2)[0].equals("/at")) {
                throw new DukeException("I'm so very sorry, the description of a "
                        + splitInput[0] + " cannot be empty.");
            }
            try {
                String eventDate = input.split("event ", 2)[1]
                        .split("/at ")[1];
                LocalDate.parse(eventDate, Task.getInputDateFormat());
            } catch (DateTimeParseException err) {
                throw new DukeException("Please enter a valid date! [dd/mm/yyyy]");
            }
            // Check correct usage of keywords for adding of deadline task
        } else if (splitInput[0].equals("deadline")) {
            if (!input.contains(" /by ")) {
                throw new DukeException("I'm so very sorry,"
                        + " please use the /by command for an event input");
            }
            if (input.split("deadline ")[1]
                    .split(" ", 2)[0].equals("/by")) {
                throw new DukeException("I'm so very sorry, the description of a "
                        + splitInput[0] + " cannot be empty.");
            }
            try {
                String deadlineDate = input.split("deadline ", 2)[1]
                        .split("/by ")[1];
                LocalDate.parse(deadlineDate, Task.getInputDateFormat());
            } catch (DateTimeParseException err) {
                throw new DukeException("Please enter a valid date! [dd/mm/yyyy]");
            }
            // Check if the index of task specified for task exist
        } else if (splitInput[0].equals("mark")
                || splitInput[0].equals("unmark")
                || splitInput[0].equals("delete")) {
            if (splitInput.length > 2) {
                throw new DukeException("I'm so very sorry, "
                        + "please make sure there is only "
                        + "one number following the "
                        + splitInput[0] + " command");
            }
            try {
                Integer.parseInt(splitInput[1]);
            } catch (NumberFormatException err) {
                throw new DukeException("☹ OOPS!!! Please make sure to input "
                        + "only one integer following the "
                        + splitInput[0] + " command");
            }
        }

        assert DukeCommand.isDukeCommand(splitInput[0]);
        // Get the respective command type and return its corresponding type
        HashMap<String, String> commandTypeMap = DukeCommand.getTaskTypeMap();
        String type = commandTypeMap.get(splitInput[0]);
        switch (type) {
        case "ADD_COMMAND":
            return new AddCommand(input);
        case "DELETE_COMMAND":
            return new DeleteCommand(input);
        case "MARK_COMMAND":
            return new MarkCommand(input);
        case "UNMARK_COMMAND":
            return new UnmarkCommand(input);
        case "OUTPUT_COMMAND":
            return new OutputCommand(input);
        case "EXIT_COMMAND":
            return new ExitCommand();
        case "FIND_COMMAND":
            return new FindCommand(input);
        case "CLEAR_COMMAND":
            return new ClearCommand();
        default:
            assert false : type;
            return null;
        }
    }
}
