package instructions.taskinstructions;

import java.time.LocalDateTime;

import date.time.DateTimeParser;
import exceptions.InvalidInputException;
import exceptions.InvalidTimeException;
import tasks.EventTask;
import tasks.TaskList;

/**
 * This class represents an instruction for a new Event task.
 * Format: "event P /at Q /until R",
 *         where P is the task description,
 *         Q is the start time, in yyyy-mm-dd hh:mm,
 *         and R is the end time, in yyyy-mm-dd hh:mm.
 *
 * @author Ong Han Yang
 */
public class EventInst extends NewTaskInst {
    /** The start time. */
    private LocalDateTime startTime;

    /** The end time. */
    private LocalDateTime endTime;

    /**
     * Constructs an Event Task Instruction.
     *
     * @param taskDesc description for the task.
     * @param startTime the starting time of the event.
     * @param endTime the ending time of the event.
     */
    private EventInst(String taskDesc, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskDesc);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Produces an Event Instruction.
     *
     * @param taskDetails the details of the instruction. This includes both the description and the
     *                    timing, but cannot start/end with a space.
     * @return the Event Instruction.
     * @throws InvalidInputException when no details are provided, the wrong
     *                               number of details provided, when either
     *                               the timing or description is omitted, or
     *                               when the given time/date format is wrong.
     */
    public static EventInst of(String taskDetails) throws InvalidInputException {
        checkAtSeparator(taskDetails);
        String[] split = taskDetails.split(" /at ");
        assert split.length == 2;

        checkUntilSeparator(split[1]);
        String[] timings = split[1].split(" /until ");
        assert timings.length == 2;

        // if either of the timings are invalid, an exception will be thrown
        DateTimeParser.checkValidFormat(timings[0]);
        DateTimeParser.checkValidFormat(timings[1]);

        LocalDateTime startTime = DateTimeParser.parse(timings[0]);
        LocalDateTime endTime = DateTimeParser.parse(timings[1]);

        boolean isStartTimeFirst = startTime.isBefore(endTime);
        boolean isEndTimeFirst = startTime.isAfter(endTime);
        if (!isStartTimeFirst && !isEndTimeFirst) {
            // happens only when both are false, which only happens when startTime = endTime
            throw new InvalidTimeException("The event cannot have no duration!");
        } else if (isEndTimeFirst) {
            // happens when end time is earlier.
            throw new InvalidTimeException("Start Time needs to be before the End Time!");
        }
        return new EventInst(split[0], startTime, endTime);

    }

    /**
     * Adds an event task to the given taskList.
     *
     * @param taskList the taskList to have an event task added to.
     * @return the feedback message after performing this instruction.
     */
    @Override
    public String doInst(TaskList taskList) {
        EventTask task = new EventTask(super.getTaskDesc(), this.startTime, this.endTime);
        taskList.add(true, task);
        return String.format("Okay, added this task:\n%s\nThere are %d tasks in the list now.",
                task, taskList.length());
    }

    /**
     * Checks for proper task details and the existence of the " /at " separator in the given task details.
     * If it is missing, finds out why and returns the reason in the InvalidInputException.
     *
     * @param taskDetails the input task details to be checked.
     * @return true when the input has proper details separated with " /at ".
     * @throws InvalidInputException when the input does not have proper details separated with " /at ",
     *          or is missing spaces or the " /at " itself.
     */
    private static boolean checkAtSeparator(String taskDetails) throws InvalidInputException {
        String[] split = taskDetails.split(" /at ");
        // a correct format will produce a String[2].

        // check for the " /at " and figure out where the problem is if it is missing
        if (split.length == 1) {
            // happens in "event a/atb", "a /atb", "a/atb"
            split = taskDetails.split("/at");
            if (split.length == 2) {
                // happens in "event a/at b", "a /atb", "a/atb"
                // user included a "/at" but did not add appropriate spacing.
                throw MISSING_SPACES_EXCEPTION;
            }
            // user did not include a "/at" at all.
            throw MISSING_TASK_DETAILS_EXCEPTION;
        }
        if (split.length >= 3) { // happens with multiple " /at "s
            throw TOO_MANY_ARGUMENTS_EXCEPTION;
        }
        return true;
    }

    /**
     * Checks for proper task details and the existence of the " /until " separator in the given task details.
     * If it is missing, finds out why and returns the reason in the InvalidInputException.
     *
     * @param taskDetails the input task details to be checked.
     * @return true when the input has proper details separated with " /until ".
     * @throws InvalidInputException when the input does not have proper details separated with " /until ",
     *          or is missing spaces or the " /until " itself.
     */
    private static boolean checkUntilSeparator(String taskDetails) throws InvalidInputException {
        String[] timings = taskDetails.split(" /until ");
        // a correct format will produce a String[2] with timings[0] not being full of spaces.

        // check for the " /until " and figure out where the problem is if it is missing
        if (timings.length == 1) {
            //happens in "/until c", "b/untilc", "b/until c", "b /untilc"
            String[] split = taskDetails.split("/until");
            if (split.length == 2 && split[0].length() != 0) {
                // happens in "b/until c", "b /untilc", "b/untilc"
                throw MISSING_SPACES_EXCEPTION;
            }
            // happens in "/until c" or the user did not include a "/until" at all.
            throw MISSING_TASK_DETAILS_EXCEPTION;
        }
        // will be invalid if the provided start timing is empty or full of spaces. ("" or "  ")
        // ie when user inputs "event a /at  /until c" or "event a /at    /until c"
        boolean isStartTimingValid = timings[0].strip().length() != 0;
        if (timings.length == 2 && isStartTimingValid) {
            throw MISSING_TASK_DETAILS_EXCEPTION;
        }
        if (timings.length >= 3) {
            throw TOO_MANY_ARGUMENTS_EXCEPTION;
        }
        return true;
    }
}
