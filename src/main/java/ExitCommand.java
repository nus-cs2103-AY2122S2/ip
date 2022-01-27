public class ExitCommand extends Command{

    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.respond("Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}
