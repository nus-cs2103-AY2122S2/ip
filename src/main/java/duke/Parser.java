package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class Parser {

    public static Command parse(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please input a command");
        } else {
            String[] input_split = input.split(" ", 2);
            if (!DukeCommands.isDukeCommand(input_split[0])) {
                throw new DukeException("I'm so very sorry,"
                        + " please make sure you enter a valid Ekud command");
            } else if (input_split.length == 1) {
                if (DukeCommands.isDukeCommand(input_split[0])
                        && DukeCommands.isDukeDescriptionCommand(input_split[0])) {
                    throw new DukeException("I'm so very sorry, the description of a "
                            + input_split[0] + " cannot be empty.");
                }
            } else if (input_split[0].equals("event")) {
                if (!input.contains("/at")) {
                    throw new DukeException("I'm so very sorry, "
                            + "please use the /at command for an event input");
                }
                if (input.split("event ")[1]
                        .split(" ", 2)[0].equals("/at")) {
                    throw new DukeException("I'm so very sorry, the description of a "
                            + input_split[0] + " cannot be empty.");
                }
                try {
                    String event_date = input.split("event ", 2)[1]
                            .split("/at ")[1];
                    LocalDate.parse(event_date, Task.getInputDateFormat());
                } catch (DateTimeParseException err) {
                    throw new DukeException("Please enter a valid date! [dd/mm/yyyy]");
                }
            } else if (input_split[0].equals("deadline")) {
                if (!input.contains("/by")) {
                    throw new DukeException("I'm so very sorry,"
                            + " please use the /by command for an event input");
                }
                if (input.split("deadline ")[1]
                        .split(" ", 2)[0].equals("/by")) {
                    throw new DukeException("I'm so very sorry, the description of a "
                            + input_split[0] + " cannot be empty.");
                }
                try {
                    String deadline_date = input.split("deadline ", 2)[1]
                            .split("/by ")[1];
                    LocalDate.parse(deadline_date, Task.getInputDateFormat());
                } catch (DateTimeParseException err) {
                    throw new DukeException("Please enter a valid date! [dd/mm/yyyy]");
                }
            } else if (input_split[0].equals("mark")
                    || input_split[0].equals("unmark")
                    || input_split[0].equals("delete")) {
                if (input_split.length > 2) {
                    throw new DukeException("I'm so very sorry, "
                            + "please make sure there is only "
                            + "one number following the "
                            + input_split[0] + " command");
                }
                try {
                    Integer.parseInt(input_split[1]);
                } catch (NumberFormatException err) {
                    throw new DukeException("☹ OOPS!!! Please make sure to input "
                            + "only one integer following the "
                            + input_split[0] + " command");
                }
            }
        }
        HashMap<String, String> commandTypeMap = DukeCommands.getTypeMap();
        String type = commandTypeMap.get(input.split(" ", 2)[0]);
        switch (type) {
        case "ADD_COMMAND":
            return new AddCommand(input);
        case "DELETE_COMMAND":
            return new DeleteCommand(input);
        case "MARK_COMMAND":
            return new MarkCommand(input);
        case "UNMARK_COMMAND":
            return new UnMarkCommand(input);
        case "OUTPUT_COMMAND":
            return new OutputCommand(input);
        case "EXIT_COMMAND":
            return new ExitCommand();
        default:
            return null;
        }
    }
}
