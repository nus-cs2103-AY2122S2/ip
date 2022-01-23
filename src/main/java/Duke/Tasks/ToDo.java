package Duke.Tasks;

import Duke.Exception.DukeException;

public class ToDo extends Task {
    public ToDo(String item) {
        super(item);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static ToDo createTask(String[] tokens) throws DukeException {
        String item = "";
        if (tokens.length == 1)
            throw new DukeException("The description of a todo task cannot be empty!");

        for (String token : tokens) {
            if (token.equals("todo")) {
                continue;
            } else {
                item += token + " ";
            }
        }
        return new ToDo(item.trim());
    }
}
