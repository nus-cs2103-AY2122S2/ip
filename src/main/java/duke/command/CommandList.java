package duke.command;

import duke.TaskList;

public class CommandList extends Command {
    private String response;
    private TaskList taskList;

    public CommandList(TaskList taskList) {
        assert taskList != null;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        response = taskList.toString();
    }

    @Override
    public String getResponse() {
        return response;
    }
}

