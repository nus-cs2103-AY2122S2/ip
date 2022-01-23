public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui io, Storage storage) {
        io.bye();
        exit = true;
    }
}
