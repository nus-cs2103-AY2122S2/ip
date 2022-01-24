public class ListCommand extends Command {
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Your outstanding tasks as of now are as listed:\n" + tasks.toString();
    }

    public boolean isExit() {
        return false;
    }
}