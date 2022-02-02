package li.zhongfu.cs2103.chatbot.types.tasks;

/**
 * A representation of a to-do task item, which has a name and can be set as done.
 */
public class ToDo extends Task {
    /**
     * Creates a new to-do task item.
     * 
     * @param name the name of the task item
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of this to-do item.
     * 
     * @returns a string representation of this to-do item
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getDone() ? "X" : " ", this.getName());
    }

    // no equals() and hashCode() -- use Task implementation
}
