package doge.command;

import doge.Doge;
import doge.Storage;
import doge.TaskList;
import doge.Ui;
import doge.exception.DeadlineException;
import doge.exception.DogeException;
import doge.task.Task;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    public DeadlineCommand (Task task) {
        super(task);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        String[] curr = this.task.getDescription().split("/");

        if (this.task.getDescription().isEmpty()) {
            throw new DeadlineException("What is the event ABOUT?");
        } else if (curr.length == 1) {
            throw new DeadlineException("Where is the END DATE?");
        } else {
            LocalDateTime dateTime = Doge.getDateTime(curr[1].trim());
            this.task.setDateTime(dateTime);
            tasks.addTask(this.task);
            ui.respond(this);
        }
    }

    @Override
    public String toString() {
        return "Stop troubling me, I've already added this task:";
    }
}
