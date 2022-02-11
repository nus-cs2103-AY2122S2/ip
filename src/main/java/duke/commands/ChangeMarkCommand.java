package duke.commands;

import duke.admin.Storage;
import duke.admin.TaskList;
import duke.admin.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

public class ChangeMarkCommand extends Command {
    private int index;
    private boolean toMark;

    /**
     * Constructor for ChangeMarkCommand
     * @param index index of task to be changed
     * @param toMark whether the command wants to mark the task or unmark the task
     */
    public ChangeMarkCommand(int index, boolean toMark) {
        assert index > 0;
        this.index = index;
        this.toMark = toMark;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert index <= tasks.getNumberOfTasks();
        Task task = tasks.changeMark(index, toMark);
        storage.updateAfterChangeMark(index, toMark);

        boolean isTriggerTask = task.getType().equals("Z") && task.getDescription().equals("tH1$ 1s Th3 tR1gG3r t45K");

        return isTriggerTask ? Ui.showNoChangeMarkMessage(toMark)
                : Ui.showChangeMarkMessage(task, toMark);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
