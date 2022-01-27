package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

class Parser {
    private static Ui ui;

    Parser() {
    }

    public static Command parse(String command) throws DukeException {
        String fullCommand = command.toLowerCase();
        if (fullCommand.equals("bye")) {
            ExitCommand c = new ExitCommand(fullCommand);
            return c;
        } else if (fullCommand.equals("save")) {
            SaveCommand c = new SaveCommand(fullCommand);
            return c;
        } else if (fullCommand.equals("list")) {
            ListCommand c = new ListCommand(fullCommand);
            return c;
        } else if (ui.getCommandWord(fullCommand).equals("todo")) {
            if (ui.isValidTask(fullCommand)) {
                String taskName = ui.getTaskName(fullCommand);
                ToDo todo = new ToDo(taskName);
                AddCommand addCommand = new AddCommand("todo", todo);
                return addCommand;
            } else {
                throw new EmptyDescriptionException("Invalid task!");
            }
        } else if (ui.getCommandWord(fullCommand).equals("deadline")) {
            if (ui.isValidTask(fullCommand)) {
                if (ui.isValidDeadline(fullCommand)) {
                    try {
                        String taskName = ui.getTaskName(fullCommand);
                        LocalDate localDate = ui.getTaskDate(fullCommand);
                        Deadline deadline = new Deadline(taskName, localDate);
                        AddCommand addCommand = new AddCommand("deadline", deadline);
                        return addCommand;
                    } catch (DateTimeParseException e) {
                        ui.showDateTimeParseException();
                    }
                } else {
                    ui.showInvalidDeadline();
                }
            } else {
                ui.showEmptyTask();
            }
        } else if (ui.getCommandWord(fullCommand).equals("event")) {
            if (ui.isValidTask(fullCommand)) {
                if (ui.isValidEvent(fullCommand)) {
                    try {
                        String taskName = ui.getTaskName(fullCommand);
                        LocalDate localDate = ui.getTaskDate(fullCommand);
                        Event event = new Event(taskName, localDate);
                        AddCommand addCommand = new AddCommand("event", event);
                        return addCommand;
                    } catch (DateTimeParseException e) {
                        ui.showDateTimeParseException();
                    }
                } else {
                    ui.showInvalidEvent();
                }
            } else {
                ui.showEmptyTask();
            }
        } else if (ui.getCommandWord(fullCommand).equals("mark")) {
            if (ui.isValidMark(fullCommand)) {
                MarkCommand markCommand = new MarkCommand(fullCommand,
                        ui.markIndex(fullCommand));
                return markCommand;
            }
        } else if (ui.getCommandWord(fullCommand).equals("unmark")) {
            if (ui.isValidUnmark(fullCommand)) {
                UnmarkCommand unmarkCommand = new UnmarkCommand(fullCommand,
                        ui.markIndex(fullCommand));
                return unmarkCommand;
            }
        } else if (ui.getCommandWord(fullCommand).equals("find")) {
            if (ui.isValidTask(fullCommand)) {
                FindCommand findCommand = new FindCommand(fullCommand);
                return findCommand;
            } else {
                ui.showGeneralException();
            }
        }
        return null;
    }
}
