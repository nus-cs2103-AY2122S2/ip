package duke.task;

import duke.exception.InvalidArgumentException;

import java.util.Arrays;
import java.util.List;

public class Todo extends Task {

    /**
     * Constructs Todo class.
     * Todo constructor accepts the description of the task.
     * @param name Description of the task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructs a Todo object as a factory constructor.
     * @param description Description of the todo task in array format.
     * @return the Todo object.
     * @throws InvalidArgumentException If there is no description.
     */
    public static Todo of (String[] description) throws InvalidArgumentException {
        return Todo.of(Arrays.asList(description));
    }

    /**
     * Constructs a Todo object as a factory constructor.
     * @param description Description of the todo task in List format.
     * @return the Todo object.
     * @throws InvalidArgumentException If there is no description.
     */
    public static Todo of(List<String> description) throws InvalidArgumentException {
        if(description.size() == 1) {
            throw new InvalidArgumentException();
        }
        String name = String.join(" ", description.subList(1, description.size()));
        return new Todo(name);
    }

    /**
     * Returns text representing the todo task.
     * This method is used for storing todo task.
     * @return Task in text format.
     */
    @Override
    public String toStorageString() {
        String status = getStatus()? "X" : ".";
        return String.format(status + " todo " + getName());
    }

    /**
     * Returns text representing the todo task for User.
     * @return Task in text format.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
