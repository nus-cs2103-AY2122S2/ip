package duke.command;

import duke.Response;
import duke.TaskList;
import duke.Ui;

public class CommandFind extends Command {
    private String keyword;
    private TaskList taskList;

    public CommandFind(String keyword, TaskList taskList) {
        this.keyword = keyword;
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        System.out.println("CommandFind Executed");
        TaskList searchResult = this.taskList.search(this.keyword);
        return Response.RESPONSE_SEARCH + "\n" + searchResult.toString();
    }
}
