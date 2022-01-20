public class Deadline extends Task {
    public String time;

    public Deadline(String input) {
        super(input.substring(9, input.indexOf("/")));
        this.time = input.substring(input.indexOf("/") + 4);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + ("(by: " + this.time + ")");
    }
}
