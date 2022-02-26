package butler.task;

import butler.ButlerInputException;
import butler.util.LocalDateTimeManager;

public class Deadline extends Task {

    /** String representation of date and time not following any format */
    private String dateAndTime;
    /** String representation of formatted LocalDateTime objects */
    private String localDateTime;

    /**
     * Constructs a Deadline object that must be completed by a certain date and time.
     * If a date and time provided does not follow the dateFormatter provided, treats it as a string.
     *
     * @param dateAndTime string representation of date and time provided by user.
     * @param description description of the task.
     * @throws ButlerInputException Throws ButlerInputException when dateAndTime inputs are not formatted correctly.
     */
    public Deadline(String description, String dateAndTime) throws ButlerInputException {
        super(description);
        this.dateAndTime = dateAndTime;
        localDateTime = LocalDateTimeManager.toLocalDateTime(dateAndTime);
    }

    /**
     * Returns symbolic representation of task type.
     *
     * @return String Representation of task type.
     */
    public String getTaskType() {
        return "[D]";
    }

    /**
     * Returns symbolic representation of dateTime.
     *
     * @return String Representation of dateTime.
     */
    public String getDateAndTime() {
        if (!localDateTime.equals("")) {
            return localDateTime;
        }
        return dateAndTime;
    }

    /**
     * Represents Deadline Object as a String
     *
     * @return String Representation of task as a whole.
     */
    @Override
    public String toString() {
        String superString = super.toString();
        String noNewLineSuper = superString.substring(0, superString.length() - 1);
        if (!localDateTime.equals("")) {
            return this.getTaskType() + noNewLineSuper + " (by: " + localDateTime + ")\n";
        }
        return this.getTaskType() + noNewLineSuper + " (by: " + dateAndTime + ")\n";
    }
}
