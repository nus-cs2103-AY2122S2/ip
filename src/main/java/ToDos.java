public class ToDos extends Task{
    public ToDos(String objective) {
        super(objective);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
