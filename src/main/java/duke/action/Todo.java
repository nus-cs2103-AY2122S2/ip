package duke.action;

public class Todo  extends Action {

    public Todo(String s)  {
        super(s);
    }

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

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}