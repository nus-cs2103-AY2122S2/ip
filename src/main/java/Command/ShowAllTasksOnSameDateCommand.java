package Command;

import DukeUtils.CortanaException;
import DukeUtils.Storage;
import DukeUtils.TaskList;
import DukeUtils.Ui;
import Task.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

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

    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        LocalDate localDate = dateTime.toLocalDate();
        LocalTime localTime = dateTime.toLocalTime();
        int numberOfTasksOnSameDate = 0;
        try {
            for(Task task: taskList.tasksArrayList) {
                if (task instanceof Deadline) { //task is a deadline
                    if (localTime != LocalTime.MAX) { //deadline has date and time
                        if (((Deadline) task).getBy().equals(dateTime)) { //all deadlines with the exact same date and time
                            numberOfTasksOnSameDate ++;
                            ui.printTask(task);
                        }
                    } else { //deadline only has date
                        if (((Deadline) task).getBy().toLocalDate().equals(localDate)) { //all deadlines with the same date regardless of time
                            numberOfTasksOnSameDate ++;
                            ui.printTask(task);
                        }
                    }
                } else if (task instanceof Event) { //task is an event
                    if (localTime != LocalTime.MAX) { //event has date and time
                        if (((Event) task).getAt().equals(dateTime)) { //all events with the exact same date and time
                            numberOfTasksOnSameDate ++;
                            ui.printTask(task);
                        }
                    } else { //event only has date
                        if (((Event) task).getAt().toLocalDate().equals(localDate)) { //all events with the same date regardless of time
                            numberOfTasksOnSameDate ++;
                            ui.printTask(task);
                        }
                    }
                }
            }
            if (numberOfTasksOnSameDate == 0) { //no tasks found
                throw new CortanaException("No task found on " + dateTimeString + "!");
            } else {
                ui.foundTaskOnSameDate(numberOfTasksOnSameDate, dateTimeString);
            }
        } catch (DateTimeParseException e) {
            throw new CortanaException("Invalid date/time!");
        }
    }

    /**
     * The program is not yet exited.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            ShowAllTasksOnSameDateCommand showAllTasksOnSameDateCommand = (ShowAllTasksOnSameDateCommand) obj;
            return showAllTasksOnSameDateCommand.dateTime.equals(this.dateTime) &&
                    showAllTasksOnSameDateCommand.dateTimeString.equals(this.dateTimeString);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, dateTimeString);
    }
}
