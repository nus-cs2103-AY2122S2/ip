package duke.command;

/**
 * class for type of a command: Todo, Deadline, Event
 */
public class TypeCommand {
    public Command type;

    public Command getType() {
        return type;
    }

    public void setType(Command type) {
        this.type = type;
    }
}