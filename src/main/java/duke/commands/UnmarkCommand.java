package duke.commands;

import duke.DukeList;

public class UnmarkCommand extends Command {
    private final int idx;

    /**
     * Constructor
     *
     * @param idx index of the task
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Function to execute the command and get the result.
     *
     * @param dukeList dukeList object
     */
    @Override
    public String getResult(DukeList dukeList) {
        return dukeList.unmarkTask(idx);
    }
}
