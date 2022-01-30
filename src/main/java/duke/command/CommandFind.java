package duke.command;

import duke.Response;
import duke.TaskList;
import duke.Ui;

public class CommandFind extends Command {
    String keyword;
    TaskList taskList;

    public CommandFind(String keyword, TaskList taskList) {
        this.keyword = keyword;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        System.out.println("CommandFind Executed");
        TaskList searchResult = this.taskList.search(this.keyword);
        Ui.wrapPrint(Response.RESPONSE_SEARCH + "\n" + searchResult.toString());
    }
}
