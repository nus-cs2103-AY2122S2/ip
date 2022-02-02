package sana.command;

import sana.TaskList;
import sana.exception.OutOfBoundsTaskException;

public class UnmarkCommand extends Command {
    @Override
    public String executeCommand(String[] args, TaskList taskList) {
        try {
            int taskIndex = Integer.parseInt(args[1]);
            if (taskIndex < 0 || taskIndex >= taskList.taskAmt()) {
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
