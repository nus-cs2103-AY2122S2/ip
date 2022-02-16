package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    Parser parser;
    String input;
    TaskList tasks;
    Storage storage;

    public DeleteCommand(Parser parser, String input, TaskList tasks, Storage storage) {
        this.parser = parser;
        this.input = input;
        this.tasks = tasks;
        this.storage = storage;
    }

    public String execute() throws DukeException {
        String response;
        int index = parser.parseIndex(input);
        if (index < tasks.getTasks().size()) {
            Task removedTask = tasks.removeTask(index);
            response = "Nice! I've removed this task:\n" + removedTask.toString();
        } else {
            throw new DukeException("No such task exists.");
        }
        storage.overwriteFile(tasks.getTasks());
        return response;
    }
}
