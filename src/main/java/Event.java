import exceptions.DukeEventException;

public class Event extends Task {

    protected String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    public static Event setEvent(String input) {
        String eventname;
        String eventat;

        try {
            String[] split = input.split("/at");
            eventname = split[0];
            eventat = split[1];
            Event e = new Event(eventname, eventat);
            System.out.println("Got it. I've added this task: ");
            System.out.println(e.toString());
            return e;
        } catch (Exception e) {
            DukeEventException error = new DukeEventException("OOPS!!! Please enter in format: event <event>/at<event date>");
            System.out.println(error.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + this.at + ")";
    }
}
