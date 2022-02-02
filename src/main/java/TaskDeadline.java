public class TaskDeadline extends Task {
    private final String date;
    private final String time;

    TaskDeadline(String name, String date, String time) {
        super(name);
        this.date = date;
        this.time = new ManagerTime(time).getFormat24();
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
                + ManagerDate.formatDate(this.date) + " "
                + this.time;
    }
}
