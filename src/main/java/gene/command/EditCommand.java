package gene.command;

import gene.component.*;
import gene.location.Location;
import gene.task.Task;

/**
 * The edit command class. Edits a task in both storage and list.
 * Only two types of edits allowed thus far, mark and unmark tasks
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class EditCommand extends Command {
    private final String inputBody;
    private final String mark;
    private String toPrint;

    /**
     * The constructor for the edit command. Take note of the mark parameter
     *
     * @param body
     * @param mark whether or not the object is to be marked or not.
     */
    public EditCommand(String body, String mark) {
        this.inputBody = body;
        this.mark = mark;
    }

    /**
     * The execute method for the edit command, which holds all the instructions
     * for when this command is to be executed. Whether or not to mark or unmark
     * depends on the mark variable of the edit command instance.
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
        String strIndex = "";
        int index = 1;
        try {
            String[] tokens = inputBody.split(" ");
            toDelete = tokens[1];
            strIndex = tokens[2]; //error here
            index = Integer.parseInt(strIndex);
        } catch (Exception err) {
            err.printStackTrace();
        }
        try {
            switch (toDelete) {
            case "location":
                if (index >= geneLocs.size()) {
                    throw new Exception("The index given exceeds the size of the list of tasks");
                }
                return handleEditLocation(geneLocs, index, geneLocationStorage);
            case "task":
                if (index >= geneTasks.size()) {
                    throw new Exception("The index given exceeds the size of the list of tasks");
                }
                return handleEditTask(geneTasks, index, geneTaskStorage);
            default:
                return "";
            }
        } catch (Exception err) {
            return Ui.showLine() + "\n" + err.getMessage() + Ui.showLine();
        }
    }

    private String handleEditTask(TaskList geneTasks, int index, TaskStorage geneTaskStorage) {
        Task targetTask = geneTasks.get(index);
        Task newTask;
        if (this.mark.equals("1")) {
            newTask = targetTask.markTask();
            toPrint = Ui.showLine() + "\n"
                    +
                    "Nice! I've marked this task as done:"
                    + "\n" + "  " + targetTask
                    + "\n"
                    + Ui.showLine();
        } else {
            newTask = targetTask.unmarkTask();
            toPrint = Ui.showLine() + "\n"
                    +
                    "OK, I've marked this task as not done yet:"
                    + "\n" + "  " + targetTask
                    + "\n"
                    + Ui.showLine();
        }
        geneTasks.set(index, newTask); //error if empty
        geneTaskStorage.updatesToFile(index, mark); //error if empty
        return toPrint;
    }

    private String handleEditLocation(LocationList geneLocs, int index, LocationStorage geneLocationStorage) {
        Location targetLocation = geneLocs.get(index);
        Location newLocation;
        if (this.mark.equals("1")) {
            newLocation = targetLocation.markLocation();
            toPrint = Ui.showLine() + "\n"
                    +
                    "Nice! I've marked this location as visited:"
                    + "\n" + "  " + targetLocation
                    + "\n"
                    + Ui.showLine();
        } else {
            newLocation = targetLocation.unmarkLocation();
            toPrint = Ui.showLine() + "\n"
                    +
                    "OK, I've marked this location as not visited yet:"
                    + "\n" + "  " + targetLocation
                    + "\n"
                    + Ui.showLine();
        }
        geneLocs.set(index, newLocation); //error if empty
        geneLocationStorage.updatesToFile(index, mark); //error if empty
        return toPrint;
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
