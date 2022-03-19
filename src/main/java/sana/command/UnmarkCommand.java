package sana.command;

import sana.TaskList;
import sana.exception.OutOfBoundsTaskException;

public class UnmarkCommand extends Command {
    /**
     * Executes the unmark command
     *
     * @param args  arguments in the command
     * @param taskList  sana's tasklist
     *
     * @return  sana's response to the command
     */
    @Override
    public String executeCommand(String[] args, TaskList taskList) {
        try {
            int taskIndex = Integer.parseInt(args[1]) - 1;
            boolean indexOutOfTaskListBounds = taskIndex < 0 || taskIndex >= taskList.taskAmt();
            if (indexOutOfTaskListBounds) {
                throw new OutOfBoundsTaskException();
            }
            taskList.getTask(taskIndex).setDone(false);

            memory.updateMemory(taskList.toList());

            return sanaResponse.markText(false)
                    + sanaResponse.printTaskInList(taskList.getTask(taskIndex));
        } catch (OutOfBoundsTaskException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return sanaResponse.taskNumberFormatError();
        }
    }
}
