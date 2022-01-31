package duke.commands;

import duke.exception.ChiException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.io.IOException;

public class MarkCommand extends Command {

    private String[] tokens;

    public MarkCommand(String[] tokens) {
        super();
        this.tokens = tokens;
    }

    @Override
    public String execute(TaskList tl, Storage sge) throws ChiException {
        try {
            String msg = String.join(" ", tokens);
            boolean isValidMarkCommand = validateMessageBody(msg, tl);
            if (!isValidMarkCommand) {
                throw new ChiException("Hey there is something wrong with this mark command nyan!");
            } else {
                int index = getDescription(msg);
                Task doneTask = tl.getTask(index);
                // Mark as done
                doneTask.markAsDone();
                sge.updateFile(doneTask, tl, "mark");
                return String.format("Great job nyan~!\n%s\n", doneTask);

            }
        } catch (IOException e) {
            throw new ChiException("Hey something went wrong with the IO nyan!");
        }
    }

    public boolean validateMessageBody(String msg, TaskList tl)  {
        try {
            String[] words = msg.split(" ");
            if (words.length > 1) {
                return false;
            }
            int s = Integer.parseInt(words[0]);
            return s >= 0 && s <= tl.getSize();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getDescription(String msg) {
        return Integer.parseInt(msg) - 1;
    }
}
