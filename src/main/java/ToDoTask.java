public class ToDoTask extends Task{
    public ToDoTask(String description, boolean isCompleted){
        super(description, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]"+super.toString();
    }

}
