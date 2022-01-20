public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc, TaskType.TODO);
    }

    @Override
    public String toString() {
        return getType() + super.toString();
    }
}
