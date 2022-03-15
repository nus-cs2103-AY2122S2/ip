package duke.command;

import duke.TaskList;
import duke.dukeexception.DukeIndexOutOfBoundException;

public class CommandMark extends Command {
    private String response;
    private TaskList taskList;
    private int taskNo;

    public CommandMark(TaskList taskList, int taskNo) {
        assert taskList != null;
        this.taskList = taskList;
        this.taskNo = taskNo;
    }

    @Override
    public void execute() throws DukeIndexOutOfBoundException {
        String change = taskList.markFinished(taskNo);
        response = change;
    }

    @Override
    public String getResponse() {
        return response;
    }
}
