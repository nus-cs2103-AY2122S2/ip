package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

class Parser {
    private final Ui ui;

    Parser() {
        this.ui = new Ui();
    }

    public Command parse(String command) throws DukeException {
        String fullCommand = command.toLowerCase();
        String commandWord = ui.getCommandWord(fullCommand);
        if (fullCommand.equals("bye")) {
            ExitCommand c = new ExitCommand(fullCommand);
            return c;
        } else if (fullCommand.equals("save")) {
            SaveCommand c = new SaveCommand(fullCommand);
            return c;
        } else if (fullCommand.equals("list")) {
            ListCommand c = new ListCommand(fullCommand);
            return c;
        } else if (commandWord.equals("todo")) {
            if (ui.isValidTask(fullCommand)) {
                String taskName = ui.getTaskName(fullCommand);
                ToDo todo = new ToDo(taskName);
                AddCommand addCommand = new AddCommand("todo", todo);
                return addCommand;
            } else {
                throw new EmptyDescriptionException("Todo description cannot be empty!");
            }
        } else if (commandWord.equals("deadline")) {
            // include date exception!
            if (ui.isValidTask(fullCommand)) {
                if (ui.isValidDeadline(fullCommand)) {
                    String taskName = ui.getTaskName(fullCommand);
                    LocalDate localDate = ui.getTaskDate(fullCommand);
                    Deadline deadline = new Deadline(taskName, localDate);
                    AddCommand addCommand = new AddCommand("deadline", deadline);
                    return addCommand;
                } else {
                    throw new InvalidCommandFormatException("Deadlines must include \"/by\"");
                }
            } else {
                throw new EmptyDescriptionException("Deadline description cannot be empty!");
            }
        } else if (commandWord.equals("event")) {
            if (ui.isValidTask(fullCommand)) {
                if (ui.isValidEvent(fullCommand)) {
                    String taskName = ui.getTaskName(fullCommand);
                    LocalDate localDate = ui.getTaskDate(fullCommand);
                    Event event = new Event(taskName, localDate);
                    AddCommand addCommand = new AddCommand("event", event);
                    return addCommand;
                } else {
                    throw new InvalidCommandFormatException("Events must include \"/at\"");
                }
            } else {
                throw new EmptyDescriptionException("Event description cannot be empty!");
            }
        } else if (commandWord.equals("mark")) {
            if (ui.isValidMarkFormat(fullCommand)) {
                MarkCommand markCommand = new MarkCommand(fullCommand, ui.markIndex(fullCommand));
                return markCommand;
            } else {
                throw new InvalidCommandFormatException("Please include item index!");
            }
        } else if (commandWord.equals("unmark")) {
            if (ui.isValidUnmarkFormat(fullCommand)) {
                UnmarkCommand unmarkCommand = new UnmarkCommand(fullCommand, ui.markIndex(fullCommand));
                return unmarkCommand;
            } else {
                throw new InvalidCommandFormatException("Please include item index!");
            }
        } else if (commandWord.equals("find")) {
            if (ui.isValidTask(fullCommand)) {
                FindCommand findCommand = new FindCommand(fullCommand);
                return findCommand;
            } else {
                throw new InvalidCommandFormatException("Invalid find!");
            }
        } else {
            String generalFormatExceptionString = "Please give a proper command!\n" +
                    "List of commands: \n" +
                    "1. todo\n" +
                    "2. deadline\n" +
                    "3. event\n" +
                    "4. list\n" +
                    "5. mark\n" +
                    "6. unmark\n" +
                    "7. find\n" +
                    "8. delete \n" +
                    "9. bye";
            throw new InvalidCommandFormatException(generalFormatExceptionString);
        }
    }
}
