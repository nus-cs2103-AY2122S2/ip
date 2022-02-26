package butler.command;

import butler.ButlerInputException;
import butler.TaskList;
import butler.util.Storage;
import butler.util.ui.Ui;

public class UpdateCommand extends Command {

    /** Index of taskList that is to be updated */
    private int index;
    /** Updated description of what needs to be done for the task */
    private String newDescription;

    /**
     * Constructs an UpdateCommand object that executes a specified command.
     *
     * @param index Index of taskList that is to be updated.
     * @param description new description of the task.
     */
    public UpdateCommand(int index, String description) {
        this.index = index;
        this.newDescription = description;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws ButlerInputException {
        String message = taskList.updateTaskDescription(index, newDescription);
        storage.writeTaskListToFile(taskList);
        ui.setMessage(message);
    }
}
