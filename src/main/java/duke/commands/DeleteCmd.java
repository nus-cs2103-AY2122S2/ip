package duke.commands;

import duke.Storage;
import duke.TaskList;

import java.security.spec.ECField;

public class DeleteCmd extends Command{
    private int index;

    /**
     * Constructor for DeleteCmd takes in the index of the task to be marked as
     * done.
     *
     * @param index the index of the task to be marked as done
     */
    public DeleteCmd(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            return ui.showTaskRemoved(taskList.removeTask(index));
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
