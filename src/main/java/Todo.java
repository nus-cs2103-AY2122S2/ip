public class Todo extends Task{
    public Todo(String description, int taskNumber) {
        super(description, taskNumber);
    }

    @Override
    public String toString() {
        return super.taskNumber + "." + "[T]" + this.getStatusIcon() + " " + super.description;
    }
}
