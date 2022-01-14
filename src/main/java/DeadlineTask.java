public class DeadlineTask extends Task {
    String preposition;
    String deadline;

    DeadlineTask(String name, String preposition, String deadline) {
        super(name);
        this.preposition = preposition;
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + String.format(" (%s: %s)", this.preposition, this.deadline);
    }
}
