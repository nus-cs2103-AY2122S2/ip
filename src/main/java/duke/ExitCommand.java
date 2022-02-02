package duke;

public class ExitCommand extends Command{
    /**
     * Execute exit command
     * @param taskList list to store all tasks
     * @param ui display output
     * @param storage store tasks
     * @throws Exception_handler
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception_handler {
        ui.println("Bye. Hope to see you again soon!");
    }

    /**
     * Method to check for exit command
     * @return A boolean to check if an exit command is entered
     */
    public boolean isExit(){
        return true;
    }
}