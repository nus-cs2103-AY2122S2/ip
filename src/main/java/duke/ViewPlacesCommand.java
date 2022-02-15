package duke;

import java.io.IOException;

/**
 * Represents a view places command.
 */
public class ViewPlacesCommand extends Command {

    /**
     * Constructs an instance of the ViewPlacesCommand class.
     *
     * @param command A string containing the word "viewplaces".
     */
    public ViewPlacesCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, PlaceList places, Ui ui,
                          Storage storageTask, Storage storagePlace) throws IOException {
        if (places.isEmpty()) {
            return "Uh-oh! List is empty!";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < places.size(); i++) {
                sb.append(i + 1).append(". ").append(places.get(i).getName()).append("\n");
            }
            return sb.toString();
        }
    }
}
