package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.task.TaskList;

public class UnmarkCommand extends Command {
    Parser parser;
    String input;
    TaskList tasks;
    Storage storage;

    public UnmarkCommand(Parser parser, String input, TaskList tasks, Storage storage) {
        this.parser = parser;
        this.input = input;
        this.tasks = tasks;
        this.storage = storage;
    }

    public String execute() throws DukeException {
        String response;
        int index = parser.parseIndex(input);
        if (index < tasks.getTasks().size()) {
            tasks.markTaskNotDone(index);
            response = "Nice! I've marked this task as not done:\n" + tasks.getTask(index).toString();
        } else {
            throw new DukeException("No such task exists.");
        }
        storage.overwriteFile(tasks.getTasks());
        return response;
    }
}
