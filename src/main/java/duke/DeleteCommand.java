package duke;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception_handler, IOException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new Exception_handler("Index out of range");
        }
        Task temp = taskList.removeTask(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(temp);
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
        storage.writeToFile(taskList.getListOfTasks());
    }

    public boolean isExit() {
        return false;
    }
}
