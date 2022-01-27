import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An abstract class that represents a task with date/time.
 */
public abstract class TaskWithDateTime extends Task {
    private String dateTimeInput;
    private String dateInput;
    private String timeInput;

    private LocalDate date;
    private LocalTime time;

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
        String[] dateTimeInputParts = dateTimeInput.split("\\s+", 2);

        if (dateTimeInputParts.length == 2) {
            dateInput = dateTimeInputParts[0].trim();
            timeInput = dateTimeInputParts[1].trim();

            // Omit any additional whitespaces in between date and time inputs
            dateTimeInput = dateInput + " " + timeInput;
        } else {
            dateInput = dateTimeInputParts[0].trim();
            timeInput = "";

            // Omit any additional whitespaces that comes with date input
            dateTimeInput = dateInput;
        }

        // Process date input
        date = processDateInput(dateInput);

        // Process time input
        if (isTimeInputProper(timeInput)) {
            time = processTimeInput(timeInput);
        } else {
            time = null;
        }

        // Generate dateTime output
        dateTimeOutput = generateDateTimeOutput(date, dateInput, time, timeInput,
                isTimeInputProper(timeInput));
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
     * @param time Time in LocalTime format
     * @param timeInput Time input of the task
     * @param isTimeInputProper Flag to indicate if the input time is proper
     * @return The date/time output of the task
     */
    private String generateDateTimeOutput(LocalDate date, String dateInput, LocalTime time,
                                          String timeInput, boolean isTimeInputProper) {
        String dateTimeOutput = "";

        if (date != null) {
            dateTimeOutput = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            dateTimeOutput = dateInput;
        }

        if (time != null) {
            dateTimeOutput += ", " + time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        } else {
            if (!timeInput.equals("")) {
                dateTimeOutput += ", " + timeInput;

                if (!isTimeInputProper) {
                    dateTimeOutput += " [Note: Invalid time format]";
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
     * Returns the string representation of the task with date/time information.
     *
     * @return The string representation of the task with date/time information
     */
    @Override
    public String toString() {
        return super.toString() + " " + dateTimeInfo();
    }
}
