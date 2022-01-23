import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Command {

    public static Deadline createDeadline(String[] inputArgs) throws DukeException {
        if (inputArgs.length < 2 || inputArgs[1].isBlank()) {
            throw new DukeException("Time and Description of a deadline task cannot be empty.");
        }

        String[] oargs = inputArgs[1].split("/", 2);

        if (oargs.length < 2 || oargs[1].isBlank() || !oargs[1].startsWith("by")) {
            throw new DukeException("Invalid/Missing suffix, format is 'deadline [message] /by [date/time]'.");
        }

        String[] dateTimeArgs = oargs[1].substring(3).split(" ");

        return new Deadline(oargs[0].trim(), convertToDate(dateTimeArgs[0]), convertToTime(dateTimeArgs[1]));
    }

    public static LocalDate convertToDate(String input) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate ld = LocalDate.parse(input, formatter);

        return ld;
    }

    public static LocalTime convertToTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime lt = LocalTime.parse(input, formatter);

        return lt;
    }

    public static Todo createTodo(String[] inputArgs) throws DukeException {
        if (inputArgs.length < 2 || inputArgs[1].isBlank()) {
            throw new DukeException("Description of a todo task cannot be empty.");
        }

        return new Todo(inputArgs[1]);
    }

    public static Task createEvent(String[] inputArgs) throws DukeException {
        if (inputArgs.length < 2 || inputArgs[1].isBlank()) {
            throw new DukeException("Time and Description of a event task cannot be empty.");
        }

        String[] oargs = inputArgs[1].split("/");

        if (oargs.length < 2 || oargs[1].isBlank() || !oargs[1].startsWith("at")) {
            throw new DukeException("Invalid/Missing suffix, format is 'event [message] /at [date/time]'.");
        }

        String[] dateTimeArgs = oargs[1].substring(3).split(" ");

        String[] timeArgs = dateTimeArgs[1].split("-");

        return new Event(oargs[0].trim(), convertToDate(dateTimeArgs[0]), convertToTime(timeArgs[0]), convertToTime(timeArgs[1]));
    }

    public static void checkSingle(String[] inputArgs) throws DukeException {
        if (inputArgs.length > 1) {
            throw new DukeException("Invalid Command!");
        }
    }
}
