public class Todo extends Task {
    public Todo(String name) {
        super(name);
        super.type = "T";
    }

    @Override
    public String printStatus() {
        return "[T] " + Task.statusSymbols[super.getStatus()] + " " + this.toString();
    }
}
