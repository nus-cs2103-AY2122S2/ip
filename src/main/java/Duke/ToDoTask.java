package Duke;

class ToDoTask extends Task{
    public ToDoTask(String description, boolean isCompleted){
        super(description, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]"+super.toString();
    }

    /**
     * Generates ToDo string to be stored in the file.
     */
    // T | 1 | read book
    @Override
    public String toFileString(){
        return "T" + super.toFileString();
    }
}