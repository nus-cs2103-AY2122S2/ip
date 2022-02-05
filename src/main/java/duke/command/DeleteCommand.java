package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;


/**
 * Represents command to delete a task from the task list.
 *
 */
public class DeleteCommand extends Command {
    protected Integer index;

    public DeleteCommand(Integer index) {
        this.index = index - 1;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        String deletedTask = (tasks.getTask(this.index)).toString();
        tasks.delete(this.index);
        storage.overWriteFile(tasks);

        StringBuilder reply = new StringBuilder();
        reply.append(this.speak(Dialogue.DELETE));
        reply.append("\n");
        reply.append(deletedTask);
        reply.append("\n");
        reply.append(this.speak(Dialogue.NUMLEFT, tasks.getSize()));
        return reply.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
