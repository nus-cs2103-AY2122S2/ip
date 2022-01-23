public class ListCommand extends Command{
    public void execute(TaskList t, Ui u, Storage s) {
        u.printWrapper(t.listTasks());
    }

    public boolean isExit() {
        return false;
    }
}
