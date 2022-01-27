import java.io.IOException;

public class UnmarkDoneCommand extends Command {
    private int index;

    public UnmarkDoneCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(List taskList, Ui ui, Storage storage) throws IOException {
        taskList.unmarkDone(index);
        ui.printUnmarkDoneTask(taskList.getArrayList().get(index));
        storage.writeToFile("data/duke.txt", taskList);
    }
}