package duke;

/**
 * todo class
 * inherit from Task class
 */
public class Todos extends Task {

    /**
     * @param description
     */
    public Todos(String description) {
        super(description);

    }

    /**
     *
     * @return string to be printed
     */
    @Override
    public String message() {
        return "T | " + "[" + this.getStatusIcon() + "] " + super.message();
    }



}
