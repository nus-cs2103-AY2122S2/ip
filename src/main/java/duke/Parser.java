package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;


public class Parser {

    public static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm";

    /**
     * Getting required info from user input
     *
     * @param command User input
     * @return String list to store required info
     * @throws DukeException
     */
    public static String[] parse(String command) throws DukeException {

        HashSet<String> commandsWithArgs = new HashSet<>();
        commandsWithArgs.add("mark");
        commandsWithArgs.add("unmark");
        commandsWithArgs.add("delete");
        commandsWithArgs.add("todo");
        commandsWithArgs.add("deadline");
        commandsWithArgs.add("event");
        commandsWithArgs.add("find");

        if (commandsWithArgs.contains(command)) {
            String[] splitedcmd = command.split(" ");
            if (splitedcmd.length < 2) {
                String message = String.format("OOPS! "
                        + "The description of a %s cannot be empty.", command);
                throw new DukeException(message);
            }
        }

        if (command.equals("bye")) {
            String[] descriptions = new String[] { "bye"};
            return descriptions;
        } else if (command.equals("list")) {
            String[] descriptions = new String[] { "list" };
            return descriptions;
        } else if (command.startsWith("mark")) {
            String[] cmd = command.split(" ");
            String[] descriptions = new String[] { "mark", cmd[1]};
            return descriptions;
        } else if (command.startsWith("unmark")) {
            String[] cmd = command.split(" ");
            String[] descriptions = new String[] { "unmark", cmd[1] };
            return descriptions;
        } else if (command.startsWith("delete")) {
            String[] cmd = command.split(" ");
            String[] descriptions = new String[] { "delete", cmd[1] };
            return descriptions;
        } else if (command.startsWith("todo")) {
            String[] cmds = command.split("todo ");
            String[] descriptions = new String[] { "todo", cmds[1] };
            return descriptions;
        } else if (command.startsWith("deadline")) {
            String[] cmd = command.split("deadline ");
            String[] task = cmd[1].split("/by ");
            boolean isDateEmpty = task[1].isEmpty();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
            LocalDateTime deadlineDate = parseDateTime(task[1], dtf);
            if (deadlineDate == null || isDateEmpty) {
                throw new DukeException("'" + task[1] + "' is in wrong format! "
                        + "Please enter date and time as dd/MM/yyyy HH:mm");
            }
            String[] descriptions = new String[] { "deadline", task[0], task[1] };
            return descriptions;
        } else if (command.startsWith("event")) {
            String[] cmd = command.split("event ");
            String[] task = cmd[1].split("/at ");
            boolean isDateEmpty = task[1].isEmpty();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
            LocalDateTime deadlineDate = parseDateTime(task[1], dtf);
            if (deadlineDate == null || isDateEmpty) {
                throw new DukeException("'" + task[1] + "' is in wrong format! "
                        + "Please enter date and time as dd/MM/yyyy HH:mm");
            }
            String[] descriptions = new String[] { "event", task[0], task[1] };
            return descriptions;

        } else if (command.startsWith("find")) {
            String[] cmd = command.split("find ");
            String[] descriptions = new String[]{ "find", cmd[1]};
            return descriptions;
        } else if (command.startsWith("reminder")) {
            String[] cmd = command.split("reminder ");
            boolean isDateEmpty = cmd[1].isEmpty();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
            LocalDateTime deadlineDate = parseDateTime(cmd[1], dtf);
            if (deadlineDate == null || isDateEmpty) {
                throw new DukeException("'" + cmd[1] + "' is in wrong format! "
                        + "Please enter date and time as dd/MM/yyyy HH:mm");
            }
            String[] descriptions = new String[] { "reminder", cmd[1]};
            return descriptions;
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses date and time input by user and returns valid LocalDateTime
     *
     * @return date and time information
     */
    private static LocalDateTime parseDateTime(String s, DateTimeFormatter dtf) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(s, dtf);
            return dateTime;
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
