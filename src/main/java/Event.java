public class Event extends Task {
    protected final String sym = "E";
    private String dayAndTime;

    Event (String description, String dayAndTime) {
        super(description);
        this.dayAndTime = dayAndTime;
    }

    @Override
    String getSym() {
        return this.sym;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s]%s (at:%s)", sym, super.getStatusIcon(), super.description, this.dayAndTime);
    }


}
