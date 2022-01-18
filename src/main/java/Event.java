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
    public static Event formatInput(String input) throws StringIndexOutOfBoundsException, DukeException {
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
        return new Event(evDes.trim(), evDate);
    }

}
