package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;
import duke.task.Tasks;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Represents a Command which adds a Task to the task List.
 */
public class AddCommand extends Command {

    private Tasks taskType;
    private String description;
    private String time;
    private LocalDateTime localDateTime;

    /**
     * Constructor for a AddCommand, specifically used for a Event Task.
     *
     * @param taskType TaskType of the Task to be added.
     * @param description Description of the Task to be added.
     * @param time Time the Task added occurs at.
     */
    public AddCommand(Tasks taskType, String description, String time) {
        super(false);
        this.taskType = taskType;
        this.description = description;
        this.time = time;
    }

    /**
     * Constructor for a AddCommand, specifically used for a ToDo Task.
     *
     * @param taskType TaskType of the Task to be added.
     * @param description Description of the Task to be added.
     */
    public AddCommand(Tasks taskType, String description) {
        super(false);
        this.taskType = taskType;
        this.description = description;
    }

    /**
     * Constructor for a AddCommand, specifically used for a Deadline Task.
     *
     * @param taskType TaskType of the Task to be added.
     * @param description Description of the Task to be added.
     * @param localDateTime Local date and time of the Task that is added.
     */
    public AddCommand(Tasks taskType, String description, LocalDateTime localDateTime) {
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
     * @throws IOException When writing to File in Storage.
     */
    @Override
    public void execute(List taskList, Ui ui, Storage storage) throws IOException {
        if (taskType == Tasks.DEADLINE) {
            taskList.add(taskType, description, localDateTime);
        } else {
            taskList.add(taskType, description, time);
        }
        ui.printAddedTask(taskList);
        storage.writeToFile("data/duke.txt", taskList);
    }
}
