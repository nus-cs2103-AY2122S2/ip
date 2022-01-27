package duke.task;

import duke.exception.InvalidArgumentException;

import java.util.Arrays;
import java.util.List;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public static Todo of (String[] description) throws InvalidArgumentException {
        return Todo.of(Arrays.asList(description));
    }

    public static Todo of(List<String> description) throws InvalidArgumentException {
        if(description.size() == 1) {
            throw new InvalidArgumentException();
        }
        String name = String.join(" ", description.subList(1, description.size()));
        return new Todo(name);
    }

    @Override
    public String toStorageString() {
        String status = getStatus()? "X" : ".";
        return String.format(status + " todo " + getName());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
