public class ToDo extends Task{

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
