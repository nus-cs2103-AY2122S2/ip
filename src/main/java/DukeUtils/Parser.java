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
    public Parser() {}

    public static Command parse(String input) throws CortanaException {
        boolean isExit = input.toLowerCase().replaceAll("[ |\\t]", "").equals("bye");
        boolean isListCommand = input.toLowerCase().replaceAll("[ |\\t]", "").equals("list");
        boolean isMarkCommand = input.toLowerCase().matches("^mark \\d+|^mark -\\d+");
        boolean isUnmarkCommand = input.toLowerCase().matches("^unmark \\d+|^unmark -\\d+");
        boolean isDeleteCommand = input.toLowerCase().matches("^delete \\d+|^delete -\\d+");
        boolean isDeleteAllCommand = input.toLowerCase().matches("^delete all");
        boolean isShowAllOnSameDate = input.toLowerCase().matches("^show all( )?(\\d{4} ?.?/?-?\\d{1,2} ?.?/?-?\\d{1,2})?( )?(\\d{4})?");
        boolean isFindCommand = input.toLowerCase().matches("^find( )? .*");
        boolean isEmptyDeadline = input.toLowerCase().replaceAll("[ |\\t]", "").equals("deadline");
        boolean isEmptyEvent = input.toLowerCase().replaceAll("[ |\\t]", "").equals("event");
        boolean isEmptyTodo = input.toLowerCase().replaceAll("[ |\\t]", "").equals("todo");
        boolean isNotEmptyDeadline = input.toLowerCase().matches("^deadline .*");
        boolean isNotEmptyEvent = input.toLowerCase().matches("^event .*");
        boolean isNotEmptyTodo = input.toLowerCase().matches("^todo .*");

        if (isExit) {
            return new ExitCommand();
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
                        String dateTimeString = input.replaceAll("show all ", "");
                        boolean correctTimeFormat = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}( \\d{4})?").matcher(dateTimeString).find();
                        if (correctTimeFormat) {
                            LocalDateTime localDateTime;
                            boolean hasTime = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2} \\d{4}").matcher(dateTimeString).find();
                            if (hasTime) {
                                localDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
                            } else {
                                LocalDate localDate = LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-M-d"));
                                localDateTime = LocalDateTime.of(localDate, LocalTime.MAX);
                            }
                            return new ShowAllTasksOnSameDateCommand(localDateTime, dateTimeString);
                        } else {
                            throw new CortanaException("Invalid date time format! Please follow the format yyyy-M-d HHmm!");
                        }
                    }
                } catch (DateTimeParseException e) {
                    throw new CortanaException("Invalid date/time!");
                }
            } else if (isFindCommand) {
                String keyWord = input.replaceAll("find ", "");
                if (keyWord.isEmpty()) {
                    throw new CortanaException("Please specify the keyword you are looking for!");
                } else {
                    return new FindCommand(keyWord);
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
    }
}
