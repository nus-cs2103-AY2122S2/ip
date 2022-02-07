package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class ViewSchedulesCommand extends Command {
    private final LocalDate date;
    private final String dateString;

    public ViewSchedulesCommand(LocalDate date, String dateString) {
        this.date = date;
        this.dateString = dateString;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        int numberOfTasksOnSameDate = (int) taskList.getTaskList()
                .stream()
                .filter(task -> !(task instanceof Todo) && isMatchingDate(task, date))
                .count();

        StringBuilder tasksOnSameDate = new StringBuilder();
        taskList.getTaskList()
                .stream()
                .filter(task -> !(task instanceof Todo) && isMatchingDate(task, date))
                .sorted()
                .forEach(task -> tasksOnSameDate.append(task).append("\n"));
        tasksOnSameDate.insert(0, ui.viewSchedulesOnDate(numberOfTasksOnSameDate, dateString));

        if (numberOfTasksOnSameDate == 0) {
            /* no tasks found */
            throw new CortanaException("You have nothing to do on " + dateString + "!");
        }
        return tasksOnSameDate.toString();
    }

    public boolean isMatchingDate(Task task, LocalDate givenDate) {
        assert task instanceof Deadline || task instanceof Event;
        LocalDateTime localDateTimeOfTask = task instanceof Deadline
                ? ((Deadline) task).getBy()
                : ((Event) task).getAt();
        LocalDate localDateOfTask = localDateTimeOfTask.toLocalDate();

        return givenDate.equals(localDateOfTask);
    }
}
