package jeff.command;

import jeff.note.Note;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

/**
 * ListCommand class is a Command that contains instructions
 * to run when user wants to list out every task.
 */
public class ListCommand extends Command {

    /**
     * Passes the string to print to ui class to print out every entry of the list if any.
     *
     * @param tasks TaskList containing all the tasks.
     * @param notes Contains all the notes.
     * @param ui Ui class for invoking user feedback.
     * @param storage Storage class used to save files.
     * @return list of all tasks.
     */
    @Override
    public String execute(TaskList tasks, Note notes, Ui ui, Storage storage) {
        String response;
        if (tasks.isEmpty()) {
            response = ui.showEmptyList();
        } else {
            StringBuilder taskStrings = new StringBuilder();
            for (int n = 0; n < tasks.size(); n++) {
                int temp = n + 1;
                String currTaskString = temp + "." + tasks.getString(n) + "\n";
                taskStrings.append(Ui.addPrefix(currTaskString));
            }
            response = ui.showList(taskStrings.toString());
        }
        return response;
    }

    /**
     * Used to exit the Jeff program.
     *
     * @return false to keep running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
