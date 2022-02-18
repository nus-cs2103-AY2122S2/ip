package duke;

import java.io.IOException;

/**
 * Represents an exit command, terminating the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an instance of the ExitCommand class.
     *
     * @param command A string containing the word "bye".
     */
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, PlaceList places, Ui ui,
                          Storage storageTask, Storage storagePlace) throws IOException {
        return ui.showGoodbye();
    }
}
