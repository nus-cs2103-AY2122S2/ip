package duke.tasks;

/**
 * A WordList item in which all the tasks (Todo, Deadline, Event) derives from.
 * A WordListItem is stored inside a WordList object.
 * As WordListItem is a more general tasks, it has a description and isDone label.
 * @see duke.tasks.Todo
 * @see duke.tasks.Deadline
 * @see duke.tasks.Event
 */
public abstract class WordListItem {
    private String description;
    private boolean isDone;

    /**
     * A generic constructor for all WordListItem.
     * @param description
     */
    public WordListItem(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * mark this WordListItem as done
     */
    public void markItem() {
        this.isDone = true;
    }

    /**
     * mark this WordListItem as not done
     */
    public void unmarkItem() {
        this.isDone = false;
    }

    /**
     * get the description of the WordListItem
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * get the isDone of the WordListItem
     * @return
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * get the symbol of WordListItem
     * @return null
     */
    static public String getSymbol() {
        return null;
    }

    @Override
    public String toString() {
        String doneSymbol = isDone ? "[X]" : "[ ]";
        return doneSymbol + " " + this.description;
    }
}
