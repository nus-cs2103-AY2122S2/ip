import java.io.IOException;

public class DoneCommand {
    private int index;
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception_handler, IOException {
        if (index < 0 || index > taskList.getSize() - 1) {
            throw new Exception_handler("Index out of range");
        }
        taskList.getListOfTasks().get(index), setDone();
        storage.writeToFile(taskList.getListOfTasks());
        ui.println("Nice! I've marked this task as done");
        ui.println(taskList.getListOfTasks().get(index));
    }
    public boolean isExit() {
        return false;
    }
}

