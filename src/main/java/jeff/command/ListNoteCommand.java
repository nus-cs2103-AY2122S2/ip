package jeff.command;

import jeff.main.JeffException;
import jeff.note.Note;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

public class ListNoteCommand extends Command {
    @Override
    public String execute(TaskList tasks, Note notes, Ui ui, Storage storage) throws JeffException {
        String response;
        if (notes.isEmpty()) {
            response = ui.showEmptyNote();
        } else {
            response = ui.showNote(notes.toString());
        }
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
