package stevie.undo;

import java.util.LinkedList;

import stevie.exception.TaskException;
import stevie.exception.messages.TaskExceptionMessages;
import stevie.undo.actions.Undo;

/**
 * UndoHistory stores the commands to undo prior commands that made changes to the task list.
 * UndoHistory stores undo commands in a stack, allowing changes to be undone in a LIFO sequence.
 */
public class UndoHistory {
    private final LinkedList<Undo> undos = new LinkedList<>();

    /**
     * Pushes undo command into a stack.
     * @param undo command to undo the prior change to task list
     */
    public void addUndo(Undo undo) {
        undos.push(undo);
    }

    /**
     * Retrieves the latest undo command that can be used to reverse the latest change.
     * @return undo command to reverse the latest change
     * @throws TaskException if stack of undos is empty
     */
    public Undo getUndo() throws TaskException {
        if (undos.isEmpty()) {
            throw new TaskException(TaskExceptionMessages.EmptyUndoListError);
        }
        return undos.pop();
    }
}
