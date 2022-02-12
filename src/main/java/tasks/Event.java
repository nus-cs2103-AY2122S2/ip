package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import yeowoo.YeowooException;

public class Event extends Task {
    private String[] dateAndTime;
    private LocalDate date;
    private String time;

    /**
     * Creates a Deadline task and sets its date and time accordingly from user input.
     *
     * @param description Description of the event task generated from user input.
     * @throws YeowooException If the input format is not followed or there are missing information.
     */
    public Event(String description) throws YeowooException {
        super(description);
        try {
            String[] strArr = description.split("/at ");
            this.description = strArr[0];
            dateAndTime = strArr[1].split(" ");
            date = LocalDate.parse(dateAndTime[0]);
            time = dateAndTime[1];
            super.saveFormat = "E," + this.description + "," + date + "," + time;
        } catch (Exception e) {
            throw new YeowooException();
        }
    }

    /**
     * Creates an Event task from previously saved list of tasks during initialisation of chat-bot.
     *
     * @param saveFormat Data saved in tasks list file.
     * @param isSaved A Boolean value set to True to differentiate the creation of task from saved list and user input.
     * @throws YeowooException If the format is not followed or there are missing information.
     */
    public Event(String saveFormat, boolean isSaved) throws YeowooException {
        super(saveFormat);
        String[] strArr = description.split(",");
        assert strArr.length == 5;
        this.description = strArr[1];
        date = LocalDate.parse(strArr[2]);
        time = strArr[3];
        if (Boolean.parseBoolean(strArr[4])) {
            super.setDone();
        }
        super.saveFormat = strArr[0] + "," + strArr[1] + "," + strArr[2] + "," + strArr[3];
    }

    /**
     * Returns the date of the event.
     * @return A LocalDate of the date of the event.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return a description of the task including its type, status, date and time.
     */
    @Override
    public String toString() {
        return "E | " + super.toString() + "AT: " + date.format(DateTimeFormatter.ofPattern("MMM d YYYY"))
                + " " + time;
    }

}
