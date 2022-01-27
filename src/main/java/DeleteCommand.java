public class DeleteCommand extends Command{
    private final int i;

    DeleteCommand (int i) {
        this.i = i;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {

            Task t = tasks.get(i);
            tasks.remove(t);
            ui.respond( "Noted. I've removed this task: \n  " +
                    t + "\n     Now you have " + tasks.size() + " tasks in the list.");
        }
    }
