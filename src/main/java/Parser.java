import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    // Input formats of date and times
    private static final DateTimeFormatter dateTimeIn = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter dateIn = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter timeIn = DateTimeFormatter.ofPattern("HHmm");

    // Output formats of date and times
    private static final DateTimeFormatter dateTimeOut = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    private static final DateTimeFormatter dateOut = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter timeOut = DateTimeFormatter.ofPattern("hh:mm a");

    private enum Format {
        DATE,
        TIME,
        DATETIME,
        INVALID
    }

    private static boolean isValidDateTime(String dt) {
        try {
            LocalDateTime.parse(dt, dateTimeIn);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private static boolean isValidDate(String d) {
        try {
            LocalDate.parse(d, dateIn);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private static boolean isValidTime(String t) {
        try {
            LocalTime.parse(t, timeIn);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private static Task prepareTodo(String request) throws DukeException {
        String[] parsedReq = request.strip().split(" ");
        if (parsedReq.length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            return new Todo(request.substring(5).strip());
        }
    }

    private static Task prepareDeadline(String request) throws DukeException {
        if (request.strip().length() == 8) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!request.contains(" /by ")) {
            throw new DukeException("You left the date/time of the deadline empty!");
        }

        String next = request.substring(8);
        String[] parsedReq = next.split("/by");
        String desc = parsedReq[0].strip();
        String by = parsedReq[1].strip();

        if (desc.length() == 0) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (by.length() == 0) {
            throw new DukeException("You left the date/time of the deadline empty!");
        } else {
            Parser.Format f = Parser.parseDateTime(by);
            switch (f) {
            case DATETIME:
                String[] s = by.split(" ");
                return new Deadline(desc, LocalDate.parse(s[0], dateIn), LocalTime.parse(s[1], timeIn));
            case DATE:
                return new Deadline(desc, LocalDate.parse(by, dateIn));
            case TIME:
                return new Deadline(desc, LocalTime.parse(by, timeIn));
            case INVALID:
            default:
                throw new InvalidDateTimeException();
            }
        }
    }

    private static Task prepareEvent(String request) throws DukeException {
        if (request.strip().length() == 5) {
            throw new DukeException("The description of an event cannot be empty.");
        } else if (!request.contains(" /at ")) {
            throw new DukeException("You left the date/time of the event empty!");
        }

        String next = request.substring(5);
        String[] parsedReq = next.split("/at");
        String desc = parsedReq[0].strip();
        String at = parsedReq[1].strip();

        if (desc.length() == 0) {
            throw new DukeException("The description of an event cannot be empty.");
        } else if (at.length() == 0 ) {
            throw new DukeException("You left the date/time of the event empty!");
        } else {
            Parser.Format f = Parser.parseDateTime(at);
            switch (f) {
            case DATETIME:
                String[] s = at.split(" ");
                return new Event(desc, LocalDate.parse(s[0], dateIn), LocalTime.parse(s[1], timeIn));
            case DATE:
                return new Event(desc, LocalDate.parse(at, dateIn));
            case TIME:
                return new Event(desc, LocalTime.parse(at, timeIn));
            case INVALID:
            default:
                throw new InvalidDateTimeException();
            }
        }
    }

    private static int prepareDelete(String request) throws DukeException {
        String[] parsedReq = request.split(" ");
        if (parsedReq.length != 2) {
            throw new DukeException("Please tell me which task you would like to delete.");
        } else {
            try {
                return Integer.parseInt(parsedReq[1]) - 1;
            } catch (NumberFormatException n) {
                throw new DukeException("Please enter a valid task number to delete!");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The task you specified does not exist. :(");
            }
        }
    }

    private static int prepareMark(String request) throws DukeException {
        String[] parsedReq = request.split(" ");
        if (parsedReq.length != 2) {
            throw new DukeException("Please tell me which task you would like to be marked as done.");
        } else {
            try {
                return Integer.parseInt(parsedReq[1]) - 1;
            } catch (NumberFormatException n) {
                throw new DukeException("Please enter a valid task number to mark as done!");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The task you specified does not exist. :(");
            }
        }
    }

    private static int prepareUnmark(String request) throws DukeException {
        String[] parsedReq = request.split(" ");
        if (parsedReq.length != 2) {
            throw new DukeException("Please tell me which task you would like to be marked as undone.");
        } else {
            try {
                return Integer.parseInt(parsedReq[1]) - 1;
            } catch (NumberFormatException n) {
                throw new DukeException("Please enter a valid task number to mark as undone!");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The task you specified does not exist. :(");
            }
        }
    }

    public static Command parseCommand(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            return new MarkCommand(prepareMark(input));
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(prepareUnmark(input));
        } else if (input.startsWith("todo")) {
            return new AddCommand(prepareTodo(input));
        } else if (input.startsWith("deadline")) {
            return new AddCommand(prepareDeadline(input));
        } else if (input.startsWith("event")) {
            return new AddCommand(prepareEvent(input));
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(prepareDelete(input));
        } else {
            throw new InvalidCommandException();
        }
    }

    public static Format parseDateTime(String input) {
        if (isValidDateTime(input)) {
            return Format.DATETIME;
        } else if (isValidTime(input)) {
            return Format.TIME;
        } else if (isValidDate(input)) {
            return Format.DATE;
        } else {
            return Format.INVALID;
        }
    }
}
