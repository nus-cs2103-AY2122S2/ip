public class ListCommand extends Command {
    /**
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {

       return ui.printTasks(taskList);
    }
}