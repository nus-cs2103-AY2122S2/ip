public abstract class Command {
    protected String command;
    protected String[] tokenizedCommand;
    protected boolean isRun;

    public Command(String command, String[] tokenizedCommand) {
        this.command = command;
        this.tokenizedCommand = tokenizedCommand;
        this.isRun = true;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isRunProgram() {
        return this.isRun;
    }
}
