package duke.commands;

import duke.DukeList;

public class DeleteCommand extends Command {
    private final int idx;

    /**
     * Constructor
     *
     * @param idx index of the task
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Function to execute the command and get the result.
     *
     * @param dukeList dukeList object
     */
    @Override
    public String getResult(DukeList dukeList) {
        return dukeList.delete(idx);
    }
}
