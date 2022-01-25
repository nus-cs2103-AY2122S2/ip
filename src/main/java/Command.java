abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return isExit;
    }
}
