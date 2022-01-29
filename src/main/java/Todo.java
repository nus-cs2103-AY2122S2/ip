public class Todo extends Task {

    Todo(String details) {
        super(details);
    }

    public String symbol() {
        return "T";
    }

    @Override
    public String displayTime() {
        return super.toString();
    }

}
