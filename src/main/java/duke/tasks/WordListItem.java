package duke.tasks;

public abstract class WordListItem {
    private String description;
    private boolean isDone;

    public WordListItem(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markItem() {
        this.isDone = true;
    }

    public void unmarkItem() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    static public String getSymbol() {
        return null;
    }

    @Override
    public String toString() {
        String doneSymbol = isDone ? "[X]" : "[ ]";
        return doneSymbol + " " + this.description;
    }
}
