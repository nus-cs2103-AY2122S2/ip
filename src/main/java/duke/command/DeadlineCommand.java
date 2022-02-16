package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.task.Deadline;
import duke.task.TaskList;

public class DeadlineCommand extends Command {
    Parser parser;
    String input;
    TaskList tasks;
    Storage storage;

    public DeadlineCommand(Parser parser, String input, TaskList tasks, Storage storage) {
        this.parser = parser;
        this.input = input;
        this.tasks = tasks;
        this.storage = storage;
    }

    public String execute() throws DukeException {
        String response;
        String[] deadlineInput = parser.parseDeadline(input);
        Deadline newDeadline = new Deadline(deadlineInput[0], deadlineInput[1]);
        tasks.addTask(newDeadline);
        response = "Got it. I've added this task:\n" + newDeadline + "\n" + "Now you have " + tasks.getTasks()
                .size() + " tasks in the list.";
        storage.overwriteFile(tasks.getTasks());
        return response;
    }
}
