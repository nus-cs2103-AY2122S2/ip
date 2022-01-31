package kenobi.command;

import kenobi.task.Deadline;
import kenobi.task.Event;
import kenobi.task.Task;
import kenobi.task.ToDo;

import java.time.LocalDate;

public class AddCommand extends Command {
    private final Task toAdd;

    public AddCommand(String name) {
        toAdd = new ToDo(name);
    }

    public AddCommand(Task.Type type, String name, LocalDate date) {
        if (type.equals(Task.Type.DEADLINE)) {
            toAdd = new Deadline(name, date);
        } else {
            toAdd = new Event(name, date);
        }
    }

    public String execute() {
        tasks.add(toAdd);
        return "I have added the following task:\n" + toAdd;
    }
}
