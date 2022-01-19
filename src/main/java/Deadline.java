public class Deadline extends Task{
    protected String by;

    public Deadline(String[] input) {
        super(input[0]);
        this.by = input[1];
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
