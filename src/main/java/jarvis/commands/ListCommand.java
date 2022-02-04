package jarvis.commands;

import jarvis.tasks.TaskList;

public class ListCommand extends Command {
    /**
     * Function to execute the command and get the result.
     *
     * @param dukeList dukeList object
     */
    @Override
    public String getResult(TaskList dukeList) {
        return dukeList.toString();
    }
}
