package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private int index;

    /**
     * Creates a Delete Command
     * @param x Index of task to delete
     */
    public DeleteCommand(int x) {
        index = x;
    }

    /**
     * Deletes task with specified index from the list
     * @param ui Ui to communicate with user
     * @param tasks DukeList that stores Tasks
     */
    @Override
    public String execute(Ui ui, DukeList tasks) {
        try {
            String msg = ui.deleteTask(tasks.getTask(index), tasks.getSize());
            tasks.delete(index);
            return msg;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return "\nDuke: Wrong index to delete! Use \"list\" to see the current tasks.\n";
        }
    }

    /**
     * Returns false so program does not quit
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
