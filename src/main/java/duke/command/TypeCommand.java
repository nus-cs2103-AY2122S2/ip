package duke.command;

/**
 * class for type of a command: Todo, Deadline, Event
 */
public class TypeCommand {
    public Command type;

    /**
     *
     * @return type of task (T, E or D)
     */
    public Command getType() {
        return type;
    }

    public void setType(Command type) {
        this.type = type;
    }
}