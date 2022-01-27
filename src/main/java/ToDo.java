public class ToDo extends Task{

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    public String toFile() {
        String markStatus = super.isMarked ? "1" : "0";
        return String.format("%s | %s | %s\n", "T", markStatus, super.taskName);
    }
}
