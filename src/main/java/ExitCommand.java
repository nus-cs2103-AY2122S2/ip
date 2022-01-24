public class ExitCommand extends Command {
    ExitCommand(String command) {
        super(command);
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Aww. Bye! See you again soon");
    }

    @Override
    boolean isExit() {
        return true;
    }
}
