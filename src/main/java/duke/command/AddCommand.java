package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.List;
import duke.Storage;
import duke.Ui;
import duke.task.TaskType;

/**
 * Represents a Command which adds a Task to the task List.
 */
public class AddCommand extends Command {

    private final TaskType taskType;
    private final String description;
    private LocalDateTime localDateTime;

    /**
     * Constructor for a AddCommand, specifically used for a Event Task.
     *
     * @param taskType TaskType of the Task to be added.
     * @param description Description of the Task to be added.
     * @param time Time the Task added occurs at.
     */
    /*public AddCommand(TaskType taskType, String description, String time) {
        super(false);
        this.taskType = taskType;
        this.description = description;
        this.time = time;
    }*/

    /**
     * Constructor for a AddCommand, specifically used for a ToDo Task.
     *
     * @param taskType TaskType of the Task to be added.
     * @param description Description of the Task to be added.
     */
    /*public AddCommand(TaskType taskType, String description) {
        super(false);
        this.taskType = taskType;
        this.description = description;
    } */

    /**
     * Constructor for a AddCommand, specifically used for a Deadline Task.
     *
     * @param taskType TaskType of the Task to be added.
     * @param description Description of the Task to be added.
     * @param localDateTime Local date and time of the Task that is added.
     */
    public AddCommand(TaskType taskType, String description, LocalDateTime localDateTime) {
        super(false);
        this.taskType = taskType;
        this.description = description;
        this.localDateTime = localDateTime;
    }

    /**
     * Executes the AddCommand.
     *
     * @param taskList Task List of Tasks.
     * @param ui Ui
     * @param storage Storage
     * @return Returns a string acknowledgement of the task added.
     * @throws IOException If File to be written to in Storage is not found.
     */
    @Override
    public String execute(List taskList, Ui ui, Storage storage) throws IOException {
        taskList.add(taskType, description, localDateTime);
        storage.writeToFile("data/duke.txt", taskList);
        return ui.showAddedTask(taskList);
    }
}
