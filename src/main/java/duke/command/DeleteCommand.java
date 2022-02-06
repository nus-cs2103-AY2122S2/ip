package duke.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * An instance of DeleteCommand.
 */
public class DeleteCommand extends Command {

    private final String[] entryNumbers;

    /**
     * Instantiates a new Delete command.
     *
     * @param entryNumbers the entry numbers that the user wants to remove/delete from the list
     */
    public DeleteCommand(String entryNumbers) {
        this.entryNumbers = entryNumbers.split(" ");
    }

    /**
     * Deletes/removes the specified task and prints a message upon its completion.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     * @return string to indicate that the specified task has been removed from the list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return "Your list is empty!";
        } else if (entryNumbers.length < 1) {
            return "Please enter the entry number(s) that you wish to remove!";
        }

        List<Integer> deletionNumbers = new ArrayList<>();
        Arrays.stream(entryNumbers)
                .forEach(entry -> {
                    int i = -1;
                    try {
                        i = Parser.parseInt(entry);
                    } catch (DukeException e) {
                        ui.showError(e.getMessage());
                    }
                    if (i >= 0) {
                        deletionNumbers.add(i - 1);
                    }
                });
        List<Task> deletedTasks = new ArrayList<>();
        deletionNumbers.forEach(n -> deletedTasks.add(tasks.get(n)));

        deletionNumbers.sort(Comparator.reverseOrder());
        deletionNumbers.forEach(tasks::removeTask);

        return ui.showDeletion(tasks.size(), deletedTasks);
    }
}
