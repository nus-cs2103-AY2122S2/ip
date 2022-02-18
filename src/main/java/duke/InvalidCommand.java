package duke;

import java.io.IOException;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {

    /**
     * Constructs an instance of the InvalidCommand class.
     *
     * @param command A string containing the original invalid command
     */
    public InvalidCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, PlaceList places, Ui ui,
                          Storage storageTask, Storage storagePlace) throws IOException {
        return this.getFirstWord();
    }
}
