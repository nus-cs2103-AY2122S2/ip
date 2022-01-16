public class Todo  extends Action{

    public Todo(String s)  {
        super(s);
    }

    public Todo(String s, boolean bool)  {
        super(s, bool);
    }

    @Override
    public Action setDone()  {
        return new Todo(act, true);
    }

    @Override
    public Action setUnDone() {
        return new Todo(act, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
