public class Events extends Task{
    protected String at;

    public Events(String objective, String at) {
        super(objective);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
