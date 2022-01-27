package duke;
public class ListCommand extends Command{

    public String execute(TaskList tasklist, Ui ui, Storage storage) {

        return ui.printAllTasks(tasklist);
    }
}
