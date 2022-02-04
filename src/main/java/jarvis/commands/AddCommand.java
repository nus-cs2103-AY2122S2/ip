package jarvis.commands;

import jarvis.exceptions.InvalidTaskException;
import jarvis.tasks.TaskList;

public class AddCommand extends Command {
    private final String taskString;

    /**
     * Constructor
     *
     * @param taskString string representation of the task
     */
    public AddCommand(String taskString) {
        this.taskString = taskString;
    }

    /**
     * Function to execute the command and get the result.
     *
     * @param dukeList dukeList object
     * @throws InvalidTaskException invalid task
     */
    @Override
    public String getResult(TaskList dukeList) throws InvalidTaskException {
        return dukeList.add(taskString);
    }
}
