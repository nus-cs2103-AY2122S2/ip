package Command;

import Task.TaskList;

import Main.Ui;
import Main.Storage;

/**
 * Mark task from tasklist and uses UI to print it
 */
public class MarkCommand extends Command {
    enum Type {
        MARK,
        UNMARK,
    }

    Type ty;
    int num;

    /**
     * Create a new MarkCommand
     *
     * @param s
     * @param num
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
     */
    public void execute(TaskList t, Ui u, Storage s) {
        String say = "";
        switch (ty) {
            case MARK:
                t.markTask(this.num);
                say = "Alright! Aright, i will mark it down!\n";
                break;
            case UNMARK:
                t.unmarkTask(this.num);
                say = "You didn't actually finish?!";
                break;
        }
        u.printWrapper(say + t.getTaskStr(this.num));
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
