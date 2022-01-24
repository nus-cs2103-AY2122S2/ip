public class HelpCommand extends Command {
    HelpCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printHelp();
    }

    @Override
    boolean isExit() {
        return false;
    }
}
