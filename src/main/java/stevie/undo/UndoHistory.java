package stevie.undo;

import java.util.LinkedList;

import stevie.exception.TaskException;
import stevie.exception.messages.TaskExceptionMessages;
import stevie.undo.actions.Undo;

public class UndoHistory {
    private final LinkedList<Undo> undos = new LinkedList<>();

    public void addUndo(Undo undo) {
        undos.push(undo);
    }

    public Undo getUndo() throws TaskException {
        if (undos.isEmpty()) {
            throw new TaskException(TaskExceptionMessages.EmptyUndoListError);
        }
        return undos.pop();
    }
}
