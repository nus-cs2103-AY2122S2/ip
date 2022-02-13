package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ErrorCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.PriorityCommand;
import duke.command.SearchCommand;
import duke.command.SortCommand;
import duke.command.UnmarkCommand;
import duke.data.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Priority;
import duke.task.ToDo;
import duke.ui.Ui;

public class Parser {

    /**
     * Returns a command with user input.
     *
     * @param text text to be parsed.
     */
    public static Command parse(String text) throws DukeException {
        List<String> input = Arrays.asList(text.split(" "));
        if (input.size() == 0) {
            throw new DukeException("empty input");
        }
        switch (input.get(0)) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return prepareMark(input);
        case "unmark":
            return prepareUnMark(input);
        case "todo":
            return prepareAddToDo(input);
        case "deadline":
            return prepareAddDeadline(input);
        case "event":
            return prepareAddEvent(input);
        case "delete":
            return prepareDelete(input);
        case "search":
            return prepareSearchDate(input);
        case "find":
            return prepareFind(input);
        case "priority":
            return preparePriority(input);
        case "sort":
            return prepareSort(input);
        default:
            return new ErrorCommand("I'm sorry, but I don't know what that means");
        }
    }

    /**
     * Converts date String to LocalDate
     *
     * @param dateText text to be parsed.
     */
    public static LocalDate convertDate(String dateText) {
        Ui ui = new Ui();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateText, formatter);
        } catch (DateTimeParseException e) {
            ui.showDateTimeParseException();
            return null;
        }
    }

    private static Command prepareMark(List<String> input) {
        try {
            int markIndex = Integer.parseInt(input.get(1)) - 1;
            return new MarkCommand(markIndex);
        } catch (IndexOutOfBoundsException e) {
            return new ErrorCommand("Missing Index");
        } catch (NumberFormatException e) {
            return new ErrorCommand("Invalid Index");
        }
    }

    private static Command prepareUnMark(List<String> input) {
        try {
            int unmarkIndex = Integer.parseInt(input.get(1)) - 1;
            return new UnmarkCommand(unmarkIndex);
        } catch (IndexOutOfBoundsException e) {
            return new ErrorCommand("Missing Index");
        } catch (NumberFormatException e) {
            return new ErrorCommand("Invalid Index");
        }
    }

    private static Command prepareAddToDo(List<String> input) {
        StringBuilder todo = new StringBuilder();
        assert input != null : "empty input";
        if (input.size() == 1) {
            DukeException exception = new DukeException("The description of a todo cannot be empty.");
            return new ErrorCommand(exception.toString());
        } else {
            for (int i = 1; i < input.size(); i++) {
                todo.append(input.get(i)).append(" ");
            }
            return new AddTodoCommand(new ToDo(todo.toString()));
        }
    }

    private static Command prepareAddEvent(List<String> input) {
        assert input != null : "empty input";
        int atIndex = input.indexOf("/at");
        if (input.size() == 1) {
            DukeException exception = new DukeException("The description of a event cannot be empty.");
            return new ErrorCommand(exception.toString());
        } else if (atIndex == -1) {
            DukeException exception = new DukeException("The datetime of a event cannot be empty.");
            return new ErrorCommand(exception.toString());
        } else {
            StringBuilder event = new StringBuilder();
            for (int i = 1; i < atIndex; i++) {
                event.append(input.get(i)).append(" ");
            }
            StringBuilder eventAt = new StringBuilder();
            for (int i = atIndex + 1; i < input.size(); i++) {
                eventAt.append(input.get(i)).append(" ");
            }
            LocalDate date = convertDate(eventAt.toString().trim());
            assert date != null;
            return new AddEventCommand(new Event(event.toString(), date));
        }
    }

    private static Command prepareAddDeadline(List<String> input) {
        assert input != null : "empty input";
        StringBuilder deadline = new StringBuilder();
        StringBuilder deadlineBy = new StringBuilder();
        int byIndex = input.indexOf("/by");
        if (input.size() == 1) {
            DukeException exception = new DukeException("The description of a deadline cannot be empty.");
            return new ErrorCommand(exception.toString());
        } else if (byIndex == -1) {
            DukeException exception = new DukeException("The datetime of a deadline cannot be empty.");
            return new ErrorCommand(exception.toString());
        } else {
            for (int i = 1; i < byIndex; i++) {
                deadline.append(input.get(i)).append(" ");
            }
            for (int i = byIndex + 1; i < input.size(); i++) {
                deadlineBy.append(input.get(i)).append(" ");
            }
            LocalDate date = convertDate(deadlineBy.toString().trim());
            assert date != null : "No date provided";
            return new AddDeadlineCommand(new Deadline(deadline.toString(), date));
        }
    }

    private static Command prepareDelete(List<String> input) {
        try {
            int deleteIndex = Integer.parseInt(input.get(1)) - 1;
            return new DeleteCommand(deleteIndex);
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            DukeException exception = new DukeException("invalid index.");
            return new ErrorCommand(exception.toString());
        }
    }

    private static Command prepareSearchDate(List<String> input) {
        LocalDate date = convertDate(input.get(1));
        if (date != null) {
            return new SearchCommand(date);
        }
        return new ErrorCommand("Search error");
    }

    private static Command prepareFind(List<String> input) {
        try {
            String keyword = input.get(1);
            return new FindCommand(keyword);
        } catch (IndexOutOfBoundsException e) {
            return new ErrorCommand("Please put in keyword");
        }
    }

    private static Command preparePriority(List<String> input) {
        try {
            int index = Integer.parseInt(input.get(1)) - 1;
            Priority priority;
            String priorityText = input.get(2);
            switch (priorityText) {
            case "low":
                priority = Priority.LOW;
                break;
            case "normal":
                priority = Priority.NORMAL;
                break;
            case "high":
                priority = Priority.HIGH;
                break;
            default:
                return new ErrorCommand("Invalid priority");
            }
            return new PriorityCommand(index, priority);
        } catch (IndexOutOfBoundsException e) {
            return new ErrorCommand("Missing Index");
        } catch (NumberFormatException e) {
            return new ErrorCommand("Invalid Index");
        }
    }

    private static Command prepareSort(List<String> input) {
        try {
            String sortBy = input.get(1);
            return new SortCommand(sortBy);
        } catch (IndexOutOfBoundsException e) {
            return new ErrorCommand("Missing or wrong sort keyword");
        }
    }
}
