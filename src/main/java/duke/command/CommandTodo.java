package duke.command;

import duke.Response;
import duke.TaskList;
import duke.Todo;

public class CommandTodo extends Command {
    private String response;
    private TaskList taskList;
    private String todoContent;

    public CommandTodo(TaskList taskList, String todoContent) {
        assert taskList != null;
        assert todoContent != null;
        this.taskList = taskList;
        this.todoContent = todoContent;
    }

    @Override
    public void execute() {
        Todo newTask = new Todo(todoContent);
        taskList.addTask(newTask);
        response = Response.RESPONSE_ADDED + "\n" + newTask + "\n" + Response.taskNo(taskList.size());
    }

    @Override
    public String getResponse() {
        return response;
    }
}
