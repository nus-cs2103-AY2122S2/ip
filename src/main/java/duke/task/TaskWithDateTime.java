package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An abstract class that represents a task with date/time.
 */
public abstract class TaskWithDateTime extends Task {
    private final String dateTimeInput;
    private LocalDate date;

    /* DateTime of the task to be printed */
    private String dateTimeOutput;

    /**
     * Constructor to initialize an instance of TaskWithDateTime class with task
     * type, description and date/time input.
     *
     * @param type Type of task
     * @param description Description of the task
     * @param dateTimeInput Date/time input of the task
     */
    public TaskWithDateTime(TaskType type, String description, String dateTimeInput) {
        this(type, description, dateTimeInput, false);
    }

    /**
     * Constructor to initialize an instance of TaskWithDateTime class with task
     * type, description, date/time input and isDone flag.
     *
     * @param type Type of task
     * @param description Description of the task
     * @param dateTimeInput Date/time input of the task
     * @param isDone Flag to indicate if the task is done
     */
    public TaskWithDateTime(TaskType type, String description, String dateTimeInput, boolean isDone) {
        super(type, description, isDone);
        this.dateTimeInput = dateTimeInput;
        processDateTimeInput();
    }

    /**
     * Processes the date/time input string to check if it is a valid date/time
     * format accepted by the system. If it is a valid date/time format, generate
     * output with the proper date/time, otherwise generate output with the input.
     */
    private void processDateTimeInput() {
        String dateInput;
        String timeInput;
        LocalTime timeStart;
        LocalTime timeEnd;

        if (dateTimeInput.contains("d/") && dateTimeInput.contains("t/")) {
            dateInput = dateTimeInput.split("d/", 2)[1].trim().split("t/", 2)[0].trim();
            timeInput = dateTimeInput.split("t/", 2)[1].trim();

            // Process date input
            date = processDateInput(dateInput);

            // Process time input
            if (timeInput.length() == 9 && timeInput.charAt(4) == '-') {
                String timeInputStart = timeInput.substring(0, 4);
                String timeInputEnd = timeInput.substring(5);

                timeStart = isTimeInputProper(timeInputStart) ? processTimeInput(timeInputStart) : null;
                timeEnd = isTimeInputProper(timeInputEnd) ? processTimeInput(timeInputEnd) : null;
            } else {
                timeStart = null;
                timeEnd = isTimeInputProper(timeInput) ? processTimeInput(timeInput) : null;
            }

            // Generate dateTime output
            dateTimeOutput = generateDateTimeOutput(date, dateInput, timeStart, timeEnd, timeInput);
        } else if (dateTimeInput.contains("d/")) {
            dateInput = dateTimeInput.split("d/", 2)[1].trim();
            timeInput = "";

            // Process date input
            date = processDateInput(dateInput);

            // Generate dateTime output
            dateTimeOutput = generateDateTimeOutput(date, dateInput, null, null, timeInput);
        } else {
            dateInput = dateTimeInput;
            dateTimeOutput = dateTimeInput;
        }
    }

