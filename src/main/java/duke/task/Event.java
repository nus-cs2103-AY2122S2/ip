package duke.task;

import duke.DukeException;

/**
 * Represents a task object that needs to be completed on
 * a certain date.
 */
public class Event extends Task {

    private String date;

    /**
     * Creates a new Event object.
     *
     * @param description The title of the Event task.
     * @param d The date in which the task will occur on.
     */
    public Event(String description, String d) {
        super(description);
        this.date = d;
    }

    /**
     * Returns a string description of a event object.
     *
     * @return A string description of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + this.getSymbol() + " " + this.getName() + " (at: " + this.date.trim() + ")";
    }

    /**
     * Creates a new Event object from a text input.
     *
     * @param input The string input which contains info on the event task.
     * @return A new Event object.
     * @throws StringIndexOutOfBoundsException If keyword /at is not used.
     * @throws DukeException If no title or date is input.
     */
    public static Event createEvent(String input) throws StringIndexOutOfBoundsException, DukeException {
        String evTask = input.substring(5); //Grabs all the text after the "event" command word
        evTask = evTask.trim();
        if (evTask.equals("")) {
            throw new DukeException("Empty description for the Event object");
        }
        String event = "/at";
        int evDatePos = evTask.indexOf(event);
        String evDate = evTask.substring(evDatePos + 3); //Grabs all the text after the "/at" key word
        String evDes = evTask.substring(0, evDatePos);
        if (evDes.trim().equals("") || evDate.trim().equals("")) {
            throw new DukeException("No valid date/description entered");
        }
        Event ev = new Event(evDes.trim(), evDate);
        System.out.println("Got it! I've added this task:");
        System.out.println(ev.toString());
        return ev;
    }

    /**
     * Creates a new Event object from a text input, a status value,
     * and a date text.
     *
     * @param status Indicates if task is marked as completed or not. 1 being
     * complete and 0 being incomplete.
     * @param description The given title of the event task.
     * @param date The due date of the event task.
     * @return A new Event object.
     */
    public static Event createEvent(int status, String description, String date) {
        Event ev = new Event(description, date);
        if (status == 1) {
            ev.markTask();
        }
        return ev;
    }

    /**
     * Formats a Event object to text.
     *
     * @return A text with information regarding the Event object.
     */
    @Override
    public String formatText() {
        int status = (this.getStatus()) ? 1 : 0;
        return "E|" + status + "|" + this.getName() + "/" + this.date.trim();
    }

}
