import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class StevieParser {
    public static Command parse(String userIn) throws StevieException {
        if (userIn.equals("bye")) {
            return new ExitCommand();
        } else if (userIn.equals("list")) {
            return new ListCommand();
        } else if (Pattern.matches("^mark\\s\\d+", userIn)) {
            return new MarkCommand(true, Integer.parseInt(userIn
                    .replaceAll("[^\\d.]", "")) - 1);
        } else if (Pattern.matches("^unmark\\s\\d+", userIn)) {
            return new MarkCommand(false, Integer.parseInt(userIn
                    .replaceAll("[^\\d.]", "")) - 1);
        } else if (Pattern.matches("^delete\\s\\d+", userIn)) {
            return new DeleteCommand(Integer.parseInt(userIn
                    .replaceAll("[^\\d.]", "")) - 1);
        }
        return parseAddTask(userIn);
    }

    private static Command parseAddTask(String userIn) throws StevieException {
        if (Pattern.matches("^todo\\s(.*?)", userIn)) {
            String s = userIn.replace("todo ", "").trim();
            if (s.length() == 0) {
                throw new StevieException("Todo task requires a task name!");
            } else {
                return new AddCommand(TaskCreator.create(TaskType.Todo, false, s));
            }
        } else if (Pattern.matches("^deadline\\s(.*s?)\\s/by\\s(.*s?)", userIn)) {
            String[] split = userIn
                    .replace("deadline ", "")
                    .split("\\s/by\\s", 2);
            split[0] = split[0].trim();
            split[1] = split[1].trim();
            if (split[0].length() == 0 || split[1].length() == 0) {
                throw new StevieException("Deadline task requires a task name and a date!");
            } else {
                Date date;
                try {
                    date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(split[1]);
                } catch (ParseException ex) {
                    throw new StevieException("Date format is unacceptable!");
                }
                return new AddCommand(TaskCreator.create(TaskType.Deadline, false, split[0], date));
            }
        } else if (Pattern.matches("^event\\s(.*s?)\\s/at\\s(.*s?)", userIn)) {
            String[] split = userIn
                    .replace("event ", "")
                    .split("\\s/at\\s", 2);
            split[0] = split[0].trim();
            split[1] = split[1].trim();
            if (split[0].length() == 0 && split[1].length() == 0) {
                throw new StevieException("Event task requires a task name and a date!");
            } else {
                Date date;
                try {
                    date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(split[1]);
                } catch (ParseException ex) {
                    throw new StevieException("Date format is unacceptable!");
                }
                return new AddCommand(TaskCreator.create(TaskType.Event, false, split[0], date));
            }
        } else {
            throw new StevieException("Oops! Your instructions were unclear!");
        }
    }
}
