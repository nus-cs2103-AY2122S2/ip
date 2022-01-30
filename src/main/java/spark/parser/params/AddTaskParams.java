package spark.parser.params;

public abstract class AddTaskParams {
    protected String title;

    public AddTaskParams(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
