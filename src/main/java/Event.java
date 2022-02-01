public class Event extends Task {
    private final String date;
    private final String time;
    private final String postfix;

    Event(String name, String date, String time) {
        super(name);
        this.date = DateManager.formatDate(date);
        this.time = new TimeManager(time).getFormat12();
        this.postfix = this.date + " " + this.time;
    }

    public String getPrefix() {
        return "E";
    }

    public String getPostfix() {
        return this.postfix;
    }

    @Override
    public String toString() {
        String prefix = "[E]";
        return prefix + super.toString() + " on:" + postfix;
    }
}
