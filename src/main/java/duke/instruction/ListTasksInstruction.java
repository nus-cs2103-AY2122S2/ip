package duke.instruction;

import java.util.List;

import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents the instruction "list".
 */
final class ListTasksInstruction extends Instruction {

    private static final String HELP_MESSAGE = "list: list all the active tasks.\n"
            + "usage: list";
    /**
     * Constructor of the instruction, adds the description.
     */
    ListTasksInstruction(TaskManager tasks) {
        super("list", tasks);
    }

    /**
     * Performs a search on the list of tasks, and return them as formatted strings.
     *
     * @param ui The UI to be used.
     */
    @Override
    public void act(Ui ui) {
        List<Task> tasks = this.tasks.listOfTasks();

        ui.printMessage(TaskManager.listToString(tasks));
    }

    /**
     * Performs the instruction and returns the output message to be printed to the GUI.
     * This method is written for GUI only. If the input is supplied to GUI,the output
     * will not be printed to the output stream.
     *
     * @return The output message to the GUI.
     */
    @Override
    public String getOutputMessage() {

        List<Task> tasks = this.tasks.listOfTasks();

        return TaskManager.listToString(tasks);
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
