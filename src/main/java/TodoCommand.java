import java.io.IOException;

public class TodoCommand extends Command {

    String[] todoCommand;

    public TodoCommand(String[] todoCommand) {
        this.todoCommand = todoCommand;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            Todo currTodo = Parser.parseTodo(todoCommand);
            taskList.add(currTodo);
            Ui.indicateAddedTask(currTodo, taskList);
            storage.appendToFile(currTodo);
        } catch (TodoException | IOException e) {
            Ui.printExceptionMessage(e);
        }
    }
}
