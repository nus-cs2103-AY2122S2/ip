public class HelpCommand extends Command {

    public static final String COMMAND = "help";

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
