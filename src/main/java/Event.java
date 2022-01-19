public class Event extends Task {
    protected String at;

    public Event(String[] input) {
        super(input[0]);
        this.at = input[1];
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }

}
