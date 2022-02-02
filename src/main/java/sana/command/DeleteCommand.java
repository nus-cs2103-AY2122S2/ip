package sana.command;

import sana.TaskList;
import sana.exception.OutOfBoundsTaskException;
import sana.task.Task;

public class DeleteCommand extends Command {
    @Override
    public String executeCommand (String[] args, TaskList taskList) {
        try {
            Integer deleteIndex = Integer.parseInt(args[1]) - 1;
            if (deleteIndex < 0 || deleteIndex >= taskList.taskAmt()) {
                throw new OutOfBoundsTaskException();
            }
            Task taskToDelete = taskList.getTask(deleteIndex);
            taskList.removeTask(deleteIndex);

            memory.updateMemory(taskList.toList());

            return sanaResponse.deleteTask(taskToDelete, taskList.taskAmt());
        } catch (OutOfBoundsTaskException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return sanaResponse.taskNumberFormatError();
        }
    }
}
