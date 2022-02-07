package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents command to add a task to the task list.
 *
 */
public class AddCommand extends Command {
    protected Task obj;

    public AddCommand(Task obj) {
        this.obj = obj;
    }

    @Override
    public String execute(TaskList task, Storage storage) throws IOException {
        task.add(obj);
        storage.appendFile(obj);

        StringBuilder reply = new StringBuilder();
        reply.append(this.speak(Dialogue.ADDED));
        reply.append("\n");
        reply.append(this.obj.toString());
        assert !reply.toString().equals("") : "Description of task cannot be empty";
        return reply.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
