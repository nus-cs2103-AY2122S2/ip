public class Deadline extends Task {
    public Deadline(String description) {
        super(description);
    }
    public String getDescription() {
        String newReply = super.description.replace("deadline ", "");
        String taskAtHand = newReply.split("/")[0];
        String deadline = newReply.split("/")[1].replace("by ", "by: ");
        String finalDescription = taskAtHand + "(" + deadline + ")";
        return "[D]" + "[" + super.getStatusIcon() + "] " + finalDescription;
    }
}
