package duke;

import java.io.IOException;

/**
 * class to delete commands
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand
     * @param index index of the task in the task list
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Execute this delete command
     * @param taskList a list to store the tasks
     * @param ui to display output
     * @param storage to store tasks
     * @throws ExceptionHandler
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ExceptionHandler, IOException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new ExceptionHandler("Index out of range");
        }
        Task temp = taskList.removeTask(index);
        ui.println("Noted. I've removed this task");
        ui.println(temp);
        ui.println("Now you have " + taskList.getSize() + " tasks in the list.");
        storage.writeToFile(taskList.getListOfTasks());
    }

    /**
     * Method to check for exit command
     * @return A boolean to check if an exit command is entered
     */
    public static boolean isExit() {
        return false;
    }
}
