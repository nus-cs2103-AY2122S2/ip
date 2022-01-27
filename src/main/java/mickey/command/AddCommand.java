package mickey.command;

import mickey.app.MickeyException;
import mickey.app.Storage;
import mickey.app.Ui;
import mickey.task.Deadline;
import mickey.task.Event;
import mickey.task.Task;
import mickey.task.TaskList;
import mickey.task.ToDo;

public class AddCommand extends Command {
    public AddCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MickeyException {
        Task newTask = null;
        switch (cmd) {
            case "todo":
                newTask = new ToDo(getDescription());
                ui.showNewTask(tasks.size() + 1, newTask, "todo");
                break;
            case "deadline":
                newTask = new Deadline(getDescription(), getDate());
                ui.showNewTask(tasks.size() + 1, newTask, "deadline");
                break;
            case "event":
                newTask = new Event(getDescription(), getDate());
                ui.showNewTask(tasks.size() + 1, newTask, "event");
                break;
            default:
        }
        if (newTask != null) {
            tasks.add(newTask);
        }
    }
}
