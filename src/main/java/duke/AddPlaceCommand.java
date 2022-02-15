package duke;

import java.io.IOException;

/**
 * Represents an add place command
 */
public class AddPlaceCommand extends Command {

    private final String name;
    private final String description;

    /**
     * Constructs an instance of the AddPlaceCommand class.
     *
     * @param command
     * @param name
     * @param description
     */
    public AddPlaceCommand(String command, String name, String description) {
        super(command);
        assert !command.contains("[") || !command.contains("]") : "Please don't use brackets in the place name";
        this.name = name;
        this.description = description;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, PlaceList places, Ui ui,
                          Storage storageTask, Storage storagePlace) throws IOException{
        Place place = new Place(this.name, this.description);
        places.add(place);
        storagePlace.update(places);
        return "Got it. I've added this place:\n" +
                "  " + place.getName() + "\n" +
                "Now you have " + places.size() + " places in the list.";
    }
}
