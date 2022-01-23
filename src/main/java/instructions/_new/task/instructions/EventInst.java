package instructions._new.task.instructions;

import Exceptions.InvalidInputException;
import tasks.EventTask;
import tasks.TaskList;

/**
 * This class represents an instruction for a new Event task.
 * Format: "event P /at Q",
 *         where P is the task description,
 *         and Q is the date and time of the event.
 *
 * @author Ong Han Yang
 */
public class EventInst extends NewTaskInst {
    /** The timing, stored as a String. */
    private String timing;

    /**
     * Constructs an Event Instruction for an Event Task.
     *
     * @param taskDesc the task description
     * @param timing   the deadline for the task
     */
    private EventInst(String taskDesc, String timing) {
        super(taskDesc);
        this.timing = timing;
    }

    /**
     * Produces an Event Instruction.
     *
     * @param taskDetails the details of the instruction. This includes both
     *                    the description and the timing.
     * @return the Event Instruction
     * @throws InvalidInputException when no details are provided, the wrong
     *                               number of details provided, or when either
     *                               the timing or description is omitted.
     */
    public static EventInst of(String taskDetails)
            throws InvalidInputException {
        String[] split = taskDetails.split(" /at ");
        // a correct format will produce a String[2].

        if (split.length == 1) {
            //happens in "event /at ", event /at b", "event a /atb" etc
            split = taskDetails.split("/by");
            if (split.length == 2 && split[0].length() != 0) {
                //happens in "event a/at b", "event a /atb", "event a/atb"
                throw NewTaskInst.MISSING_SPACES_EXCEPTION;
            }
            throw NewTaskInst.MISSING_TASK_DETAILS_EXCEPTION;
        }
        if (split.length >= 3) { // happens with multiple " /by "s
            throw NewTaskInst.TOO_MANY_ARGUMENTS_EXCEPTION;
        }

        return new EventInst(split[0], split[1]);
    }

    /**
     * Gets the deadline for this task.
     *
     * @return the deadline for this task.
     */
    public String getTiming() {
        return this.timing;
    }

    /**
     * Adds an evennt task to the given taskList.
     *
     * @param taskList the taskList to have an event task added to.
     * @return the feedback message after performing this instruction.
     */
    @Override
    public String doInst(TaskList taskList) {
        EventTask task = new EventTask(super.getTaskDesc(), this.timing);
        taskList.add(task);
        return String.format("Okay, added this task:\n%s\nThere are %d tasks " +
                "in the list now."
                , task, taskList.length());
    }
}