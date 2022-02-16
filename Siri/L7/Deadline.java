public class Deadline extends Task {

    String deadline;

    public Deadline(String description, String initialLetter, String deadline) {
        super(description, initialLetter);
        this.deadline = deadline;
        //find a way to scan deadline after description entered by user, maybe use the "/" as delimiter for the scanner?
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + this.deadline + ")";
    }

}
