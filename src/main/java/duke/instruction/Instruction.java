package duke.instruction;

import java.util.Arrays;
import java.util.List;

import duke.main.DukeException;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * A task represents an instruction inputted to main.Duke by a user.
 * At this stage, a task has minimally a name (description).
 */
public abstract class Instruction {

    private static final String TERMINATE_INSTRUCTION = "bye";

    private static final List<Class<? extends Instruction>> INSTRUCTION_TYPES =
            Arrays.asList(AddInstruction.class, DeleteInstruction.class,
                    FindInstruction.class, HelpInstruction.class,
                    ListTasksInstruction.class, MarkAsDoneInstruction.class,
                    QuitInstruction.class, UnmarkAsDoneInstruction.class);

    protected TaskManager tasks;
    private String description;

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

    /**
     * Constructor to be used by subclasses that does not operate on
     * tasks.
     *
     * @param description The description of the instruction.
     */
    protected Instruction(String description) {
        this.description = description;
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
            return new ListTasksInstruction(tasks);
        case Instruction.TERMINATE_INSTRUCTION:
            return new QuitInstruction(Instruction.TERMINATE_INSTRUCTION, tasks);
        case "mark":
            // Mark the task as done. If the second parameter is not an integer, or if the task does not exit, throw
            // an exception. (To be implemented later)
            return new MarkAsDoneInstruction(instruction, tasks);
        case "unmark":
            // Mark the task as not done. If the second parameter is not an integer, or if the task does not exit,
            // throw an exception. (To be implemented later)
            return new UnmarkAsDoneInstruction(instruction, tasks);
        case "todo":
        case "event":
        case "deadline":
            // These three cases are used to add tasks of different types.
            return new AddInstruction(Task.of(instruction), tasks);
        case "delete":
            return new DeleteInstruction(instruction, tasks);
        case "find":
            return new FindInstruction(instruction, tasks);
        case "help":
            return new HelpInstruction();
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
     * @throws DukeException If any exception occurs when dealing with the instruction.
     */
    public abstract void act(Ui ui) throws DukeException;

    /**
     * Returns whether current instruction is a terminating instruction.
     *
     * @return True if current instruction is a terminating instruction.
     */
    public boolean isTerminatingInstruction() {
        return this instanceof QuitInstruction;
    }

    /**
     * Performs the instruction and returns the output message to be printed to the GUI.
     * This method is written for GUI only. If the input is supplied to GUI,the output
     * will not be printed to the output stream.
     *
     * @return The output message to the GUI.
     * @throws DukeException If unable to get the output message.
     */
    public abstract String getOutputMessage() throws DukeException;

    /**
     * Returns a list of classes that represent each type of instructions.
     *
     * @return The list.
     */
    protected static List<Class<? extends Instruction>> getInstructionTypes() {
        return INSTRUCTION_TYPES;
    }
}
