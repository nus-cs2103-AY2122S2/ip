package duke.command;

import duke.util.Save;
import duke.util.TaskList;

/**
 * This MarkCommand class will mark a task as done when executed.
 */
public class MarkCommand extends Command {
    private final int taskNum;

    /**
     * Constructor for MarkCommand with a given task number to be marked as done in the list.
     *
     * @param taskNum 0-based index task number to be marked.
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes command by marking task as done.
     *
     * @param tasks TaskList of tasks.
     * @param save  Saved history.
     */
    @Override
    public String execute(TaskList tasks, Save save) {
        String response = "";
        try {
            tasks.getTask(taskNum).mark();
            response += "Nice! I've marked this task as done:\n";
            String markedString = tasks.getTask(taskNum).track() + tasks.getTask(taskNum).getStatus()
                    + " " + tasks.getTask(taskNum);
            response += markedString;
        } catch (IndexOutOfBoundsException e) {
            response = "☹ Woof Woof!!! This task cannot be found with my Wonka eyes!!!";
        }
        return response;
    }
}
