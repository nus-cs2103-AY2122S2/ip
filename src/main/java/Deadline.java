public class Deadline extends Task {
    private final String date;
    private final String time;

    Deadline(String name, String date, String time) {
        super(name);
        this.date = date;
        this.time = new TimeManager(time).getFormat24();
    }

    public String getPrefix() {
        return "D";
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
        String prefix = "[D]";
        return prefix + super.toString() + " on: "
                + DateManager.formatDate(this.date) + " "
                + this.time;
    }
}
