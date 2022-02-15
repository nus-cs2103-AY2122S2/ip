package duke;

import java.io.IOException;

/**
 * Represents a view command
 */
public class ViewCommand extends Command {

    private final int idx;

    /**
     * Constructs an instance of the ViewCommand class.
     *
     * @param command a string containing the word "view".
     * @param idx an integer representing the place number to view.
     */
    public ViewCommand(String command, int idx) {
        super(command);
        this.idx = idx;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, PlaceList places, Ui ui,
                          Storage storageTask, Storage storagePlace) throws IOException {
        try {
            int placeIdx = idx - 1;
            if (placeIdx >= places.size() || placeIdx < 0) {
                throw new DukeException("Please choose a valid place! (Your list has "
                        + places.size() + " places)");
            }
            return places.get(placeIdx).getDescription();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
