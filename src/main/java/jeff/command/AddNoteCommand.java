package jeff.command;

import jeff.main.JeffException;
import jeff.note.Note;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

public class AddNoteCommand extends Command {

    private String body;

    public AddNoteCommand(String body) {
        this.body = body;
    }
    @Override
    public String execute(TaskList tasks, Note notes, Ui ui, Storage storage) throws JeffException {
        notes.add(body);
        String response = ui.showNoteAdded(body);
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
