package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

public class Deadline extends Task {
    private String[] dateAndTime;
    private LocalDate date;
    private String time;

    /**
     * Creates a Deadline task and sets its date and time accordingly from user input.
     *
     * @param description Description of the deadline task generated from user input.
     * @throws DukeException If the input format is not followed or there are missing information.
     */
    public Deadline(String description) throws DukeException {
        super(description);
        try {
            String[] strArr = description.split("/by ");
            this.description = strArr[0];
            dateAndTime = strArr[1].split(" ");
            date = LocalDate.parse(dateAndTime[0]);
            time = dateAndTime[1];
            super.saveFormat = "D," + this.description + "," + date + "," + time + "," + super.isDone;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    /**
     * Creates a Deadline task from previously saved list of tasks during initialisation of chat-bot.
     *
     * @param saveFormat Data saved in tasks list file.
     * @param isSaved A Boolean value set to True to differentiate the creation of task from saved list and user input.
     * @throws DukeException If the format is not followed or there are missing information.
     */
    public Deadline(String saveFormat, boolean isSaved) throws DukeException {
        super(saveFormat);
        try {
            String[] strArr = description.split(",");
            this.description = strArr[1];
            date = LocalDate.parse(strArr[2]);
            time = strArr[3];
            if (Boolean.parseBoolean(strArr[4])) {
                super.setDone();
            }
            super.saveFormat = strArr[0] + "," + strArr[1] + "," + strArr[2] + "," + strArr[3];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return A description of the task including its type, status, date and time.
     */
    @Override
    public String toString() {
        return "D | " + super.toString() + "BY: " + date.format(DateTimeFormatter.ofPattern("MMM d YYYY"))
                + " " + time;
    }
}
