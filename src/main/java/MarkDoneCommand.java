public class MarkDoneCommand extends MarkCommand{
    public MarkDoneCommand(String userInput){
        super(userInput);
    }

    @Override
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) {
        return false;
    }
}
