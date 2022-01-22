import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public class Parser {

    private static final int EVENT_OFFSET = 5;
    private static final int TODO_OFFSET = 4;
    private static final int DEADLINE_OFFSET = 8;
    private static final int INPUT_OFFSET = 3;

    public static String formatTime(String input){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HHmm");
        DateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy hh:mm aa");
        Date date;
        String output = "";
        try {
            date = df.parse(input);
            output = outputFormat.format(date);
        } catch (ParseException e) {
            e.getMessage();
        }
        return output;
    }

    public Command parse(String input) throws IncompleteCommandException, InvalidCommandException {
        String[] inputSplit = input.split(" "); //split input by space
        String command = inputSplit[0];
        if (command.equals("bye")) {
            return new ExitCommand();

        } else if (command.equals("list")) {
            return new PrintCommand();

        } else if (command.equals("todo")) {
            String description = input.substring(TODO_OFFSET).trim();

            if (description.length() == 0) {
                throw new IncompleteCommandException(command);
            }
            return new AddCommand(new Todo(description));

        } else if (command.equals("deadline")) {
            String[] inputSlash = input.split("/");
            String description = inputSlash[0].substring(DEADLINE_OFFSET).trim();

            if (description.length() == 0) {
                throw new IncompleteCommandException(command);
            }

            String timeInput = inputSlash[1].substring(INPUT_OFFSET);
            String time = formatTime(timeInput);
            String duration;

            if (time.equals("")) {
                duration = timeInput;
            } else {
                duration = time;
            }
            return new AddCommand(new Deadline(description, duration));

        } else if (command.equals("event")) {
            String[] inputSlash = input.split("/");
            String description = inputSlash[0].substring(EVENT_OFFSET).trim();

            if (description.length() == 0) {
                throw new IncompleteCommandException(command);
            }

            String timeInput = inputSlash[1].substring(INPUT_OFFSET);
            String time = formatTime(timeInput);
            String duration;

            if (time.equals("")) {
                duration = timeInput;
            } else {
                duration = time;
            }
            return new AddCommand(new Event(description, duration));

        } else if (command.equals("mark")) {
            return new MarkCommand(Integer.parseInt(inputSplit[1]));

        } else if (command.equals("unmark")) {
            return new UnmarkCommand(Integer.parseInt(inputSplit[1]));

        } else if (command.equals("delete")) {
            return new DeleteCommand(Integer.parseInt(inputSplit[1]));

        } else {
            throw new InvalidCommandException();
        }
    }
}
