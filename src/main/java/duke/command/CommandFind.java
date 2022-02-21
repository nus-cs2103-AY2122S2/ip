package duke.command;

import duke.Response;
import duke.TaskList;

public class CommandFind extends Command {
    private String response;
    private String keyword;
    private TaskList taskList;

    public CommandFind(String keyword, TaskList taskList) {
        assert keyword != null;
        assert taskList != null;
        this.keyword = keyword;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        System.out.println("CommandFind Executed");
        TaskList searchResult = this.taskList.search(this.keyword);
        response = Response.RESPONSE_SEARCH + "\n" + searchResult.toString();
    }

    @Override
    public String getResponse() {
        return response;
    }
}
