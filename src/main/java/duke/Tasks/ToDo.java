package duke.Tasks;

/**
 * To Do is a subclass of Task
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super("T", description);
    }

    /**
     * Generates the String to be stored in the database
     * @return String text formatted for database
     */
    @Override
    public String dBText() {
        String complete = this.getCompleted() ? "1" : "0";
        return String.format("T|%s|%s", complete, this.getDescription());
    }
}
