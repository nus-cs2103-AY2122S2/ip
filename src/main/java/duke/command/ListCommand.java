package duke.command;

import duke.task.TaskList;

public class ListCommand extends Command {
    @Override
    public String executeCommand(TaskList taskList) {
        return taskList.getTasks();
    }
}
