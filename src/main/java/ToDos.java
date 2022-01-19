public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public String getDescription() {
        return "[T]" + this.getDescription() + "\n";
    }
}
