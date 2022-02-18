package jeff.command;

import jeff.note.Note;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

/**
 * HelpCommand class is a Command that contains instructions
 * to run when user inputs an invalid command.
 */
public class HelpCommand extends Command {

    /**
     * Helps the user find available commands.
     *
     * @param tasks TaskList containing all the tasks.
     * @param notes Contains all the notes.
     * @param ui Ui class for invoking user feedback.
     * @param storage Storage class used to save files.
     * @return points to the readme.txt to help the users.
     */
    @Override
    public String execute(TaskList tasks, Note notes, Ui ui, Storage storage) {
        String response = ui.showHelp();
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
