public class Event extends Task {
    private final String date;
    private final String time;

    Event(String name, String date, String time) {
        super(name);
        this.date = date;
        this.time = new TimeManager(time).getFormat24();
    }

    public String getPrefix() {
        return "E";
    }

    @Override
    public String getDate() {
        return this.date;
    }

    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        String prefix = "[E]";
        return prefix + super.toString() + " on: "
                + DateManager.formatDate(this.date) + " "
                + this.time;
    }
}
