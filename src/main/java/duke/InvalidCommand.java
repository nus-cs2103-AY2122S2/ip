package duke;

public class InvalidCommand extends Command {

    public InvalidCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(this.getFirstWord());
    }
}
