package duke;

/**
 * Encapsulates boolean value to be used like a mutable object.
 */
public class Over {
    private boolean isOver;

    /**
     * Creates an Over object
     */
    public Over() {
        this.isOver = false;
    }

    /**
     * sets this as true
     */
    public void setOver() {
        this.isOver = true;
    }

    public boolean isOver() {
        return isOver;
    }
}
