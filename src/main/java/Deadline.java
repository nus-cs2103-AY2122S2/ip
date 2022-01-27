public class Deadline extends Task {
    public String time;

    public Deadline(String input) {
        super(input.substring(9, input.indexOf("/")));
        this.time = input.substring(input.indexOf("/") + 4);
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + ("(by: " + this.time + ")");
    }
}
