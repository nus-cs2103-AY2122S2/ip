public class Event extends Task {
    protected String type;
    protected String time;

    Event(String name, String time) {
        super(name);
        this.time = time;
        this.type = "E";
    }

    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + this.type + "][" + status + "]" + this.name + " (at: "
                + this.time + ")";
    }

}
