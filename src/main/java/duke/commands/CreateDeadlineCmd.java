package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Deadline;

public class CreateDeadlineCmd extends Command {
    private Deadline deadline;

    public CreateDeadlineCmd(String[] cmdVar) {
        String[] deadlineDetails = cmdVar[1].split(" /by ", 2);
        this.deadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        taskList.addTask(deadline);
        return ui.showTaskAdded(deadline);
    }
}