package duke.command;

import java.util.Objects;

import duke.task.Task;
import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class FindCommand extends Command {
    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Execute find task with keyword operation.
     *
     * @param taskList the task list to operate on
     * @param ui the ui to operate on
     * @param storage the storage to operate on
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        int numberOfTasksMatchKeyword = 0;
        for (Task task: taskList.getTaskList()) {
            if (task.getDescription().contains(keyWord)) {
                numberOfTasksMatchKeyword++;
                ui.printTask(task);
            }
        }
        if (numberOfTasksMatchKeyword == 0) {
            throw new CortanaException("No task found with keyword " + keyWord + "!");
        } else {
            ui.foundTasksMatchKeyword(numberOfTasksMatchKeyword, keyWord);
        }
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            FindCommand findCommand = (FindCommand) obj;
            return Objects.equals(findCommand.keyWord, this.keyWord);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyWord);
    }
}
