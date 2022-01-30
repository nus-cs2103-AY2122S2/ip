package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class AddCommand extends Command {
    private final Task toAdd;

    public AddCommand(String name) {
        toAdd = new ToDo(name);
    }

    public AddCommand(Task.Type type, String name, String details) {
        if (type.equals(Task.Type.DEADLINE)) {
            toAdd = new Deadline(name, details);
        } else {
            toAdd = new Event(name, details);
        }
    }

    public String execute() {
        tasks.add(toAdd);
        return "I have added the following task:\n" + toAdd;
    }
}
