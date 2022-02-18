package Duke.Processing;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import Duke.Exception.DukeException;

public class Parser {

    static final String DATEEROR = "Wrong date time format\n";
    /**
     * Converts the String for a new task to a date
     *
     * @param  date the String the user inputs
     * @return      the String in a date format the system can recognise
     */
    public static LocalDateTime convertDate(String date) throws DukeException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(date, format);
        } catch (DateTimeParseException error) {
            throw new DukeException(DATEEROR);
        }
    }

    /**
     * Converts the String for a previously created task
     *
     * @param  date the String the user inputs
     * @return      the String in a date format the system can recognise
     */
    public static LocalDateTime convertDate2(String date) throws DukeException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE dd/MM/yyyy HHmm");
            return LocalDateTime.parse(date, format);
        } catch (DateTimeParseException error) {
            throw new DukeException(DATEEROR);
        }
    }
    /**
     * Takes in the input command and the Tasklist to modify it accordingly.
     *
     * @param  input the type of command the user has input
     * @param  tasklist the current Tasklist that contains tasks.
     * @return A string output
     */

    public static String parse(String input, TaskList tasklist) throws DukeException{
        String output;
        try {
            if(input.equalsIgnoreCase("list")) {
                output = tasklist.list();
            } else if(input.startsWith("todo")) {
                String item = input.substring(5);
                output = tasklist.addTodo(item);
            } else if(input.startsWith("deadline")) {
                String item = input.substring(9);
                String[] split = item.split(" /by ");
                output = tasklist.addDeadline(split);
            } else if (input.startsWith("event")) {
                String item = input.substring(6);
                String[] split = item.split(" /at ");
                output = tasklist.addEvent(split);
            } else if (input.startsWith("mark")) {
                String num = input.substring(5);
                output = tasklist.mark(num);
            } else if (input.startsWith("unmark")) {
                String num = input.substring(7);
                output = tasklist.unmark(num);
            } else if (input.startsWith("delete")) {
                String num = input.substring(7);
                output = tasklist.delete(num);
            } else if (input.startsWith("F find")) {
                String name = input.substring(7);
                output = tasklist.findS(name);
            } else if (input.equals("clear")) {
                output = tasklist.clear();
            } else if (input.equalsIgnoreCase("help")) {
                output = tasklist.help();
            } else {
                throw new DukeException("I'm not sure we can do that");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Are you sure that's the correct number");
        }
        return  output;
    }
}
