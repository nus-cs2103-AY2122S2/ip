public class ToDo extends Task {
    public ToDo(int taskId, String name) {
        super(taskId, name);

        System.out.println("Got it. I've added this todo: ");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
