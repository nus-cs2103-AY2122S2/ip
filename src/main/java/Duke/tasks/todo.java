package Duke.tasks;

public class todo extends Task{
    public todo(String item) {
        super(item);
    }

    @Override
    public String toString() {
        return String.format("T|%s|%s", this.finished(), super.toString());
    }
}
