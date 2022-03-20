package duke.commands;

import java.util.ArrayList;
import duke.tasks.Task;

/**
 * A class that handles the unmark command, where we unmark the "isDone" status of the task.
 */
public class Unmark extends Commands {

    public Unmark(ArrayList<Task> tasks) {
        super(tasks);
    }

    /**
     * Handles unmarked tasks command.
     * @param currTask
     * @return
     */
    public String unmarkTask(Task currTask) {

        String res = "";

        boolean currState = currTask.getIsDone();
        currTask.setDone(!currState);

        res += super.LINE_BREAK + "\n";
        res += " " + super.UNMARK_MESSAGE;
        res += " [ ] " + currTask.getDescription();

        return res;
    }
}
