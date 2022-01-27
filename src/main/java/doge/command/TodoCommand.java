package doge.command;

import doge.Storage;
import doge.TaskList;
import doge.Ui;
import doge.exception.DogeException;
import doge.exception.TodoException;
import doge.task.Task;

public class TodoCommand extends Command {
    public TodoCommand (Task task) {
        super(task);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        if (this.task.getDescription().isEmpty()) {
            throw new TodoException("So doing NOTHING is a task? doge.task.Task details cannot be left empty!");
        } else {
            tasks.addTask(this.task);
        }
    }

    @Override
    public String toString() {
        return "Stop troubling me, I've already added this task:";
    }

}
