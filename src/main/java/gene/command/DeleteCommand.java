package gene.command;

import gene.component.*;
import gene.location.Location;
import gene.task.Task;

/**
 * The delete command class. Deletes a task from both storage and list.
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class DeleteCommand extends Command {
    private final String inputBody;

    /**
     * The delete command constructor
     *
     * @param body
     */
    public DeleteCommand(String body) {
        this.inputBody = body;
    }

    /**
     * The execute command which holds the instructions for when the command is
     * executed. Removes tasks from Gene's list as well as storage.
     *
     * @param geneTasks the list of tasks
     * @param geneUi the Ui class task object
     * @param geneTaskStorage the tastorage class object
     * @param geneLocs the list of locations
     * @param geneLocationStorage the location storage class object
     */
    @Override
    public String execute(TaskList geneTasks, Ui geneUi, TaskStorage geneTaskStorage,
                          LocationList geneLocs, LocationStorage geneLocationStorage) {
        String toDelete = "";
        String strIndex;
        int index = 1;
        try {
            String[] tokens = inputBody.split(" ");
            toDelete = tokens[1];
            strIndex = tokens[2]; //error here
            index = Integer.parseInt(strIndex);
        } catch (Exception err) {
            return "Seems like you keyed in the wrong string of words...\n"
                    + "Write it like this instead: " + "delete location/task 0";
        }
        try {
            switch (toDelete) {
            case "location":
                Location targetLocation = geneLocs.get(index);
                geneLocs.remove(index); //error if empty
                geneLocationStorage.deleteLineToFile(index); //error if empty
                return Ui.showLine() + "\n"
                        + "Noted. I've removed this location:\n"
                        + "  " + targetLocation + "\n"
                        + "Now you have " + geneLocs.size() + " locations in the list."
                        + "\n"
                        + Ui.showLine();
            case "task":
                Task targetTask = geneTasks.get(index);
                geneTasks.remove(index); //error if empty
                geneTaskStorage.deleteLineToFile(index); //error if empty
                return Ui.showLine() + "\n"
                        + "Noted. I've removed this task:\n"
                        + "  " + targetTask + "\n"
                        + "Now you have " + geneTasks.size() + " tasks in the list."
                        + "\n"
                        + Ui.showLine();
            default:
                break;
            }
        } catch (Exception err) {
            return "File and list is already empty";
        }
        return "";
    }

    /**
     * The method to distinguish this command from an exit command
     *
     * @return must return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
