public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public void markAsDone() {
        super.markAsDone();
        System.out.println(super.markAsDoneFeedback + this);
    }

    @Override
    public void markAsUndone() {
        super.markAsUndone();
        System.out.println(super.markAsUndoneFeedback + this);
    }
}