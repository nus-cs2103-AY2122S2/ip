public abstract class Command {

    CommandType commandType;
    
    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public abstract void execute(Ui ui, Storage storage);
    
}
