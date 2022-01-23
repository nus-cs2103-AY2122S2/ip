package duke;

import duke.command.*;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] splited = input.split(" ", 2);
        String firstWord = splited[0];
        String remaining = "";
        if (splited.length == 2) {
            remaining = splited[1];
        }
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.matches("delete [1-9]+\\d*")) {
            return new DeleteCommand(Integer.parseInt(remaining));
        } else if (input.matches("mark [1-9]+\\d*")) {
            return new MarkCommand(Integer.parseInt(remaining));
        } else if (input.matches("unmark [1-9]+\\d*")) {
            return new UnmarkCommand(Integer.parseInt(remaining));
        } else if (firstWord.equals("todo")) {
            if (remaining.equals("")) {
                throw new DukeException("The description of a todo cannot be empty");
            }
            return new TodoCommand(remaining);
        } else if (firstWord.equals("deadline")) {
            String[] desc_by = remaining.split(" /by ", 2);
            if (desc_by.length < 2) {
                throw new DukeException("Must specify a deadline");
            } else if (desc_by[0].length() == 0) {
                throw new DukeException("The description of a deadline cannot be empty");
            }
            try {
                DukeDateTime datetime = DukeDateTime.parse(desc_by[1]);
                return new DeadlineCommand(desc_by[0], datetime);
            } catch (Exception e) {
                throw new DukeException("Unrecognized time format. Valid formats:\nyyyy-M-d\nyyyy-M-d H:mm");
            }
        } else if (firstWord.equals("event")) {
            String[] desc_at = remaining.split(" /at ", 2);
            if (desc_at.length < 2) {
                throw new DukeException("Must specify the time of the event");
            } else if (desc_at[0].length() == 0) {
                throw new DukeException("The description of an event cannot be empty");
            }
            try {
                DukeDateTime datetime = DukeDateTime.parse(desc_at[1]);
                return new EventCommand(desc_at[0], datetime);
            } catch (Exception e) {
                throw new DukeException("Unrecognized time format. Valid formats:\nyyyy-M-d\nyyyy-M-d H:mm");
            }
        } else {
            return new UnrecognizedCommand();
        }
    }

}
