package duke.command;

import java.util.Objects;

import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The type Fond command.
 */
public class FindCommand extends Command {
    private final String keyWord;

    /**
     * Instantiates a new Find command.
     *
     * @param keyWord the word to search for
     */
    public FindCommand(String keyWord) {
        assert !keyWord.isEmpty();
        this.keyWord = keyWord;
    }

    /**
     * Find all the tasks that match with the given keyword.
     * Only the description of a task is being searched.
     *
     * @param taskList the task list to operate on
     * @param ui the ui to operate on
     * @param storage the storage to operate on
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        int numberOfTasksMatchKeyword = (int) taskList.getTaskList()
                .stream()
                .filter(task -> task.getDescription().contains(keyWord))
                .count();

        StringBuilder tasksWithSameKeyword = new StringBuilder();
        taskList.getTaskList()
                .stream()
                .filter(task -> task.getDescription().contains(keyWord))
                .forEach(task -> tasksWithSameKeyword.append(task).append("\n"));

        if (numberOfTasksMatchKeyword == 0) {
            throw new CortanaException("No task found with keyword " + keyWord + "!");
        } else {
            tasksWithSameKeyword.insert(0, ui.foundTasksMatchKeyword(numberOfTasksMatchKeyword, keyWord));
            return tasksWithSameKeyword.toString();
        }
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
