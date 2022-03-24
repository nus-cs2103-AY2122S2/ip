package duke;

public class ErrorCommand extends Command {
    ErrorCommand() {
        super("Error");
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui){
        ui.wrongCommandErrorMessage();
    }
}
