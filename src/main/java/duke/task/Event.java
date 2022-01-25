package duke.task;

import duke.task.Task;
import duke.DukeException;

public class Event extends Task {

    private String date;

    public Event(String description, String d) {
        super(description);
        this.date = d;
    }

    @Override
    public String toString() {
        return "[E]" + this.getSymbol() + " " + this.getName() + " (at: " + this.date.trim() + ")";
    }

    //Formats a line of text into an Event object
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
        if (evDate.trim().equals("") || evDate.trim().equals("")) {
            throw new DukeException("No valid date/description entered");
        }
        Event ev = new Event(evDes.trim(), evDate);
        System.out.println("Got it! I've added this task:");
        System.out.println(ev.toString());
        return ev;
    }

    public static Event createEvent(int status, String description, String date) {
        Event ev = new Event(description, date);
        if (status == 1) {
            ev.markTask();
        }
        return ev;
    }

    @Override
    public String formatText() {
        int status = (this.getStatus()) ? 1 : 0;
        return "E|" + status + "|" + this.getName() + "/" + this.date.trim();
    }

}
