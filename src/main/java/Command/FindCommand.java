package Command;

import DukeUtils.CortanaException;
import DukeUtils.Storage;
import DukeUtils.TaskList;
import DukeUtils.Ui;
import Task.Task;

import java.util.Objects;

public class FindCommand extends Command{
    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        int numberOfTasksMatchKeyword = 0;
        for (Task task: taskList.tasksArrayList) {
            if (task.description.contains(keyWord)) {
                numberOfTasksMatchKeyword ++;
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
        if (obj != null  && obj.getClass() == getClass()) {
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
