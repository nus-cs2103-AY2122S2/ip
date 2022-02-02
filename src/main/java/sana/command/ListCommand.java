package sana.command;

import sana.SanaResponse;
import sana.TaskList;

public class ListCommand extends Command {
    public String executeCommand (String[] args, TaskList taskList) {
        return sanaResponse.printTaskList(taskList.toList(), false);
    }
}
