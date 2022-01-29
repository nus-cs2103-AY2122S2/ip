public class WordListItem {
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

    @Override
    public String toString() {
        String doneSymbol = isDone ? "[X]" : "[ ]";
        return doneSymbol + " " + this.description;
    }
}
