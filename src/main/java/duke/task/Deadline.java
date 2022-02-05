package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeParseException;

/**
 * Deadline represents a task that the user has to to by a certain time.
 *
 * @author Jian Rong
 */
public class Deadline extends Task{
    private final String title;
    private final LocalDate date;
    private final LocalTime time;


    /**
     * Constructor of Deadline class.
     *
     * @param command Input from User
     * @throws DukeException If syntax of command is wrong.
     */
    public Deadline(String command) throws DukeException {
        try {
            this.title = command.split(" /by ")[0];
            String deadline = command.split(" /by ")[1];
            String[] deadlineList = deadline.split(" ");
            this.date = LocalDate.parse(deadlineList[0].replace("/", "-"));
            this.time = LocalTime.parse(deadlineList[1]);
        } catch (IndexOutOfBoundsException | DateTimeParseException e){
            throw new DukeException("Please tell me the deadline in this format: <Activity> /by YYYY/MM/DD HH:MM");
        }
        System.out.println("added: " + this.toString());
    }

    /**
     * Returns a summary of the Deadline Task.
     * @return The summary of the Deadline.
     */
    public String toString(){
        int day = date.getDayOfMonth();
        Month month = date.getMonth();
        int year = date.getYear();
        if (this.checked) {
            return String.format("[D][X] %s (by: %d %s %d %s)", title, day, month, year, time);
        } else {
            return String.format("[D][ ] %s (by: %d %s %d %s)", title, day, month, year, time);
        }
    }
}
