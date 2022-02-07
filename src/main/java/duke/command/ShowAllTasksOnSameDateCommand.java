package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The type Show all tasks on same date command.
 */
public class ShowAllTasksOnSameDateCommand extends Command {
    private final LocalDateTime dateTime;
    private final String dateTimeString;

    /**
     * Instantiates a new Show all tasks on same date command.
     *
     * @param dateTime       the specified date time
     * @param dateTimeString the specified date time in string
     */
    public ShowAllTasksOnSameDateCommand(LocalDateTime dateTime, String dateTimeString) {
        this.dateTime = dateTime;
        this.dateTimeString = dateTimeString;
    }

    /**
     * Execute show all tasks on the same date/time operation.
     *
     * @param taskList the task list to operate on
     * @param ui the ui to operate on
     * @param storage the storage to operate on
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        int numberOfTasksOnSameDate = (int) taskList.getTaskList()
                .stream()
                .filter(task -> !(task instanceof Todo) && isMatchingDateTime(task, dateTime))
                .count();

        StringBuilder tasksOnSameDate = new StringBuilder();

        taskList.getTaskList()
                .stream()
                .filter(task -> !(task instanceof Todo) && isMatchingDateTime(task, dateTime))
                .forEach(task -> tasksOnSameDate.append(task).append("\n"));

        tasksOnSameDate.append(ui.foundTaskOnSameDate(numberOfTasksOnSameDate, dateTimeString));
        return tasksOnSameDate.toString();
    }

    /**
     * Returns whether a task matches the user input date/time
     *
     * @param task          the particular task in task list
     * @param givenDateTime the given date time
     * @return whether a task matches the user input date/time
     */
    public boolean isMatchingDateTime(Task task, LocalDateTime givenDateTime) {
        assert task instanceof Deadline || task instanceof Event;
        LocalDateTime localDateTimeOfTask = task instanceof Deadline
                ? ((Deadline) task).getBy()
                : ((Event) task).getAt();
        LocalDate localDateOfTask = localDateTimeOfTask.toLocalDate();

        if (givenDateTime.toLocalTime() != LocalTime.MAX) {
            return givenDateTime.equals(localDateTimeOfTask);
        }
        return givenDateTime.toLocalDate().equals(localDateOfTask);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            ShowAllTasksOnSameDateCommand showAllTasksOnSameDateCommand = (ShowAllTasksOnSameDateCommand) obj;
            return showAllTasksOnSameDateCommand.dateTime.equals(this.dateTime)
                    && showAllTasksOnSameDateCommand.dateTimeString.equals(this.dateTimeString);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, dateTimeString);
    }
}
