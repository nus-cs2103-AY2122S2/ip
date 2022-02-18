package duke;

import java.io.IOException;

/**
 * Represents a delete place command
 */
public class DeletePlaceCommand extends Command {

    private final int idx;

    /**
     * Constructor of the DeletePlaceCommand class.
     *
     * @param command A string containing the word "delplace".
     * @param idx An integer representing the place number to delete.
     */
    public DeletePlaceCommand(String command, int idx) {
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
            Place deletedPlace = places.remove(placeIdx);
            storagePlace.update(places);
            return "Noted. I've removed this place:\n  "
                    + deletedPlace.getName()
                    + "\nNow you have " + places.size() + " places in the list.";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
