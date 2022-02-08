package duke;

public class ListCommand extends Command {

    public ListCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
         if (tasks.isEmpty()) {
            ui.showMessage("Uh-oh! List is empty!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + "." + tasks.get(i));
            }
        }
    }
}
