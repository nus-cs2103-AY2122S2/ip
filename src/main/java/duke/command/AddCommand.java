package duke.command;

import java.io.IOException;
import java.time.LocalDate;

import duke.Storage;
import duke.exception.BingChillingException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.MessageUi;

/**
 * Represents commands which add task to the task list. An AddCommand
 * object corresponds a valid Ekud command, which can then be executed.
 */
public class AddCommand implements Command {

    private String fullCommand;
    private String taskType;

    /**
     * Constructor for this class.
     * @param fullCommand User's input
     */
    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.taskType = fullCommand.split(" ", 2)[0];
    }

    /**
     * Executes a valid Ekud command that add a task to the task list.
     * Supports the addition of 'todo', 'event and 'deadline' task.
     * @param tasks TaskList object.
     * @param storage Storage object.
     * @param ui Ui object.
     * @throws IOException If directory or file cannot be found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, MessageUi ui) throws BingChillingException {
        boolean isAddCommand = BingChillingCommand.getTaskTypeMap().get(taskType).equals("ADD_COMMAND");
        assert isAddCommand : taskType;
        String[] taskInformation = fullCommand.split(taskType + " ", 2);
        switch (taskType) {
        case "todo":
            ToDo toDoTask = new ToDo(taskInformation[1]);
            return tasks.addToList(toDoTask, ui, storage);
        case "deadline":
            String by = taskInformation[1].split("/by ", 2)[1];
            LocalDate deadlineDate = LocalDate.parse(by,
                    Task.getInputDateFormat());
            Deadline deadlineTask = new Deadline(taskInformation[1].split("/by ")[0], deadlineDate);
            return tasks.addToList(deadlineTask, ui, storage);
        case "event":
            String at = taskInformation[1].split("/at ", 2)[1];
            LocalDate eventDate = LocalDate.parse(at,
                    Task.getInputDateFormat());
            Event eventTask = new Event(taskInformation[1].split("/at ")[0], eventDate);
            return tasks.addToList(eventTask, ui, storage);
        default:
            assert false : taskType;
            return ("ERROR");
        }
    }
}
