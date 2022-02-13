package Commands;

import Exceptions.DukeException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.TaskList;
import util.Parser;
import util.Storage;
import util.Ui;

import java.io.IOException;
import java.time.LocalDate;

public class ViewScheduleCommand extends DukeCommand {
    public ViewScheduleCommand(String date) {
        super(date);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

        LocalDate currentDate = Parser.parseDate(this.commandBody);
        TaskList tasksOnScheduledDate = new TaskList();

        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            LocalDate taskDate;
            if (task instanceof Deadline) {
                taskDate = ((Deadline) task).deadline.toLocalDate();
            } else if (task instanceof Event) {
                taskDate = ((Event) task).getEventDate().toLocalDate();
            } else {
                continue;
            }

            if (currentDate.isEqual(taskDate)) {
                tasksOnScheduledDate.add(task);
            }
        }

        TaskList sortedTaskList = TaskList.sort(tasksOnScheduledDate);
        return ui.showTaskList(sortedTaskList);
    }
}
