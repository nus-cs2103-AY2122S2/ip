package duke.command;

import duke.Storage;
import duke.TaskMaster;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(String details) {
        this.task = new ToDo(details);
    }

    public AddCommand(String cmd, String details, LocalDate date) {
        if (cmd .equals("event")) {
            this.task = new Event(details, date);
        } else {
            this.task = new Deadline(details, date);
        }
    }

    @Override
    public void execute(TaskMaster tasks, Ui ui, Storage storage) {
        tasks.add_task(this.task);
        ui.notifyAddedTaskMessage(this.task);
        storage.saveToFile(tasks.get_tasks());
    }
}
