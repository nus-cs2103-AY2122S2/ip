public class Deadline extends Task{
    public Deadline(String description) {
        super(description);
        String[] strArr = description.split("/by");
        this.description = strArr[0] + "(" + "by:" + strArr[1] + ")";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
