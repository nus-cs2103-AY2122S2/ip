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
}
