import java.io.IOException;

public class DeleteCommand extends Command {
    int index;
    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions, IOException {
        if ((index - 1) > taskList.getSize() || (index - 1) < 0) {
            throw new FunBoxExceptions("Wrong index!");
        } else {
            taskList.delete(this.index - 1, ui);
            storage.deleteTask(index);
        }
    }
}
