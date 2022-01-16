public class Event extends Action {
    private final String at;
    public Event(String task, String at) {
        super(task);
        this.at = at;
    }

    public Event(String task, String at, boolean bool){
        super(task, bool);
        this.at = at;
    }

    @Override
    public Action setDone()  {
        return new Event(act, at, true);
    }

    @Override
    public Action setUnDone()  {
        return new Event(act, at, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
