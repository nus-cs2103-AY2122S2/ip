package duke.instruction;

import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents the 'add' instruction to main.Duke.
 */
public final class AddInstruction extends Instruction {

    private static final String HELP_MESSAGE = "add: adds a task (todo/deadline/event) "
            + "to the task list.\n"
            + "usage: 1. todo <task_name>"
            + "       2. deadline <task_name> /by <YYYY-MM-DD>"
            + "       3. event <task_name> /at <time_in_any_format>";

    private final Task task;

    /**
     * Constructor for an add instruction.
     *
     * @param task The task to be added.
     * @param tasks The task manager to be used.
     */
    protected AddInstruction(Task task, TaskManager tasks) throws IllegalArgumentException {

        super("add", tasks);
        this.task = task;
    }

    /**
     * Adds the task into the task manager, and returns the message when the action is done.
     *
     * @param ui The UI to be used.
     */
    @Override
    public void act(Ui ui) {
        ui.printMessage(tasks.addTask(this.task));
    }

    /**
     * Performs the instruction and returns the output message to be printed to the GUI.
     * This method is written for GUI only. If the input is supplied to GUI,the output
     * will not be printed to the output stream.
     *
     * @return The output message to the GUI.
     */
    public String getOutputMessage() {
        return tasks.addTask(task);
    }


    /**
     * Get the help message for the current instruction.
     *
     * @return The help message.
     */
    protected static String getHelpMessage() {
        return HELP_MESSAGE;
    }
}
