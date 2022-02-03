package duke;

public class ListCommand extends Command{
    /**
     * Execute ListCommand
     * @param taskList list to store all tasks
     * @param ui display output
     * @param storage store tasks
     * @throws Exception_handler
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception_handler {
        if(taskList.getSize() <= 0) {
            throw new Exception_handler("No tasks in the list");
        }
        int count = 1;
        for (Task t : taskList.getListOfTasks()) {
            ui.println(count + ". " + t);
            count++;
        }
    }

    /**
     * Method to check for exit command
     * @return A boolean to check if an exit command is entered
     */
    public boolean isExit(){
        return false;
    }
}