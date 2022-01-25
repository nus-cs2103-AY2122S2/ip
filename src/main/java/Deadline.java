public class Deadline extends Task {
    
    String deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String tempStr = " ";

        if (this.isDone) {
            tempStr = "X";
        }

        return "[D][" + tempStr + "] " + this.description + "(" + this.deadline + ")";
    }

}
