package duke.commands;

import duke.DukeList;
import duke.UI;
import duke.exceptions.InvalidTaskException;

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
     * Function to execute the command.
     *
     * @param dukeList dukelist object
     * @param ui ui object
     * @throws InvalidTaskException invalid task
     */
    @Override
    public void execute(DukeList dukeList, UI ui) throws InvalidTaskException {
        ui.printMsg(dukeList.add(taskString));
    }
}
