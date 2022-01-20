/**
 * Provides abstraction of an task in the todoList
 */
public class Task {
    private boolean finished;
    private String content;

    /**
     * Contructs an item object
     * @param content description of what to do in String
     */
    public Task (String content) {
        this.content = content;
        this.finished = false;
    }

    /**
     * marks an item as done
     */
    public void finished() {
        this.finished = true;
    }

    /**
     * marks an item as unfinished
     */
    public void notFinished() {
        this.finished = false;
    }

    @Override
    public String toString() {
        if (finished) {
            return "[X] " + content;
        } else {
            return "[ ] " + content;
        }
    }

}
