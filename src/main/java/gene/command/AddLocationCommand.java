package gene.command;

import gene.component.*;
import gene.exception.BadLocationDescException;
import gene.location.Location;


/**
 * The add command class. This class encapsulates the different kinds of
 * add commands. These include add to do task, add event task and add deadline
 * task.
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class AddLocationCommand extends Command {
    private final String locationBody;

    /**
     * The add command class constructor
     * @param locationBody the body text from the user input to be parsed
     */
    public AddLocationCommand(String locationBody) {
        this.locationBody = locationBody;
    }

    /**
     * The execute method for the add command, which holds all the instructions
     * for when this command is to be executed.
     *
     * @param geneTasks the list of tasks
     * @param geneUi the Ui class task object
     * @param geneTaskStorage the tastorage class object
     * @param geneLocs the list of locations
     * @param geneLocationStorage the location storage class object
     */
    @Override
    public String execute(TaskList geneTasks, Ui geneUi, TaskStorage geneTaskStorage, LocationList geneLocs, LocationStorage geneLocationStorage) {
        return addLocation(locationBody, geneUi, geneLocationStorage, geneLocs);
    }

    private String addLocation(String locationBody, Ui geneUi, LocationStorage geneLocationStorage, LocationList geneLocs) {
        String locationName = "";
        try {
            locationName = stringSplitter("location ", locationBody)[1];
        } catch (BadLocationDescException e) {
            return "";
        }
        String locationPostalCode = "";
        try {
            String[] combinedLocation = stringSplitter(" /at ", locationName);
            locationName = combinedLocation[0];
            locationPostalCode = combinedLocation[1];
        } catch (BadLocationDescException err) {
            err.printStackTrace();
        }
        String locationType = "";
        try {
            String[] combinedLocation = stringSplitter(", ", locationPostalCode);
            locationPostalCode = combinedLocation[0];
            locationType = combinedLocation[1];
        } catch (BadLocationDescException e) {
            e.printStackTrace();
        }
        Location newLocation = new Location(locationName, locationPostalCode, locationType);
        geneLocs.add(newLocation);
        geneLocationStorage.writeToFile(locationName, locationPostalCode, locationType, false);
        return Ui.showLine() + "\n"
                + "Got it. I've added this location:\n"
                + "  " + newLocation + "\n"
                + "Now you have " + geneLocs.size() + " locations in the list."
                + "\n"
                + Ui.showLine();
    }

    /**
     * The method to distinguish this command from an exit command.
     *
     * @return must return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    private String[] stringSplitter(String splitKey, String toSplit) throws BadLocationDescException {
        String[] splitUp = toSplit.split(splitKey);
        if (splitUp.length < 2) {
            throw new BadLocationDescException();
        }
        return splitUp;
    }
}
