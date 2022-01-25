public class MarkUndoneCommand extends MarkCommand{
    public MarkUndoneCommand(String userInput){
        super(userInput);
    }

    public boolean execute(Storage storage, Ui ui, TaskManager taskManager){
        return false;
    }
}
