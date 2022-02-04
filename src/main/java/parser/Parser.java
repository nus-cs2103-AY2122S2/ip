package parser;

import command.AddDeadlineCommand;
import command.Command;
import command.ExitCommand;
import command.MarkCommand;
import command.ListCommand;
import data.DukeException;
import command.ErrorCommand;
import command.UnmarkCommand;
import command.DeleteCommand;
import command.SearchCommand;
import command.AddTodoCommand;
import command.AddEventCommand;
import task.Deadline;
import task.Event;
import task.ToDo;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
public class Parser {
    public static Command parse(String text) throws DukeException {
        Ui ui = new Ui();
        List<String> input = Arrays.asList(text.split(" "));
        switch (input.get(0)) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                try {
                    int markIndex = Integer.parseInt(input.get(1)) - 1;
                    return new MarkCommand(markIndex);
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand("Missing Index");
                }
            case "unmark":
                try {
                    int unmarkIndex = Integer.parseInt(input.get(1)) - 1;
                    return new UnmarkCommand(unmarkIndex);
                } catch (IndexOutOfBoundsException e) {
                    return new ErrorCommand("Missing Index");
                }
            case "todo":
                StringBuilder todo = new StringBuilder();
                if (input.size() == 1) {
                    DukeException exception = new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    return new ErrorCommand(exception.toString());
                } else {
                    for (int i = 1; i < input.size(); i++) {
                        todo.append(input.get(i)).append(" ");
                    }
                    return new AddTodoCommand(new ToDo(todo.toString()));
                }
            case "deadline":
                StringBuilder deadline = new StringBuilder();
                StringBuilder deadlineBy = new StringBuilder();
                int byIndex = input.indexOf("/by");
                if (input.size() == 1) {
                    DukeException exception = new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    return new ErrorCommand(exception.toString());
                } else if (byIndex == -1) {
                    DukeException exception = new DukeException("OOPS!!! The datetime of a deadline cannot be empty.");
                    return new ErrorCommand(exception.toString());
                } else {
                    for (int i = 1; i < byIndex; i++) {
                        deadline.append(input.get(i)).append(" ");
                    }
                    for (int i = byIndex + 1; i < input.size(); i++) {
                        deadlineBy.append(input.get(i)).append(" ");
                    }
                    LocalDate date = convertDate(deadlineBy.toString().trim());
                    if (date != null) {
                        return new AddDeadlineCommand(new Deadline(deadline.toString(), date));
                    }
                }
            case "event":
                int atIndex = input.indexOf("/at");
                if (input.size() == 1) {
                    DukeException exception = new DukeException("OOPS!!! The description of a event cannot be empty.");
                    return new ErrorCommand(exception.toString());
                } else if (atIndex == -1) {
                    DukeException exception = new DukeException("OOPS!!! The datetime of a event cannot be empty.");
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
                    if (date != null) {
                        return new AddEventCommand(new Event(event.toString(), date));
                    }
                }
            case "delete":
                try {
                    int deleteIndex = Integer.parseInt(input.get(1)) - 1;
                    return new DeleteCommand(deleteIndex);
                } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                    DukeException exception = new DukeException("OOPS!!! invalid index.");
                    return new ErrorCommand(exception.toString());
                }
            case "search":
                LocalDate date = convertDate(input.get(1));
                if (date != null) {
                    return new SearchCommand(date);
                }
            default:
                return new ErrorCommand("OOPS!!! I'm sorry, but I don't know what that means");
        }
    }

    public static LocalDate convertDate (String dateText) {
        Ui ui = new Ui();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateText, formatter);
        } catch (DateTimeParseException e) {
            ui.showDateTimeParseException();
            return null;
        }
    }
}