    /**
     * Returns the date in LocalDate format if date input string is a valid date
     * format accepted by the system.
     *
     * @param dateInput Date input of the task
     * @return The date, if valid, in LocalDate format, null otherwise
     */
    private LocalDate processDateInput(String dateInput) {
        try {
            return LocalDate.parse(dateInput);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Checks if the time input string is in a proper time format of 4 digits
     * string and within the possible range (0000 to 2359).
     *
     * If time input string is not in a proper time format of 4 digits, it is
     * still considered a proper time input defined by the user.
     *
     * @param timeInput Time input of the task
     * @return True if time input is proper and within range, false otherwise
     */
    private boolean isTimeInputProper(String timeInput) {
        try {
            int timeInputInInt = Integer.parseInt(timeInput);
            return timeInput.length() == 4 && (timeInputInInt >= 0 && timeInputInInt <= 2359);
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     * Returns the time in LocalTime format if time input string is a valid time
     * format accepted by the system.
     *
     * @param timeInput Time input of the task
     * @return The time, if valid, in LocalTime format, null otherwise.
     */
    private LocalTime processTimeInput(String timeInput) {
        try {
            int timeInputInInt = Integer.parseInt(timeInput);

            int hour = timeInputInInt / 100;
            int min = timeInputInInt - (hour * 100);

            String timeInputInTimeFormat = (hour < 10 ? "0" + hour : hour)
                    + ":" + (min < 10 ? "0" + min : min);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
            return LocalTime.parse(timeInputInTimeFormat, timeFormatter);
        } catch (DateTimeParseException | NumberFormatException e) {
            return null;
        }
    }

    /**
     * Generates the date/time output of the task.
     *
     * @param date Date in LocalDate format
     * @param dateInput Date input of the task
     * @param timeStart Start time in LocalTime format
     * @param timeEnd End time in LocalTime format
     * @param timeInput Time input of the task
     * @return The date/time output of the task
     */
    private String generateDateTimeOutput(LocalDate date, String dateInput, LocalTime timeStart,
                                          LocalTime timeEnd, String timeInput) {
        String dateTimeOutput = "";

        // For task with date (in LocalDate format), timeStart (in LocalTime format)
        // and timeEnd (in LocalTime format)
        // Put date, timeStart and timeEnd into output String
        if (date != null && timeStart != null && timeEnd != null) {
            dateTimeOutput = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + ", " + timeStart.format(DateTimeFormatter.ofPattern("hh:mm a"))
                    + " to " + timeEnd.format(DateTimeFormatter.ofPattern("hh:mm a"));
            return dateTimeOutput;
        }

        // For task with date (in LocalDate format) and timeEnd (in LocalTime format)
        // Put date, timeStart and timeEnd into output String
        if (date != null && timeStart == null && timeEnd != null) {
            dateTimeOutput = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + ", " + timeEnd.format(DateTimeFormatter.ofPattern("hh:mm a"));
            return dateTimeOutput;
        }

        // For task without date (in LocalDate format), timeStart (in LocalTime format)
        // and timeEnd (in LocalTime format)
        // Put original date and time input into output String
        if (date == null && timeStart == null && timeEnd == null) {
            dateTimeOutput = dateTimeInput;
            return dateTimeOutput;
        }

        // Put date (in LocalDate format) into output String if it is not null,
        // otherwise put the original date input
        if (date != null) {
            dateTimeOutput = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            dateTimeOutput = dateInput;
        }

        // Put timeStart (in LocalTime format) and/or timeEnd (in LocalTime format)
        // into output String if it is not null, otherwise put the original time input
        if (timeStart != null && timeEnd != null) {
            dateTimeOutput += ", " + timeStart.format(DateTimeFormatter.ofPattern("hh:mm a"))
                    + " to " + timeEnd.format(DateTimeFormatter.ofPattern("hh:mm a"));
        } else if (timeStart == null && timeEnd != null) {
            dateTimeOutput += ", " + timeEnd.format(DateTimeFormatter.ofPattern("hh:mm a"));
        } else {
            if (!timeInput.equals("")) {
                if ((timeInput.length() == 9 && timeInput.charAt(4) == '-') || !isTimeInputProper(timeInput)) {
                    dateTimeOutput += ", " + timeInput + "[Note: Invalid time format]";
                } else {
                    dateTimeOutput += ", " + timeInput;
                }

            }
        }

        return dateTimeOutput;
    }

    /**
     * Returns the string representation of the date/time in output format.
     *
     * @return The string representation of the date/time for output
     */
    public String getDateTimeOutput() {
        return dateTimeOutput;
    }

    /**
     * An abstract method to return the date/time information of the task.
     * The method will be implemented in the extended classes.
     *
     * @return The string representation of the date/time information
     */
    public abstract String dateTimeInfo();

    /**
     * Checks if the date of the task is on the specified date.
     *
     * @param dateStr Specified date
     * @return Flag to indicate if the date of the task is on the specified date
     */
    @Override
    public boolean isOnDate(String dateStr) {
        try {
            LocalDate dateToSearch = LocalDate.parse(dateStr);
            return date != null && date.equals(dateToSearch);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns the string representation of the task with date/time information.
     *
     * @return The string representation of the task with date/time information
     */
    @Override
    public String toString() {
        return super.toString() + " " + dateTimeInfo();
    }

    /**
     * Returns the string representation of the task with date/time in save format.
     *
     * @return The string representation of the task with date/time to be saved
     */
    public String saveFormat() {
        return super.saveFormat() + " | " + dateTimeInput;
    }
}
