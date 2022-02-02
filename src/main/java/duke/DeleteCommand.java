package duke;

import java.io.IOException;

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
     * @throws Exception_handler
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception_handler, IOException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new Exception_handler("Index out of range");
        }
        Task temp = taskList.removeTask(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(temp);
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
        storage.writeToFile(taskList.getListOfTasks());
    }

    /**
     * Method to check for exit command
     * @return A boolean to check if an exit command is entered
     */
    public boolean isExit() {
        return false;
    }
}
