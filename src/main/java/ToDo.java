public class ToDo extends Task {
    /**
     * Constructs a ToDo class for a general Task
     * @param description String
     * @param id int, the task number
     */
    ToDo(String description, int id) {
        super(description, id);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
