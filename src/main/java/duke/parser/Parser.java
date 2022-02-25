package duke.parser;

import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.TodoCommand;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Parser {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_TODO = "todo";

    public static final String DEADLINE_DATE = " /by ";
    public static final String EVENT_DATE = " /at ";

    public static Command parse(String input) throws DukeException {
        StringTokenizer st = new StringTokenizer(input);
        String cmd = st.nextToken();

        switch (cmd) {
            case COMMAND_BYE:
                return new ExitCommand();
            case COMMAND_LIST:
                return new ListCommand();
            case COMMAND_DELETE:
                return new DeleteCommand(getIndex(st));
            case COMMAND_MARK:
                return new MarkCommand(getIndex(st));
            case COMMAND_UNMARK:
                return new UnmarkCommand(getIndex(st));
            case COMMAND_TODO:
                return new TodoCommand(validDescription(input.replaceFirst(cmd, "")));
            case COMMAND_DEADLINE:
                return parseDeadline(input.replaceFirst(cmd, ""));
            case COMMAND_EVENT:
                return parseEvent(input.replaceFirst(cmd, ""));
            default:
                throw new InvalidCommandException("Maybe try not trying to break my chatbot? thx xoxo");
        }
    }

    private static int getIndex(StringTokenizer st) throws DukeException {
        try {
            return Integer.parseInt(st.nextToken());
        } catch (NoSuchElementException e) {
            throw new DukeException("Maybe try specifyin' what task you want next time? jfc");
        }
    }

    private static DeadlineCommand parseDeadline(String description) throws DukeException {
        try {
            String[] deadlineTask = description.split(DEADLINE_DATE);
            return new DeadlineCommand(validDescription(deadlineTask[0]), deadlineTask[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please include a task deadline.");
        }
    }

    private static EventCommand parseEvent(String description) throws DukeException {
        try {
            String[] eventTask = description.split(EVENT_DATE);
            return new EventCommand(validDescription(eventTask[0]), eventTask[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please include an event date.");
        }
    }

    private static String validDescription(String description) throws DukeException{
        if (!description.isBlank()) {
            return description;
        } else {
            throw new DukeException("try adding sth? idk just a thot xoxo");
        }
    }
}
