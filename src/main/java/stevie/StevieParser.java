package stevie;

import stevie.command.*;
import stevie.exception.StevieException;
import stevie.task.TaskCreator;
import stevie.task.TaskType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * StevieParser parses user's input to creates a Command. Command is used to alter the user's task list.
 */
public class StevieParser {
    /**
     * Parses a user's input to produce a command. A valid command is only produced if user's
     * input is complete. If not, an exception is thrown to inform user of missing information.
     *
     * @param userIn user's input
     * @return a command if a valid input is given
     * @throws StevieException if user's input is invalid or incomplete
     */
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
        } else if (Pattern.matches("^find\\s(.*?)", userIn)) {
            return new FindCommand(userIn.replace("find ", "").trim());
        }
        return parseAddTask(userIn);
    }

    /**
     * Parses a user's input to produce an AddCommand. A valid add command is only produced if user's
     * input contains the appropriate fields for the task. If not, an exception is thrown to inform
     * user of missing information.
     *
     * @param userIn user's input
     * @return an AddCommand if a valid input is given
     * @throws StevieException if user's input is invalid or incomplete
     */
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
