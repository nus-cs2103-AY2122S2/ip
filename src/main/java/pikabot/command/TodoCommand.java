package pikabot.command;

import java.io.IOException;

import pikabot.Parser;
import pikabot.Storage;
import pikabot.TaskList;
import pikabot.Ui;
import pikabot.exception.TodoException;
import pikabot.task.Todo;

/**
 * Represents a command to create a Todo task.
 */
public class TodoCommand extends Command {

    private String[] todoCommand;

    /**
     * Constructs a TodoCommand.
     *
     * @param todoCommand String array containing input string from user.
     */
    public TodoCommand(String[] todoCommand) {
        this.todoCommand = todoCommand;
    }

    /**
     * Executes TodoCommand by creating a Todo task.
     *
     * @param taskList TaskList to add the Todo task to.
     * @param storage Storage to update data file in computer.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            Todo currTodo = Parser.parseTodo(todoCommand);
            taskList.add(currTodo);
            storage.appendToFile(currTodo);
            return Ui.indicateAddedTask(currTodo, taskList);

        } catch (TodoException | IOException e) {
            return Ui.printExceptionMessage(e);
        }
    }
}
