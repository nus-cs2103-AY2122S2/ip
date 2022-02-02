public class Deadline extends Task{
    String dString;

    Deadline(String item, String dString) {
        super(item);
        this.dString = dString;
    }

    @Override
    public String toString() {
        return String.format("D|%s|%s|%s", this.finished(), super.toString(), dString);
    }
}
