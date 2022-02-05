package gene.command;

import gene.component.Storage;
import gene.component.TaskList;
import gene.component.Ui;
import gene.task.Task;

/**
 * The edit command class. Edits a task in both storage and list.
 * Only two types of edits allowed thus far, mark and unmark tasks
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class EditCommand extends Command{
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
     * @param tasks the list of tasks
     * @param userInt the Ui class object
     * @param storage the storage class object
     */
    @Override
    public void execute(TaskList tasks, Ui userInt, Storage storage) {
        String[] tokens = this.taskBody.split(" ");
        String strIndex = tokens[1]; //error here
        int index = Integer.parseInt(strIndex);
        Task targetTask = tasks.get(index); //have to edit tasklist
        Task newTask;

        if (this.mark.equals("1")) {
            newTask = targetTask.markTask();
            toPrint = "----------------------------" +
                    "----------------------------\n" +
                    "Nice! I've marked this task as done:"
                    + "\n" + "  " + targetTask
                    + "\n"
                    + "--------------------------------------------------------\n";
        } else {
            newTask = targetTask.unmarkTask();
            toPrint = "----------------------------" +
                    "----------------------------\n" +
                    "OK, I've marked this task as not done yet:"
                    + "\n" + "  " + targetTask
                    + "\n"
                    + "--------------------------------------------------------\n";
        }

        tasks.set(index, newTask);
        storage.updatesToFile(index, mark);

        userInt.print(toPrint);

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
