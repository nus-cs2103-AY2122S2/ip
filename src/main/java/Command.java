public abstract class Command {

    private boolean exit = false;
    private Task task;

    public Command() {
    }

    public void execute(TaskMaster tasks, Ui ui, Storage storage) throws DukeException {

    }

    public boolean isExit() {
        return this.exit;
    }

    public void startExit() {
        this.exit = true;
    }


}
