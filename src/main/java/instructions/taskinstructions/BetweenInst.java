package instructions.taskinstructions;

import java.time.LocalDateTime;

import date.time.DateTimeParser;
import exceptions.InvalidInputException;
import exceptions.InvalidTimeException;
import tasks.BetweenTask;
import tasks.TaskList;


/**
 * This class represents an instruction for a new Between Task.
 * Format: {@code do P /between Q /and R},
 *         where P is the task description,
 *         Q is the start time, in yyyy-mm-dd hh:mm,
 *         and R is the end time, in yyyy-mm-dd hh:mm.
 *
 * @author Ong Han Yang
 */
public class BetweenInst extends NewTaskInst {
    /** The start time. */
    private LocalDateTime startTime;

    /** The end time. */
    private LocalDateTime endTime;

    /**
     * Constructs a Between Task Instruction.
     *
     * @param taskDesc description for the task.
     * @param startTime the starting time of the task.
     * @param endTime the ending time of the task.
     */
    private BetweenInst(String taskDesc, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskDesc);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Produces a BetweenTask Instruction.
     *
     * @param taskDetails the details of the instruction. This includes both the description and the
     *                    timing, but cannot start/end with a space.
     * @return the BetweenTask Instruction.
     * @throws InvalidInputException when no details are provided, the wrong
     *                               number of details provided, when either
     *                               the timing or description is omitted, or
     *                               when the given time/date format is wrong.
     */
    public static BetweenInst of(String taskDetails) throws InvalidInputException {
        checkBetweenSeparator(taskDetails);
        String[] split = taskDetails.split(" /between ");
        assert split.length == 2;

        checkAndSeparator(split[1]);
        String[] timings = split[1].split(" /and ");
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
            throw new InvalidTimeException("The task cannot have no duration!");
        } else if (isEndTimeFirst) {
            // happens when end time is earlier.
            throw new InvalidTimeException("Start Time needs to be before the End Time!");
        }
        return new BetweenInst(split[0], startTime, endTime);

    }

    /**
     * Adds a BetweenTask to the given taskList.
     *
     * @param taskList the taskList to have an BetweenTask added to.
     * @return the feedback message after performing this instruction.
     */
    @Override
    public String doInst(TaskList taskList) {
        BetweenTask task = new BetweenTask(super.getTaskDesc(), this.startTime, this.endTime);
        taskList.add(true, task);
        return String.format("Okay, added this task:\n%s\nThere are %d tasks in the list now.",
                task, taskList.length());
    }

    /**
     * Checks for proper task details and the existence of the " /between " separator in the given
     * task details. If it is missing, finds out why and returns the reason in the InvalidInputException.
     *
     * @param taskDetails the input task details to be checked.
     * @return true when the input has proper details separated with " /between ".
     * @throws InvalidInputException when the input does not have proper details separated with
     *          " /between ", or is missing spaces or the " /between " itself.
     */
    private static boolean checkBetweenSeparator(String taskDetails) throws InvalidInputException {
        String[] split = taskDetails.split(" /between ");
        // a correct format will produce a String[2].

        // check for the " /between " and figure out where the problem is if it is missing
        if (split.length == 1) {
            // happens in "event a/betweenb", "a /betweenb", "a/betweenb"
            split = taskDetails.split("/between");
            if (split.length == 2) {
                // happens in "event a/between b", "a /betweenb", "a/betweenb"
                // user included a "/between" but did not add appropriate spacing.
                throw MISSING_SPACES_EXCEPTION;
            }
            // user did not include a "/between" at all.
            throw MISSING_TASK_DETAILS_EXCEPTION;
        }
        if (split.length >= 3) { // happens with multiple " /between "s
            throw TOO_MANY_ARGUMENTS_EXCEPTION;
        }
        return true;
    }

    /**
     * Checks for proper task details and the existence of the " /and " separator in the given task details.
     * If it is missing, finds out why and returns the reason in the InvalidInputException.
     *
     * @param taskDetails the input task details to be checked.
     * @return true when the input has proper details separated with " /and ".
     * @throws InvalidInputException when the input does not have proper details separated with " /and ",
     *          or is missing spaces or the " /and " itself.
     */
    private static boolean checkAndSeparator(String taskDetails) throws InvalidInputException {
        String[] timings = taskDetails.split(" /and ");
        // a correct format will produce a String[2] with timings[0] not being full of spaces.

        // check for the " /and " and figure out where the problem is if it is missing
        if (timings.length == 1) {
            //happens in "/and c", "b/andc", "b/and c", "b /andc"
            String[] split = taskDetails.split("/and");
            if (split.length == 2 && split[0].length() != 0) {
                // happens in "b/and c", "b /andc", "b/andc"
                throw MISSING_SPACES_EXCEPTION;
            }
            // happens in "/and c" or the user did not include a "/and" at all.
            throw MISSING_TASK_DETAILS_EXCEPTION;
        }
        // will be invalid if the provided start timing is empty or full of spaces. ("" or "  ")
        // ie when user inputs "event a /between  /and c" or "event a /between    /and c"
        boolean isStartTimingEmpty = timings[0].strip().length() == 0;
        if (timings.length == 2 && isStartTimingEmpty) {
            throw MISSING_TASK_DETAILS_EXCEPTION;
        }
        if (timings.length >= 3) {
            throw TOO_MANY_ARGUMENTS_EXCEPTION;
        }
        return true;
    }
}
