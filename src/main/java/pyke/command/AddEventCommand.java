package pyke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import pyke.exception.EmptyDescriptionException;
import pyke.exception.EmptyTimeException;
import pyke.exception.PykeException;
import pyke.task.Event;
import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;


public class AddEventCommand extends Command {
    private String taskName;
    private String eventTime;

    /**
     * Constructs an AddEventCommand.
     *
     * @param taskName the name of the task.
     * @param eventTime the time of the event.
     */
    public AddEventCommand(String taskName, String eventTime) {
        this.taskName = taskName;
        this.eventTime = eventTime;
    }

    /**
     * Executes a command that adds an event task.
     * Then it will write the list to the local file and output certain info about this operation.
     *
     * @param taskList the class store the tasks info.
     * @param ui the interface for output information.
     * @param storage in charge of file IO.
     * @throws PykeException if the task name if empty.
     * @throws DateTimeParseException if the input dates are not in the correct format for parsing.
     * @throws IOException if there is an error when writing to the local file.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws PykeException, DateTimeParseException, IOException {
        if (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        } else if (eventTime.isEmpty()) {
            throw new EmptyTimeException();
        } else {
            taskList.addTask(new Event(taskName, LocalDate.parse(eventTime)));
            storage.saveFile(taskList);
            ui.outputText("Got it. I've added this task:\n    "
                    + taskList.getTaskOutputStyle(taskList.getSize() - 1)
                    + "\nNow you have " + taskList.getSize() + " tasks in the list.");
        }
    }

    @Override
    public String executeUi(TaskList taskList, Ui ui, Storage storage)
            throws PykeException, DateTimeParseException, IOException {
        if (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        } else if (eventTime.isEmpty()) {
            throw new EmptyTimeException();
        } else {
            taskList.addTask(new Event(taskName, LocalDate.parse(eventTime)));
            storage.saveFile(taskList);
            return ui.outputUiText("Got it. I've added this task:\n    "
                    + taskList.getTaskOutputStyle(taskList.getSize() - 1)
                    + "\nNow you have " + taskList.getSize() + " tasks in the list.");
        }
    }
    /**
     * Knows if this command will exit the program.
     *
     * @return true if this method will exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
