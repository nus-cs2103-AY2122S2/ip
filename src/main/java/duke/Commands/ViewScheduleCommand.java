package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;
import java.time.LocalDate;

/**
 * ViewScheduleCommand is a subclass of DukeCommand that is created when the user inputs "viewSchedule"
 */
public class ViewScheduleCommand extends DukeCommand {
    public ViewScheduleCommand(String date) {
        super(date);
    }

    /**
     * Generates the tasks that are scheduled on the user inputted date
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     * @return String list of tasks
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

        if (tasks.getSize() == 0) {
            return "List is empty! Please add a task first";
        }
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

        if (tasksOnScheduledDate.getSize() == 0) {
            return ui.showEmptySchedule();
        }

        TaskList sortedTaskList = TaskList.sort(tasksOnScheduledDate);
        return ui.showTaskList(sortedTaskList);
    }
}
