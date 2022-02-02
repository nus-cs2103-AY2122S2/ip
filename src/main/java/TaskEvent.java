public class TaskEvent extends Task {
    private final String date;
    private final String time;

    TaskEvent(String name, String date, String time) {
        super(name);
        this.date = date;
        this.time = new ManagerTime(time).getFormat24();
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
                + ManagerDate.formatDate(this.date) + " "
                + this.time;
    }
}
