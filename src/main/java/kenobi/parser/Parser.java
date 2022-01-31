package kenobi.parser;

import kenobi.command.AddCommand;
import kenobi.command.Command;
import kenobi.command.DeleteCommand;
import kenobi.command.ExitCommand;
import kenobi.command.InvalidCommand;
import kenobi.command.ListCommand;
import kenobi.command.MarkCommand;
import kenobi.command.UnmarkCommand;
import kenobi.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static java.lang.Integer.parseInt;

public class Parser {
    public static Command parse(String str) throws ParseException {
        String[] cmd = str.split("\\s", 2);
        String[] fields;

        switch (cmd[0]) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            return new MarkCommand(parseInt(cmd[1]));

        case "unmark":
            return new UnmarkCommand(parseInt(cmd[1]));

        case "delete":
            return new DeleteCommand(parseInt(cmd[1]));

        case "todo":
            return new AddCommand(cmd[1]);

        case "deadline":
            try {
                fields = cmd[1].split(" /by ");
                return new AddCommand(Task.Type.DEADLINE, fields[0], LocalDate.parse(fields[1]));
            } catch (DateTimeParseException e) {
                throw new ParseException("date");
            }

        case "event":
            try{
                fields = cmd[1].split(" /at ");
                return new AddCommand(Task.Type.EVENT, fields[0], LocalDate.parse(fields[1]));
            } catch (DateTimeParseException e) {
                throw new ParseException("date");
            }
        }

        return new InvalidCommand();
    }
}
