package duke.command;

/**
 * class for task description in a command
 */
public class DescriptionCommand extends TypeCommand {
    private String description;

    /**
     *
     * @return description of a task
     */
    public String getDescription() {
        return description;
    }


    /**
     * Set the description of a task
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
