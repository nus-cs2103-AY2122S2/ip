package duke;

public class ByeCommand extends Command {
    ByeCommand() {
        super("Bye", true);
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.farewellMessage();
    }
}
