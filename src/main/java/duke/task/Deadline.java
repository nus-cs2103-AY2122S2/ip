package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Represents a task object that needs to be completed by
 * a certain date.
 */
public class Deadline extends Task {

    private LocalDate date;

    /**
     * Creates a new Deadline object.
     *
     * @param description The title of the deadline task.
     * @param dLine       The date in which the task is due by.
     */
    public Deadline(String description, LocalDate dLine) {
        super(description);
        this.date = dLine;
    }

    /**
     * Returns a string description of a deadline object.
     *
     * @return A string description of the deadline task.
     */
    @Override
    public String toString() {
        String date = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + this.getSymbol() + " " + this.getName() + " (by: " + date + ")";
    }

    /**
     * Creates a new Deadline object from a text input.
     *
     * @param input The string input which contains info on the deadline task.
     * @return A new Deadline object.
     * @throws StringIndexOutOfBoundsException If keyword /by is not used.
     * @throws DukeException If no title or date is input.
     */
    public static Deadline createDeadline(String input)
            throws StringIndexOutOfBoundsException, DukeException {
        String dlTask = input.substring(8);
        dlTask = dlTask.trim();
        if (dlTask.equals("")) {
            throw new DukeException("Empty description for Deadline object");
        }
        String dLine = "/by";
        int dlDatePos = dlTask.indexOf(dLine);
        String dlDate = dlTask.substring(dlDatePos + 3);
        String dlDes = dlTask.substring(0, dlDatePos);
        if (dlDate.trim().equals("") || dlDes.trim().equals("")) {
            throw new DukeException("No valid date/description entered");
        }
        LocalDate dateOfDeadline = LocalDate.parse(dlDate.trim());
        Deadline dl = new Deadline(dlDes.trim(), dateOfDeadline);
        System.out.println("Got it! I've added this task:");
        System.out.println(dl.toString());
        return dl;
    }

    /**
     * Creates a new Deadline object from a text input, a status value,
     * and a date text.
     *
     * @param status      Indicates if task is marked as completed or not. 1 being
     *                    complete and 0 being incomplete.
     * @param description The given title of the deadline task.
     * @param date        The due date of the deadline task.
     * @return A new Deadline object.
     */
    public static Deadline createDeadline(int status, String description, String date) {
        Deadline dl = new Deadline(description, LocalDate.parse(date));
        if (status == 1) {
            dl.markTask();
        }
        return dl;
    }

    /**
     * Formats a Deadline object to text.
     *
     * @return A text with information regarding the Deadline object.
     */
    @Override
    public String formatText() {
        int status = (this.getStatus()) ? 1 : 0;
        return "D|" + status + "|" + this.getName() + "/" + this.date.toString();
    }
}
