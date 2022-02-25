package duke;

class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    @Override
    public String getInitial() {
        return "[E]";
    }

    @Override
    public String getTime() {
        String timeFormat = "hh:mm AM";
        return at.substring(at.length() - timeFormat.length());
    }
}
