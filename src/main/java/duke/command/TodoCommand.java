package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand extends Command {
    Parser parser;
    String input;
    TaskList tasks;
    Storage storage;

    public TodoCommand(Parser parser, String input, TaskList tasks, Storage storage) {
        this.parser = parser;
        this.input = input;
        this.tasks = tasks;
        this.storage = storage;
    }

    public String execute() throws DukeException {
        String response;
        Todo newTodo = new Todo(parser.parseTodo(input));
        tasks.addTask(newTodo);
        response = "Got it. I've added this task:\n" + newTodo + "\n" + "Now you have " + tasks.getTasks()
                .size() + " tasks in the list.";
        storage.overwriteFile(tasks.getTasks());
        return response;
    }
}
