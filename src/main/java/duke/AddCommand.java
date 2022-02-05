package duke;

import java.io.IOException;

/**
 * add tasks
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand
     * @param task to be added to the task list
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * @param taskList a list to store all tasks
     * @param ui to display output
     * @param storage to store task
     * @throws ExceptionHandler
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(task);
        ui.println("Got it. I've added this task:");
        ui.println(task);
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
