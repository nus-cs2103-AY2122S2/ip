public class Deadline extends Task {
    String type = "D";
    String date;

    Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + "(by: "+ this.date + ")";
    }
}
