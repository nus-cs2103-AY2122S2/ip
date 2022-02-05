package command;

import task.TaskList;
import tsundere.Storage;
import tsundere.TsundereException;

/**
 * Marks task from tasklist and uses UI to print it.
 */
public class MarkCommand extends Command {
    enum Type {
        MARK,
        UNMARK,
    }

    protected Type type;
    protected int num;

    /**
     * Creates a new MarkCommand.
     *
     * @param s String input by user.
     * @param num the index of the task in tasklist to be mark.
     */
    public MarkCommand(String s, int num) {
        if (s.equals("MARK")) {
            this.type = Type.MARK;
        } else {
            this.type = Type.UNMARK;
        }

        this.num = num;
    }

    /**
     * Executes the mark/unmark command task in the tasklist and store it using the storage.
     *
     * @param t TaskList for managing and adding tasks.
     * @param s Storage for saving to file.
     * @throws TsundereException exception for invalid number.
     */
    public String execute(TaskList t, Storage s) throws TsundereException {
        String say = "";
        if (t.getCount() < this.num || this.num <= 0) {
            throw new TsundereException("Your number is not valid!!!");
        }
        switch (type) {
        case MARK:
            t.markTask(this.num);
            say = "Alright! Aright, i will mark it down!\n";
            break;
        case UNMARK:
            t.unmarkTask(this.num);
            say = "You didn't actually finish?!\n";
            break;
        default:
        }
        s.saveFile(t.tasksToString());
        return (say + t.getTaskStr(this.num));
    }

    /**
     * Determines if the class is ExitCommand.
     *
     * @return false always because it is not an ExitCommand.
     */
    public boolean isExit() {
        return false;
    }
}
