public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 1; i <= tasks.length(); i++) {
            System.out.println(i + ". " + tasks.at(i).toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
