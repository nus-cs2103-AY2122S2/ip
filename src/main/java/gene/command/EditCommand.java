package gene.command;

import gene.component.*;
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
    private final String taskBody;
    private final String mark;
    private String toPrint;

    /**
     * The constructor for the edit command. Take note of the mark parameter
     *
     * @param body
     * @param mark whether or not the object is to be marked or not.
     */
    public EditCommand(String body, String mark) {
        this.taskBody = body;
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
    public String execute(TaskList geneTasks, Ui geneUi, TaskStorage geneTaskStorage, LocationList geneLocs, LocationStorage geneLocationStorage) {
        String[] tokens = this.taskBody.split(" ");
        String strIndex = tokens[1]; //error here
        int index = Integer.parseInt(strIndex);
        Task targetTask = geneTasks.get(index); //have to edit tasklist
        Task newTask;
        if (this.mark.equals("1")) {
            newTask = targetTask.markTask();
            toPrint = Ui.showLine()
                    +
                    "Nice! I've marked this task as done:"
                    + "\n" + "  " + targetTask
                    + "\n"
                    + Ui.showLine();
        } else {
            newTask = targetTask.unmarkTask();
            toPrint = Ui.showLine()
                    +
                    "OK, I've marked this task as not done yet:"
                    + "\n" + "  " + targetTask
                    + "\n"
                    + Ui.showLine();
        }
        geneTasks.set(index, newTask);
        geneTaskStorage.updatesToFile(index, mark);
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
