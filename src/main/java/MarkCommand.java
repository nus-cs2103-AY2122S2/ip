public class MarkCommand extends Command {
    private final int i;

    MarkCommand (int i) {
        this.i = i;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {

            Task t = tasks.get(i);
            t.mark();
            ui.respond("Nice! I've marked this task as done:\n  " + t);
    }
}
