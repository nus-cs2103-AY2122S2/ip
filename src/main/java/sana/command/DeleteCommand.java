package sana.command;

import sana.TaskList;
import sana.exception.OutOfBoundsTaskException;
import sana.task.Task;

public class DeleteCommand extends Command {

    /**
     * Supports the delete command
     *
     * @param args  the arguments in the command
     * @param taskList  sana's tasklist
     *
     * @return  sana's response to the command
     */
    @Override
    public String executeCommand (String[] args, TaskList taskList) {
        try {
            int deleteIndex = Integer.parseInt(args[1]) - 1;
            boolean indexOutOfTaskListBounds = deleteIndex < 0 || deleteIndex >= taskList.taskAmt();
            if (indexOutOfTaskListBounds) {
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
