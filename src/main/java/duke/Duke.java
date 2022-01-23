package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * Class containing Duke and main function
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
//        try {
//            taskList = new TaskList(storage.load());
//        } catch (DukeException e) {
//            ui.showLoadingError();
//            taskList = new TaskList();
//        }
        this.taskList = new TaskList();
    }


    public void run() {
        ui.showHi();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand().toLowerCase();
            if (fullCommand.equals("bye")) {
                ExitCommand c = new ExitCommand(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } else if (fullCommand.equals("save")) {
                SaveCommand c = new SaveCommand(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } else if (fullCommand.equals("list")) {
                ListCommand c = new ListCommand(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } else if (ui.getCommandWord(fullCommand).equals("todo")) {
                if (ui.isValidTask(fullCommand)) {
                    String taskName = ui.getTaskName(fullCommand);
                    ToDo todo = new ToDo(taskName);
                    AddCommand addCommand = new AddCommand("todo", todo);
                    addCommand.execute(taskList, ui, storage);
                    isExit = addCommand.isExit();
                } else {
                    ui.showEmptyTask();
                }
            } else if (ui.getCommandWord(fullCommand).equals("deadline")) {
                if (ui.isValidTask(fullCommand)) {
                    if (ui.isValidDeadline(fullCommand)) {
                        try {
                            String taskName = ui.getTaskName(fullCommand);
                            LocalDate localDate = ui.getTaskDate(fullCommand);
                            Deadline deadline = new Deadline(taskName, localDate);
                            AddCommand addCommand = new AddCommand("deadline", deadline);
                            addCommand.execute(taskList, ui, storage);
                            isExit = addCommand.isExit();
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
                            addCommand.execute(taskList, ui, storage);
                            isExit = addCommand.isExit();
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
                    markCommand.execute(taskList, ui, storage);
                    isExit = markCommand.isExit();
                }
            } else if (ui.getCommandWord(fullCommand).equals("unmark")) {
                if (ui.isValidUnmark(fullCommand)) {
                    UnmarkCommand unmarkCommand = new UnmarkCommand(fullCommand,
                            ui.markIndex(fullCommand));
                    unmarkCommand.execute(taskList, ui, storage);
                    isExit = unmarkCommand.isExit();
                }
            } else {
                ui.showGeneralException();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}