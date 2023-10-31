package duke.commands;

import duke.admin.Storage;
import duke.admin.TaskList;
import duke.admin.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

public class UpdateCommand extends Command {
    private int index;
    private String typeOfUpdate;
    private String updateValue;

    /**
     * Constructor for UpdateCommand
     * @param description description of the command that contains the type of update, index of task to be updated,
     * and value to be updated to
     */
    public UpdateCommand(String description) {
        String[] splitDescription = description.split(" ");
        assert splitDescription.length >= 3;

        this.typeOfUpdate = splitDescription[0];
        this.index = Integer.parseInt(splitDescription[1]) - 1;
        this.updateValue = splitDescription[2];
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task taskToBeUpdated = tasks.update(typeOfUpdate, index, updateValue);
        String typeOfTask = taskToBeUpdated.getType();
        storage.updateAfterEdits(typeOfTask, typeOfUpdate, index, updateValue);

        return Ui.showUpdatedMessage(taskToBeUpdated);
    }
}
