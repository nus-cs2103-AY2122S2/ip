package doge.command;

import doge.Doge;
import doge.Storage;
import doge.TaskList;
import doge.Ui;
import doge.exception.DogeException;
import doge.exception.EventException;
import doge.task.Task;

import java.time.LocalDateTime;

public class EventCommand extends Command {

    public EventCommand(Task task) {
        super(task);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        String[] curr = this.task.getDescription().split("/");

        if (this.task.getDescription().isEmpty()) {
            throw new EventException("What is the event ABOUT?");
        } else if (curr.length == 1) {
            throw new EventException("Where is the END DATE?");
        } else {
            LocalDateTime dateTime = Doge.getDateTime(curr[1].trim());
            this.task.setDescription(curr[0].trim());
            this.task.setDateTime(dateTime);
            tasks.addTask(this.task);
        }
    }

    @Override
    public String toString() {
        return "Stop troubling me, I've already added this task:";
    }
}
