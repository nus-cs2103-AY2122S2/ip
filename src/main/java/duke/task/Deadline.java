package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A concrete task which contains a name
 * and a deadline by which the task should
 * be completed.
 */
public class Deadline extends Task {
    static final SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    static final SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("dd MMM");

    /** Deadline for current task */
    private final Date deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = parseDeadline(deadline);
    }

    /**
     * Parse given date string to Date object, where
     * string must be in the format of DD/MM/YYYY.
     *
     * @param dateString Date string to parse.
     * @return Date object corresponding to dateString
     * @throws IllegalArgumentException If the date string is not of DD/MM/YYYY format.
     */
    private static Date parseDeadline(String dateString) throws IllegalArgumentException {
        try {
            Date date = INPUT_DATE_FORMAT.parse(dateString);
            return date;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: Dates should be in DD/MM/YYYY");
        }
    }

    @Override
    public String getDescription() {
        String formattedDeadline = OUTPUT_DATE_FORMAT.format(this.deadline);
        return String.format("[D]%s %s (by: %s)",
                super.getDoneStatusCheckbox(), super.getName(), formattedDeadline);
    }

    @Override
    public String encodeTaskData() {
        String doneString = this.isDone() ? "Y" : "N";
        String inputFormattedDeadline = INPUT_DATE_FORMAT.format(this.deadline);
        return String.format("D,%s,%s,%s", super.getName(), doneString, inputFormattedDeadline);
    }
}
