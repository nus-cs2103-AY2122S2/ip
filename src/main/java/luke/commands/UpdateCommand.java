package luke.commands;

/**
 * Framework for commands that updates the task list.
 */
public abstract class UpdateCommand extends Command {

    private final int index;

    /**
     * Constructs the update command with the specified index.
     * @param index The specified index of the task to be updated in the task list.
     */
    UpdateCommand(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
