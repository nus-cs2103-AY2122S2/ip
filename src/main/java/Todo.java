public class Todo  extends Action{
    public Todo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
