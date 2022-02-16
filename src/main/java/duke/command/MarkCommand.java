package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.task.TaskList;

public class MarkCommand extends Command {
    Parser parser;
    String input;
    TaskList tasks;
    Storage storage;

    public MarkCommand(Parser parser, String input, TaskList tasks, Storage storage) {
        this.parser = parser;
        this.input = input;
        this.tasks = tasks;
        this.storage = storage;
    }

    public String execute() throws DukeException {
        String response;
        int index = parser.parseIndex(input);
        if (index < tasks.getTasks().size()) {
            tasks.markTaskDone(index);
            response = "Nice! I've marked this task as done:\n" + tasks.getTask(index).toString();
        } else {
            throw new DukeException("No such task exists.");
        }
        storage.overwriteFile(tasks.getTasks());
        return response;
    }
}
