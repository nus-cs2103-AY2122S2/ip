public abstract class Command {

    protected CommandType commandType;
    protected boolean active;
    
    public Command(CommandType commandType) {
        this.commandType = commandType;
        this.active = true;
    }

    public abstract boolean isActive();

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    
}
