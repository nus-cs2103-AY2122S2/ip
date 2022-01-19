public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public String getToDo() {
        return "[T]" + this.getTask() + "\n";
    }
}
