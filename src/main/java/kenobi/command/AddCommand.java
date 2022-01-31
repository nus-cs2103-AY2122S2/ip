package kenobi.command;

import kenobi.task.Deadline;
import kenobi.task.Event;
import kenobi.task.Task;
import kenobi.task.TaskException;
import kenobi.task.ToDo;

import java.time.LocalDate;

public class AddCommand extends Command {
    private final Task.Type taskType;
    private final String taskName;
    private final LocalDate taskDate;

    public AddCommand(String name) {
        taskType = Task.Type.TODO;
        taskName = name;
        taskDate = null;
    }

    public AddCommand(Task.Type type, String name, LocalDate date) {
        taskType = type;
        taskName = name;
        taskDate = date;
    }

    public String execute() {
        Task toAdd;

        try {
            switch (taskType) {
            case DEADLINE:
                toAdd = new Deadline(taskName, taskDate);
                break;

            case EVENT:
                toAdd = new Event(taskName, taskDate);
                break;

            default:
            case TODO:
                toAdd = new ToDo(taskName);
                break;
            }
        } catch (TaskException e) {
            return e.getMessage();
        }

        tasks.add(toAdd);
        return "I added the following task to the archives:\n" + toAdd;
    }
}
