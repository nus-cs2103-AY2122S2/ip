package Commands;

import Tasks.Task;
import Tasks.TaskList;
import util.Storage;
import util.Ui;

import java.io.IOException;

public class DeleteCommand extends DukeCommand {
    public DeleteCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(this.commandBody) - 1;
            Task task = tasks.delete(index);
            storage.save(tasks);
            ui.showDeleteReply(task, tasks.getSize());
        } catch (IndexOutOfBoundsException | IOException e) {
            if (tasks.getSize() == 0) {
                System.out.println("List is empty! Please add a task before removing/marking it.\n");
            } else {
                System.out.println("Please enter a valid number in the list!");
            }
        }
    }
}