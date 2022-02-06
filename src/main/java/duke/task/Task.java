package duke.task;

public abstract class Task {
    protected String title;

    public Task(String title) {
        this.title = title;
    }

    boolean checked = false;

    public void setChecked() {
        this.checked = true;
    }

    public void setUnChecked(){
        this.checked = false;
    }

    /**
     * Checks if title contains term.
     * @param term Search term by user
     * @return boolean True if title contains term, false otherwaise
     */
    public boolean titleContains(String term) {
        return title.contains(term);
    }
}
