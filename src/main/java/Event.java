public class Event extends Task {

    public String dateTime;

    public Event(String item, String dateTime) {
        super(item);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }

    public static Event createTask(String[] tokens) throws DukeException {
        boolean found = false;
        String item = "";
        String location = "";
        for (String token : tokens) {
            if (token.equals("event")) {
                continue;
            } else if (token.equals("/at")) {
                found = true;
                continue;
            }
            if (found) {
                location += token + " ";
            } else {
                item += token + " ";
            }
        }

        if (item.equals(""))
            throw new DukeException("The description of an event task cannot be empty!");
        else if (location.equals(""))
            throw new DukeException("Please specify a location for the event!");

        return new Event(item.trim(), location.trim());
    }
}
