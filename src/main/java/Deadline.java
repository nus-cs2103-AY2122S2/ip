public class Deadline extends Task{
    protected String dead;

    public Deadline(String name, String dead) {
        super(name);
        this.dead = dead;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + dead + ")";
    }
}
