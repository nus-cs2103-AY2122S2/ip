public class Deadline extends Task {

    private String date;

    public Deadline(String details, String date) {
        super(details);
        this.date = date;
    }

    @Override
    public String toString() {
        return "D" + super.toString() + " | " + this.date;
    }

}
