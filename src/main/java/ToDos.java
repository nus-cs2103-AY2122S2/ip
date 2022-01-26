public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public ToDos(int mark, String description) {
        super(description, mark);
    }

    public String getToDo() {
        return "[T]" + this.getTask() + "\n";
    }

    public String getFormattedText() {
        return "T:" + this.getMark() + ":" + this.getDescription();
    }
}
