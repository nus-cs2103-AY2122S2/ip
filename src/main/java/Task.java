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

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }

    public String toString(){
        String isDone=(done==true)?"X":" ";
        return "["+isDone+"] " +getTitle();
    }
}
