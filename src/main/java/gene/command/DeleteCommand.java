package gene.command;

import gene.component.*;
import gene.task.Task;

/**
 * The delete command class. Deletes a task from both storage and list.
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class DeleteCommand extends Command {
    private final String taskBody;

    /**
     * The delete command constructor
     *
     * @param body
     */
    public DeleteCommand(String body) {
        this.taskBody = body;
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
    public String execute(TaskList geneTasks, Ui geneUi, TaskStorage geneTaskStorage, LocationList geneLocs, LocationStorage geneLocationStorage) {
        try {
            String[] tokens = taskBody.split(" ");
            String strIndex = tokens[1]; //error here
            int index = Integer.parseInt(strIndex);
            Task targetTask = geneTasks.get(index);
            geneTasks.remove(index); //error if empty
            geneTaskStorage.deleteLineToFile(index); //error if empty
            return Ui.showLine()
                    + "Noted. I've removed this task:\n"
                            + "  " + targetTask + "\n"
                            + "Now you have " + geneTasks.size() + " tasks in the list."
                            + "\n"
                            + Ui.showLine();
        } catch (Exception err) {
            System.out.println("File and list is already empty");
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
