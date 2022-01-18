public class Event extends Task {
    public Event(String description) {
        super(description);
    }

    public String getDescription() {
        String newReply = super.description.replace("event ", "");
        String taskAtHand = newReply.split("/")[0];
        String event = newReply.split("/")[1].replace("at ", "at: ");
        String finalDescription = taskAtHand + "(" + event + ")";
        return "[E]" + "[" + super.getStatusIcon() + "] " + finalDescription;
    }
}
