package instructions._new.task.instructions;

import Exceptions.InvalidInputException;
import tasks.DeadlineTask;
import tasks.TaskList;

/**
 * This class represents an instruction for a new Deadline task.
 * Format: "deadline P /by Q",
 *         where P is the task description,
 *         and Q is the deadline.
 *
 * @author Ong Han Yang
 */
public class DeadlineInst extends NewTaskInst {
    /** The deadline, stored as a String. */
    private String deadline;

    /**
     * Constructs a Deadline Instruction for a Deadline Task.
     *
     * @param taskDesc the task description
     * @param deadline the deadline for the task
     */
    private DeadlineInst(String taskDesc, String deadline) {
        super(taskDesc);
        this.deadline = deadline;
    }

    /**
     * Produces a Deadline Instruction.
     *
     * @param taskDetails the details of the instruction. This includes both
     *                    the description and the deadline.
     * @return the Deadline Instruction
     * @throws InvalidInputException when no details are provided, the wrong
     *                               number of details provided, or when either
     *                               the deadline or description is omitted.
     */
    public static DeadlineInst of(String taskDetails)
            throws InvalidInputException {
        String[] split = taskDetails.split(" /by ");
        // a correct format will produce a String[2].

        if (split.length == 1) {
            //happens in "deadline /by ", deadline  /by b", "deadline a /byb" etc
            split = taskDetails.split("/by");
            if (split.length == 2 && split[0].length() != 0) {
                //happens in "deadline a/at b", "deadline a /atb" etc
                throw NewTaskInst.MISSING_SPACES_EXCEPTION;
            }
            throw NewTaskInst.MISSING_TASK_DETAILS_EXCEPTION;
        }
        if (split.length >= 3) { // happens with multiple " /by "s
            throw NewTaskInst.TOO_MANY_ARGUMENTS_EXCEPTION;
        }

        return new DeadlineInst(split[0], split[1]);
    }

    /**
     * Gets the deadline for this task.
     *
     * @return the deadline for this task.
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Adds a deadline task to the given taskList.
     *
     * @param taskList the taskList to have a deadline task added to.
     * @return the feedback message after performing this instruction.
     */
    @Override
    public String doInst(TaskList taskList) {
        DeadlineTask task = DeadlineTask.of(super.getTaskDesc(), this.deadline);
        taskList.add(task);
        return String.format("Okay, added this task:\n%s\nThere are %d tasks " +
                "in the list now."
                , task, taskList.length());
    }
}
