public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toStorageString() {
        return String.format("T#%s#%s", this.getStatusIcon(), this.description);
    }
}
