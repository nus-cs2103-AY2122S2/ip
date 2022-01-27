public class Event extends Task {

    private String date;

    public Event(String details, String date) {
        super(details);
        this.date = date;
    }

    @Override
    public String toString() {
        return "E" + super.toString() + " | " + this.date;
    }

}
