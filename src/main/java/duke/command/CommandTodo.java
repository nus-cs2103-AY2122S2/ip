package duke.command;

import duke.Response;
import duke.TaskList;
import duke.Todo;
import duke.Ui;

public class CommandTodo extends Command {
    private TaskList taskList;
    private String todoContent;

    public CommandTodo(TaskList taskList, String todoContent) {
        this.taskList = taskList;
        this.todoContent = todoContent;
    }

    @Override
    public String execute() {
        Todo newTask = new Todo(todoContent);
        taskList.addTask(newTask);
        return Response.RESPONSE_ADDED + "\n" + newTask + "\n" + Response.taskNo(taskList.size());
    }
}
