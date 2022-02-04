package duke;
public class ListCommand extends Command{

    /**
     * Calls ui method to print out all tasks stored
     * @param tasklist TaskList has all current tasks
     * @param ui Ui handles printing to output
     * @param storage Storage saves tasklist
     * @return void
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) {

        ui.printAllTasks(tasklist);
    }
}
