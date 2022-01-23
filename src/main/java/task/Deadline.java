package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    final static SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    final static SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMM");
    /**
     * Deadline for current task
     */
    private final Date deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = parseDeadline(deadline);
    }

    /**
     * Parse given date string to Date object, where
     * string must be in the format of DD/MM/YYYY
     *
     * @param dateString date string to parse
     * @return Date object corresponding to dateString
     * @throws IllegalArgumentException if the date string is not of DD/MM/YYYY format
     */
    private static Date parseDeadline(String dateString) throws IllegalArgumentException {
        try {
            Date date = inputDateFormat.parse(dateString);
            return date;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: Dates should be in DD/MM/YYYY");
        }
    }

    @Override
    public String getDescription() {
        String formattedDeadline = outputDateFormat.format(this.deadline);
        return String.format("[D]%s %s (by: %s)",
                super.getDoneStatusCheckbox(), super.getName(), formattedDeadline);
    }

    @Override
    public String getSeralisedTaskData() {
        String doneString = this.isDone() ? "Y" : "N";
        return String.format("D,%s,%s,%s", super.getName(), doneString, this.deadline);
    }
}
