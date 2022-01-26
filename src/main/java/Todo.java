public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description,isDone);
    }

    @Override
    public String dataFormatOfTask() {
        String bool;
        if(this.isDone) {
            bool = "1";
        } else {
            bool = "0";
        }
        return "T | " + bool + " | " + this.description;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
