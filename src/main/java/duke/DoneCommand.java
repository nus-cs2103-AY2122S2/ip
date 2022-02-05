package duke;

import java.io.IOException;

/**
 * class to mark command as done
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Constructor for DoneCommand
     * @param index of the task in the task list
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * execute delete command
     * @param taskList a list to add all the tasks
     * @param ui to display output
     * @param storage to store task
     * @throws IOException
     * @throws ExceptionHandler
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, ExceptionHandler {
        if (index < 0 || index > taskList.getSize() - 1) {
            throw new ExceptionHandler ("Invalid task number");
        }
        taskList.getListOfTasks().get(index).setDone();
        storage.writeToFile(taskList.getListOfTasks());
        ui.println("Nice! I've marked this task as done:");
        ui.println(taskList.getListOfTasks().get(index));
    }

    /**
     * Method to check for exit command
     * @return A boolean to check if an exit command is entered
     */
    public static boolean isExit() {
        return false;
    }
}


