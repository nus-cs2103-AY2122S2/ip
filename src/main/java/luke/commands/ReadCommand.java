package luke.commands;

import luke.data.TaskList;
import luke.data.tasks.Task;

public abstract class ReadCommand extends Command {

    public CommandResult readTaskList(TaskList taskList, String emptyMsg, String startMsg) {
        return readTaskList(taskList, emptyMsg, startMsg, false);
    }

    public CommandResult readTaskList(TaskList taskList, String emptyMsg, String startMsg, boolean isFiltered) {
        String msg = emptyMsg;
        boolean isEmpty = true;
        if (!taskList.isEmpty()) {
            msg = startMsg;
            for (int i = 0; i < taskList.size(); i++) {
                if (!isFiltered || taskList.get(i).isNotFiltered()) {
                    msg += String.format("\t%d. %s\n", i + 1, taskList.get(i));
                    isEmpty = false;
                }
            }
            msg = msg.stripTrailing();
        }
        msg = isEmpty ? emptyMsg : msg;
        return new CommandResult(msg);
    }
}
