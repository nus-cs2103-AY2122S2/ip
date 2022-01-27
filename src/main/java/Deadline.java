public class Deadline extends Task{
    private final String time;

    Deadline(String task, String time) {
        super(task, "D");
        this.time = time;
    }

    @Override
    String saveFormat() {
        return super.saveFormat() + "/" + this.time;
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + this.time + ")";
    }
}
