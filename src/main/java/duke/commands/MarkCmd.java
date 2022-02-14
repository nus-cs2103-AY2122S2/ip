package duke.commands;

import duke.Storage;
import duke.TaskList;


public class MarkCmd extends Command{

    private int index;

    /**
     * Constructor for MarkCmd takes in the index of the task to be marked as
     * done.
     *
     * @param index the index of the task to be marked as done
     */
    public MarkCmd(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.mark(index);
    }

}
