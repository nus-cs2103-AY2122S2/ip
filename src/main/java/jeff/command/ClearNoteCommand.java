package jeff.command;

import jeff.main.JeffException;
import jeff.note.Note;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

public class ClearNoteCommand extends Command {
    @Override
    public String execute(TaskList tasks, Note notes, Ui ui, Storage storage) throws JeffException {
        notes.clear();
        String response = ui.showNoteCleared();
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
