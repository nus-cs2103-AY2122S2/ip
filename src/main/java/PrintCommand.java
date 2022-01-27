public class PrintCommand extends Command{
    private final String text;

    PrintCommand(String s) {
        text = s;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.respond(text);
    }
}
