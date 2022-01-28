package stevie.command;

import stevie.exception.StevieException;
import stevie.StevieUi;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

public class DeleteCommand extends Command {
    private final int taskIdx;

    public DeleteCommand(int idx) {
        taskIdx = idx;
    }

    @Override
    public boolean execute(TaskList tasks, TaskDataHandler storage, StevieUi ui) {
        String out;
        try {
            out = tasks.delete(taskIdx);
        } catch (StevieException ex) {
            ui.outputMessage(ex.getMessage());
            return false;
        }
        tasks.save(storage);
        ui.outputMessage(out);
        return false;
    }
}
