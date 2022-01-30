package main.java.duke.instruction;

import main.java.duke.main.DukeException;
import main.java.duke.task.Task;
import main.java.duke.task.TaskManager;
import main.java.duke.ui.Ui;

/**
 * A task represents an instruction inputted to main.Duke by a user.
 * At this stage, a task has minimally a name (description).
 */
public abstract class Instruction {

    private String description;
    private static final String TERMINATE_INSTRUCTION = "bye";
    protected TaskManager tasks;

    /**
     * Constructor to be used by subclasses.
     *
     * @param description The description of the instruction.
     * @param tasks The task manager to be used with the instruction.
     */
    protected Instruction(String description, TaskManager tasks) {
        this.description = description;
        this.tasks = tasks;
    }

    protected static String getTerminateInstruction() {
        return TERMINATE_INSTRUCTION;
    }

    /**
     * Factory method. Create an instance of subclass of instructions according to the string inputted, and returns it.
     * The instruction must have at least one word.
     *
     * @param instruction The line of command.
     * @return A corresponding instance of instruction.
     * @throws DukeException If the instruction is invalid.
     */
    public static Instruction of(String instruction, TaskManager tasks)
            throws DukeException {

        // Extract the words in the instruction. The first word should determine the type of instruction to be returned.
        String[] words = instruction.split(" ", 2);

        if (words.length == 0) {
            throw new InvalidInstructionException("Oops, I don't know what empty instruction means.");
        }

        String type = words[0];

        switch (type) {
        case "list":
            return new ListTasks(tasks);
        case Instruction.TERMINATE_INSTRUCTION:
            return new Quit(Instruction.TERMINATE_INSTRUCTION, tasks);
        case "mark":
            // Mark the task as done. If the second parameter is not an integer, or if the task does not exit, throw
            // an exception. (To be implemented later)
            return new MarkAsDone(instruction, tasks);
        case "unmark":
            // Mark the task as not done. If the second parameter is not an integer, or if the task does not exit,
            // throw an exception. (To be implemented later)
            return new UnmarkAsDone(instruction, tasks);
        case "todo":
        case "event":
        case "deadline":
            // These three cases are used to add tasks of different types.
            return new Add(Task.of(instruction), tasks);
        case "delete":
            return new Delete(instruction, tasks);
        default:
            throw new InvalidInstructionException("Oops, I'm not sure what you mean.");
        }
    }

    /**
     * Sets the description of the instruction.
     *
     * @param description The description of the instruction.
     */
    protected void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the description of the instruction.
     */
    protected String getDescription() {
        return this.description;
    }

    /**
     * Performs the associated action of the task. By default, there is no action associated to a task.
     *
     * @param ui The UI to be used by this instruction.
     * @return The message once the instruction is executed.
     */
    public abstract void act(Ui ui);

    /**
     * Returns whether current instruction is a terminating instruction.
     *
     * @return True if current instruction is a terminating instruction.
     */
    public boolean isTerminatingInstruction() {
        return this instanceof Quit;
    }
}
