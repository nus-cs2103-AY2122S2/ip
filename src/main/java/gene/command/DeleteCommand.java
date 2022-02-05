package gene.command;

import gene.component.Storage;
import gene.component.TaskList;
import gene.component.Ui;
import gene.task.Task;

/**
 * The delete command class. Deletes a task from both storage and list.
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class DeleteCommand extends Command{
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
     * @param tasks the list of tasks
     * @param userInt the Ui class object
     * @param storage the storage class object
     */
    @Override
    public void execute(TaskList tasks, Ui userInt, Storage storage) {
        try {
            String[] tokens = taskBody.split(" ");
            String strIndex = tokens[1]; //error here
            int index = Integer.parseInt(strIndex);
            Task targetTask = tasks.get(index);
            tasks.remove(index); //error if empty
            storage.deleteLineToFile(index); //error if empty
            System.out.println(
                    "----------------------------" +
                            "----------------------------\n" +
                            "Noted. I've removed this task:\n"
                            + "  " + targetTask + "\n"
                            + "Now you have " + tasks.size() + " tasks in the list."
                            + "\n"
                            + "--------------------------------------------------------"
            );
        } catch (Exception err) {
            System.out.println("File and list is already empty");
        }
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
