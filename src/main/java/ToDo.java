public class ToDo extends Task {

    protected String type;

    public ToDo(String description) {
        super(description);
        this.type = "todo";
    }

    @Override
    public String taskDescriptionForFile() {
        int i = super.isDone ? 1 : 0;
        return "T , " + i + " , " + this.description.trim();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
