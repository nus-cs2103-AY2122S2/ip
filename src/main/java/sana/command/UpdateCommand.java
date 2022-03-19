package sana.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import sana.TaskList;
import sana.exception.IncompatibleTaskException;
import sana.exception.OutOfBoundsTaskException;
import sana.exception.SanaException;
import sana.exception.UnknownCommandException;
import sana.task.Deadline;
import sana.task.Event;
import sana.task.Task;

/**
 * Updates a task that is in the task list
 *
 * Comes in format: update (task index) (/name or /date) (new name or new date)
 */
public class UpdateCommand extends Command {
    private static final int INPUT_TO_INDEX_OFFSET = 1;

    @Override
    public String executeCommand (String[] args, TaskList taskList) {
        try {
            int taskIndex = Integer.parseInt(args[1]) - INPUT_TO_INDEX_OFFSET;
            boolean indexOutOfTaskListBounds = taskIndex < 0 || taskIndex >= taskList.taskAmt();
            if (indexOutOfTaskListBounds) {
                throw new OutOfBoundsTaskException();
            }
            Task taskToUpdate = taskList.getTask(taskIndex);

            if (args[2].equals("/name")) {
                taskToUpdate.rename(args[3]);
            } else if (args[2].equals("/date")) {
                LocalDate newDate = LocalDate.parse(args[3]);

                if (taskToUpdate.getClass() == Deadline.class) {
                    Deadline deadlineToUpdate = (Deadline) taskToUpdate;
                    deadlineToUpdate.changeDate(newDate);
                } else if (taskToUpdate.getClass() == Event.class) {
                    Event eventToUpdate = (Event) taskToUpdate;
                    eventToUpdate.changeDate(newDate);
                } else {
                    throw new IncompatibleTaskException();
                }
            } else {
                throw new UnknownCommandException();
            }

            memory.updateMemory(taskList.toList());

            return sanaResponse.updateTask(taskToUpdate)
                    + sanaResponse.printTaskInList(taskList.getTask(taskIndex));
        } catch (SanaException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return sanaResponse.taskNumberFormatError();
        } catch (DateTimeParseException e) {
            return sanaResponse.dateFormatError();
        }
    }
}
