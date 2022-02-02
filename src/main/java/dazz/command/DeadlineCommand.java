package dazz.command;

import java.time.LocalDateTime;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;
import dazz.task.Deadline;

public class DeadlineCommand extends Command {
    private Deadline deadline;

    public DeadlineCommand(String description, LocalDateTime dateTime) {
        this.deadline = new Deadline(description, dateTime);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.deadline);
        ui.showAdd(deadline, taskList);
        storage.updateList(taskList);
    }

}
