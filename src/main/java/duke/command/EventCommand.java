package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.task.Event;
import duke.task.TaskList;

public class EventCommand extends Command {
    Parser parser;
    String input;
    TaskList tasks;
    Storage storage;

    public EventCommand(Parser parser, String input, TaskList tasks, Storage storage) {
        this.parser = parser;
        this.input = input;
        this.tasks = tasks;
        this.storage = storage;
    }

    public String execute() throws DukeException {
        String response;
        String[] eventInput = parser.parseEvent(input);
        Event newEvent = new Event(eventInput[0], eventInput[1]);
        tasks.addTask(newEvent);
        response = "Got it. I've added this task:\n" + newEvent + "\n" + "Now you have " + tasks.getTasks()
                .size() + " tasks in the list.";
        storage.overwriteFile(tasks.getTasks());
        return response;
    }
}
