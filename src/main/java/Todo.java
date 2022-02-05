public class Todo extends Task{
    public boolean isDone;

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    public String toSave() {
        int isDoneNumber;
        if(isDone) {
            isDoneNumber = 1;
        } else {
            isDoneNumber = 0;
        }
        return "T | " + isDoneNumber + " | " + description +
                System.lineSeparator();
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
