public class Events extends Task{
    protected String at;

    public Events(String objective, String at) {
        super(objective);
        this.at = at;
    }
    public Events(String objective, Boolean done, String at) {
        super(objective, done);
        this.at = at;
    }
    @Override
    public String serialize() {
        return "E|" + (this.done ? "1|" : "0|") + this.objective + "|" + this.at +"\n";
    };
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
