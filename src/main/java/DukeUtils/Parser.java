package DukeUtils;
import Command.*;
import Task.Deadline;
import Task.Event;
import Task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Parser {
    static boolean isExit;
    static boolean isListCommand;
    static boolean isMarkCommand;
    static boolean isUnmarkCommand;
    static boolean isDeleteCommand;
    static boolean isDeleteAllCommand;
    static boolean isShowAllOnSameDate;
    static boolean isEmptyDeadline;
    static boolean isEmptyEvent;
    static boolean isEmptyTodo;
    static boolean isNotEmptyDeadline;
    static boolean isNotEmptyEvent;
    static boolean isNotEmptyTodo;

    public Parser() {}

    public static Command parse(String input) throws CortanaException {
        isExit = input.toLowerCase().replaceAll("[ |\\t]", "").equals("bye");
        isListCommand = input.toLowerCase().replaceAll("[ |\\t]", "").equals("list");
        isMarkCommand = input.toLowerCase().matches("^mark \\d+|^mark -\\d+");
        isUnmarkCommand = input.toLowerCase().matches("^unmark \\d+|^unmark -\\d+");
        isDeleteCommand = input.toLowerCase().matches("^delete \\d+|^delete -\\d+");
        isDeleteAllCommand = input.toLowerCase().matches("^delete all");
        isShowAllOnSameDate = input.toLowerCase().matches("^show all( \\d{4}-\\d{1,2}-\\d{1,2})?( \\d{4})?");
        isEmptyDeadline = input.toLowerCase().replaceAll("[ |\\t]", "").equals("deadline");
        isEmptyEvent = input.toLowerCase().replaceAll("[ |\\t]", "").equals("event");
        isEmptyTodo = input.toLowerCase().replaceAll("[ |\\t]", "").equals("todo");
        isNotEmptyDeadline = input.toLowerCase().matches("^deadline .*");
        isNotEmptyEvent = input.toLowerCase().matches("^event .*");
        isNotEmptyTodo = input.toLowerCase().matches("^todo .*");
        if (isExit) {
            return new ExitCommand(true);
        } else {
            if (isListCommand) {
                return new ListCommand();
            } else if (isMarkCommand) {
                int index = Integer.parseInt(input.replaceAll("mark ", "")) - 1;
                return new MarkCommand(index);
            } else if (isUnmarkCommand) {
                int index = Integer.parseInt(input.replaceAll("unmark ", "")) - 1;
                return new UnmarkCommand(index);
            } else if (isDeleteCommand) {
                int index = Integer.parseInt(input.replaceAll("delete ", "")) - 1;
                return new DeleteCommand(index);
            } else if (isDeleteAllCommand) {
                return new DeleteAllCommand();
            } else if (isShowAllOnSameDate) {
                try {
                    if (input.replaceAll(" ", "").matches("showall")) {
                        throw new CortanaException("Please specify the date time you are looking for!");
                    } else {
                        String time = input.replaceAll("show all ", "");
                        return new ShowAllTasksOnSameDateCommand(time);
                    }
                } catch (CortanaException e) {
                    new Ui().showErrorMessage(e.getMessage());
                }
            } else {
               try {
                    String taskType = isEmptyDeadline ? "deadline" : isEmptyEvent ? "event" : "todo";
                    if (isEmptyDeadline || isEmptyEvent || isEmptyTodo ) {
                        String aOrAn = isEmptyEvent ? "an " : "a ";
                        throw new CortanaException("OOPS!!! The description of " + aOrAn + taskType + " cannot be empty!");
                    } else {
                        boolean hasBy = Pattern.compile("/by .*").matcher(input).find();
                        boolean hasAt = Pattern.compile("/at .*").matcher(input).find();
                        if (isNotEmptyDeadline && hasBy) { //valid deadline command
                            String[] actualTask = input.replaceAll("deadline ", "").split("/by ");
                            String description = actualTask[0];
                            String by = actualTask[1];
                            boolean correctTimeFormat = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}( \\d{4})?").matcher(by).find();
                            if (correctTimeFormat) {
                                boolean hasTime = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2} \\d{4}").matcher(by).find();
                                Deadline deadline;
                                if (hasTime) {
                                    LocalDateTime localDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
                                    deadline = new Deadline(description, localDateTime);
                                } else {
                                    LocalDate localDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-M-d"));
                                    deadline = new Deadline(description, LocalDateTime.of(localDate, LocalTime.MAX));
                                }
                                return new AddCommand(deadline);
                            } else {
                                throw new CortanaException("Invalid date time format! Please follow the format yyyy-M-d HHmm!");
                            }
                        } else if (isNotEmptyEvent && hasAt) { //valid event command
                            String[] actualTask = input.replaceAll("event ", "").split("/at ");
                            String description = actualTask[0];
                            String at = actualTask[1];
                            boolean correctTimeFormat = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}( \\d{4})?").matcher(at).find();
                            if (correctTimeFormat) {
                                boolean hasTime = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2} \\d{4}").matcher(at).find();
                                Event event;
                                if (hasTime) {
                                    LocalDateTime localDateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
                                    event = new Event(description, localDateTime);
                                } else {
                                    LocalDate localDate = LocalDate.parse(at, DateTimeFormatter.ofPattern("yyyy-M-d"));
                                    event = new Event(description, LocalDateTime.of(localDate, LocalTime.MAX));
                                }
                                return new AddCommand(event);
                            } else {
                                throw new CortanaException("Invalid date time format! Please follow the format yyyy-M-d HHmm!");
                            }
                        } else if (isNotEmptyTodo) { //valid todo command
                            String description = input.replaceAll("todo ", "");
                            Todo todo = new Todo(description);
                            return new AddCommand(todo);
                        } else if (isNotEmptyDeadline && hasAt) { //used /at for deadline
                            throw new CortanaException("Please use the /by keyword for deadline!");
                        } else if (isNotEmptyEvent && hasBy) { //used /by for event
                            throw new CortanaException("Please use the /at keyword for event!");
                        } else if (isNotEmptyDeadline) { //deadline without specifying time with /by
                            throw new CortanaException("Please specify the deadline time with the /by keyword!");
                        } else if (isNotEmptyEvent) { //event without specifying time with /at
                            throw new CortanaException("Please specify the event time with the /at keyword!");
                        } else { //invalid command
                            throw new CortanaException("I don't know what that means :(");
                        }
                    }
                }
                catch (DateTimeParseException e) {
                    throw new CortanaException("Invalid date/time!");
                }
            }
        }
        return new InvalidCommand();
    }
}
