import java.io.IOException;

public class TodoCommand extends Command {
    TodoCommand(String commandArgument) {
        super(commandArgument);
    }

    public void execute(Storage storage, TaskList taskList, Ui ui) throws IOException {
        String todoTask = commandArgument.trim();
        if (todoTask.isEmpty()) {
            ui.emptyTodoDescriptionErrorMessage();
        } else {
            taskList.addTaskToTaskList(new Todo(todoTask));
            storage.save(taskList);
        }
    }
}
