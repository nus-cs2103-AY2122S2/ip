package duke.command;

import duke.Response;
import duke.TaskList;
import duke.Todo;
import duke.Ui;

public class CommandTodo extends Command {
    TaskList taskList;
    String todoContent;

    public CommandTodo(TaskList taskList, String todoContent) {
        this.taskList = taskList;
        this.todoContent = todoContent;
    }

    @Override
    public void execute() {
        Todo newTask = new Todo(todoContent);
        taskList.addTask(newTask);
        Ui.wrapPrint(Response.RESPONSE_ADDED + "\n" + newTask + "\n" + Response.taskNo(taskList.size()));
    }
}
