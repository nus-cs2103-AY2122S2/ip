public class Deadline extends Action{
    private final String by;

    public Deadline(String task, String by)  {
        super(task);
        this.by  = by;
    }

    public Deadline(String task, String by, boolean bool)  {
        super(task, bool);
        this.by = by;
    }

    @Override
    public Action setDone()  {
        return new Deadline(act, by, true);
    }

    @Override
    public Action setUnDone()  {
        return new Deadline(act, by, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}