package duke.commands;

import duke.admin.Storage;
import duke.admin.TaskList;
import duke.admin.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * AddCommand is a Command that adds a task that is either a ToDo task, Deadline
 * task or Event task to the program.
 */

public class AddCommand extends Command {
    private String description;
    private String type;
    private Task taskToBeAdded;

    /**
     * Constructor for AddCommand which takes in the type of task and description of
     * task that is to be added.
     * @param type        type of task to be added
     * @param description description of task to be added
     */
    public AddCommand(String type, String description) {
        assert type != null;
        assert description != null;
        this.type = type;
        this.description = description;
    }

    /**
     * Adds the task to TaskList, updates the storage file and notifies the user
     * when it's done
     * @param tasks   task list local to user
     * @param storage storage instance local to user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        switch (type) {
        case "T":
            taskToBeAdded = new ToDo(description);
            break;
        case "D":
            taskToBeAdded = new Deadline(description);
            break;
        case "E":
            taskToBeAdded = new Event(description);
            break;
        default:
            break;
        }

        tasks.add(taskToBeAdded);
        storage.updateAfterAdd(taskToBeAdded, description);

        return Ui.showAddedMessage(taskToBeAdded, tasks);
    }

    /**
     * Checks if this is an exit command, and only returns true for an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
