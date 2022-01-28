package Commands;

import Tasks.Task;
import util.*;
import Tasks.TaskList;

import java.io.IOException;

public class UnmarkCommand extends DukeCommand {
    public UnmarkCommand(String description) {
        super(description);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(this.commandBody) - 1;
            Task task = tasks.get(index);
            task.toggleUncompleted();
            storage.save(tasks);
            ui.showUnmarkReply(task);
        } catch(IndexOutOfBoundsException | IOException e) {
            if (tasks.getSize() == 0) {
                System.out.println("List is empty! Please add a task before removing/marking it.\n");
            } else {
                System.out.println("Please enter a valid number in the list!\n");
            }
        }
    }
}
