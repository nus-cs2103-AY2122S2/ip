public class DeleteCommand extends Command{
    public DeleteCommand(String userInput){
        super(userInput);
    }

    public boolean execute(Storage storage, Ui ui, TaskManager taskManager){
        return false;
    }
}
