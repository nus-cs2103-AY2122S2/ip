public class Task {
    private final String title;
    private Boolean done;

    protected Task(String title) {
        this.title = title;
        this.done = false;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
