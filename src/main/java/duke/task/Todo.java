package duke.task;

import static duke.constant.Message.CLOSE_BRACKET;
import static duke.constant.Message.OPEN_BRACKET;
import static duke.constant.TaskConstant.PREFIX_TODO;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return OPEN_BRACKET + PREFIX_TODO + CLOSE_BRACKET + super.toString();
    }
}
