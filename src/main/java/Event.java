public class Event extends Task {
    public String time;

    public Event(String input) {
        super(input.substring(6, input.indexOf("/")));
        this.time = input.substring(input.indexOf("/") + 4);
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + ("(at: " + this.time + ")");
    }
}
