package duke;
public class ListCommand extends Command{

    /**
     * Calls ui method to print out all tasks stored
     * @param tasklist TaskList has all current tasks
     * @param ui Ui handles printing to output
     * @param storage Storage saves tasklist
     * @return String printed by ui
     */
    public String execute(TaskList tasklist, Ui ui, Storage storage) {

        return ui.printAllTasks(tasklist);
    }
}
