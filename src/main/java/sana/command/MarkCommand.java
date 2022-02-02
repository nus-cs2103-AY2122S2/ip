package sana.command;

import sana.SanaResponse;
import sana.TaskList;
import sana.exception.OutOfBoundsTaskException;

public class MarkCommand extends Command {
    @Override
    public String executeCommand(String[] args, TaskList taskList) {
        try {
            int taskIndex = Integer.parseInt(args[1]);
            if (taskIndex < 0 || taskIndex >= taskList.taskAmt()) {
                throw new OutOfBoundsTaskException();
            }
            taskList.getTask(taskIndex).setDone(true);

            memory.updateMemory(taskList.toList());

            return sanaResponse.markText(true)
                    + sanaResponse.printTaskInList(taskList.getTask(taskIndex));
        } catch (OutOfBoundsTaskException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return sanaResponse.taskNumberFormatError();
        }
    }
}
