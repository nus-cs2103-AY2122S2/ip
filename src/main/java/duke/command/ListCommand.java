package duke.command;

import java.io.IOException;
import java.util.List;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Represents command to list all the tasks in the task list.
 *
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        List<Task> t = tasks.getObjectives();
        StringBuilder reply = new StringBuilder();
        reply.append(this.speak(Dialogue.LIST));
        for (int i = 0; i < t.size(); i++) {
            reply.append(i + 1);
            reply.append(".");
            reply.append(t.get(i));
            reply.append("\n");
        }
        return reply.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}





















