package duke.task;

import static duke.constant.Message.CLOSE_BRACKET;
import static duke.constant.Message.OPEN_BRACKET;
import static duke.constant.TaskConstant.PREFIX_TODO;

/**
 * A class represents for a todo.
 */
public class Todo extends Task {
    /**
     * Class constructor with the description and the date time
     * Creates an undone Todo.
     *
     * @param description Todo description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides toString method to make a string including prefix, status icon, description.
     * @return String representation Todo
     */
    @Override
    public String toString() {
        return OPEN_BRACKET + PREFIX_TODO + CLOSE_BRACKET + super.toString();
    }
}
