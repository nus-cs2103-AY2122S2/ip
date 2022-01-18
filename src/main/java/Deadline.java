public class Deadline extends Task {
    
    String deadline;

    Deadline(String deadline, String d) {
        super(d);
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
