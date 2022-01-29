package duke.command;

import duke.util.Save;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This MarkCommand class will mark a task as done when executed.
 */
public class MarkCommand extends Command {
    private int taskNum;

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
     * @param ui    Ui provided.
     * @param save  Saved history.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Save save) {
        try {
            tasks.getTask(taskNum).mark();
            System.out.println("\t____________________________________________________________");
            System.out.println("\t Nice! I've marked this task as done:");
            System.out.println("\t\t" + tasks.getTask(taskNum).track()
                    + tasks.getTask(taskNum).getStatus() + " " + tasks.getTask(taskNum));
            System.out.println("\t____________________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t____________________________________________________________");
            System.out.println("\t☹ Woof Woof!!! This task cannot be found with my Wonka eyes!!!");
            System.out.println("\t____________________________________________________________");
        }
    }
}
