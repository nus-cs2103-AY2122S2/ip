public class DeadlineTask extends Task {

    private final String date;

    public DeadlineTask(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s %s",
                getStatusIcon(), name, String.format("(by: %s)", this.date));
    }

    @Override
    public String toStore() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.name, this.date);
    }
}
