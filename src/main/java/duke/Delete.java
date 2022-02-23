package duke;

/**
 * delete the tasks from list
 * inherit from task
 */
public class Delete extends Task {

    /**
     * @param description
     */
    public Delete(String description) {
        super(description);

    }

    @Override
    public String message() {
        String message = "Noted. I've deleted this task:\n" + super.message();
        return message;
    }
}

