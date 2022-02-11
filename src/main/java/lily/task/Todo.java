package lily.task;

/**
 * A simple Task which only contains a description.
 * 
 * @author Hong Yi En, Ian
 * @version Jan 2022 (AY21/22 Sem 2)
 */
public class Todo extends Task {
    /**
     * Create a Todo object.
     * 
     * @param s The description of what is due.
     */
    public Todo(String s) {
        super(s);
    }
    
    /**
     * Returns the Todo as a String.
     * 
     * @return Todo in the form of "[T][ ] Description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
} 