package command;

import task.TaskList;
import tsundere.Storage;
import tsundere.TsundereException;
import tsundere.Ui;

/**
 * Mark task from tasklist and uses UI to print it
 */
public class MarkCommand extends Command {
    enum Type {
        MARK,
        UNMARK,
    }

    protected Type ty;
    protected int num;

    /**
     * Create a new MarkCommand
     *
     * @param s String input by user
     * @param num the index of the task in tasklist to be mark
     */
    public MarkCommand(String s, int num) {
        if (s.equals("MARK")) {
            this.ty = Type.MARK;
        } else {
            this.ty = Type.UNMARK;
        }

        this.num = num;
    }

    /**
     * Execute the mark/unmark command task in the tasklist and store it using the Storage
     *
     * @param t TaskList for managing and adding tasks
     * @param u UI for displaying text
     * @param s Storage for saving to file
     * @throws TsundereException exception for invalid number
     */
    public void execute(TaskList t, Ui u, Storage s) throws TsundereException {
        String say = "";
        if (t.getCount() < this.num || this.num <= 0) {
            throw new TsundereException("Your number is not valid!!!");
        }
        switch (ty) {
        case MARK:
            t.markTask(this.num);
            say = "Alright! Aright, i will mark it down!\n";
            break;
        case UNMARK:
            t.unmarkTask(this.num);
            say = "You didn't actually finish?!";
            break;
        default:
        }
        u.wrapText(say + t.getTaskStr(this.num));
        s.saveFile(t.tasksToString());
    }

    /**
     * Determine if the class is ExitCommand.
     *
     * @return False always because it is not an ExitCommand
     */
    public boolean isExit() {
        return false;
    }
}
