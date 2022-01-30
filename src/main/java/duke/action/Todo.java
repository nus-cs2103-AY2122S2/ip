package duke.action;

/**
 * An extension from Action.
 */
public class Todo  extends Action {

    /**
     * Constructs a new Todo object with a String variable s.
     * @param s task todo
     */
    public Todo(String s)  {
        super(s);
    }

    /**
     * Constructs a new Todo object with a String variable s,
     * and a boolean variable, bool.
     * @param s task todo
     * @param bool done status of task todo
     */
    public Todo(String s, boolean bool)  {
        super(s, bool);
    }

    /**
     * Returns a new Todo object with same variable values except
     * isDone which is now true
     * @return marked Todo object
     */
    @Override
    public Action setDone()  {
        return new Todo(act, true);
    }

    /**
     * Returns a new Todo object with same variable values except
     * isDone which is now false
     * @return unmarked Todo object
     */
    @Override
    public Action setUnDone() {
        return new Todo(act, false);
    }

    /**
     * Returns a string representation of the Todo object.
     * @return string representation
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}