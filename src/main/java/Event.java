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

    public static Event createTask(String[] tokens) {
        boolean found = false;
        String item = "";
        String dateTime = "";
        for (String token : tokens) {
            if (token.equals("event")) {
                continue;
            } else if (token.equals("/at")) {
                found = true;
                continue;
            }
            if (found) {
                dateTime += token + " ";
            } else {
                item += token + " ";
            }
        }
        return new Event(item.trim(), dateTime.trim());
    }
}
