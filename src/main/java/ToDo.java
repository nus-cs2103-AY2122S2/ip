public class ToDo extends Task {

    public ToDo(String title) {
        super(title, "T", null);
    }

    public ToDo(String title, String done) {
        super(title, "T", done, null);
    }
}
