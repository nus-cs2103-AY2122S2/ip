package jarvis.commands;

import jarvis.tasks.TaskList;

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
    public String getResult(TaskList dukeList) {
        return dukeList.unmarkTask(idx);
    }
}
