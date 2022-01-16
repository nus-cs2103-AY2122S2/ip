public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    /*
     * Customized toString method for Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
