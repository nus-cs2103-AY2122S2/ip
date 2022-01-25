public abstract class MarkCommand extends Command{
    public MarkCommand(String userInput){
        super(userInput);
    }

    public abstract boolean execute(Storage storage, Ui ui, TaskManager taskManager);
}
