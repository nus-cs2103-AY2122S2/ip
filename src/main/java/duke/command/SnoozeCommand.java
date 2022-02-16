package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

public class SnoozeCommand extends Command {
    Parser parser;
    String input;
    TaskList tasks;
    Storage storage;

    public SnoozeCommand(Parser parser, String input, TaskList tasks, Storage storage) {
        this.parser = parser;
        this.input = input;
        this.tasks = tasks;
        this.storage = storage;
    }

    public String execute() throws DukeException {
        String response;
        int snoozeIndex = parser.parseIndex(input);
        Task selectedTask = tasks.getTask(snoozeIndex);
        boolean isSuccessful = false;
        if (selectedTask instanceof Event) {
            isSuccessful = ((Event)selectedTask).snooze();
        }
        if (selectedTask instanceof Deadline) {
            isSuccessful = ((Deadline)selectedTask).snooze();
        }
        if (isSuccessful) {
            response = "Task snoozed by 10min!\n" + selectedTask.toString();
        } else {
            throw new DukeException("Sorry! This task can't be snoozed!");
        }
        return response;
    }
}
