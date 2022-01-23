import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class Parser {
    public Command parse(String input) throws DukeException {
        String[] tokens = input.split(" ");
        switch (tokens[0]) {
            case "list":
                return list();
            case "mark":
                return mark(tokens, true);
            case "unmark":
                return mark(tokens, false);
            case "delete":
                return delete(tokens);
            case "todo":
                return store(ToDo.createTask(tokens));
            case "deadline":
                return store(Deadline.createTask(tokens));
            case "event":
                return store(Event.createTask(tokens));
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    protected DeleteCommand delete(String[] tokens) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (Exception exception) {
            throw new DukeException("Invalid input! Please enter the number of the task you want to delete.");
        }

        return new DeleteCommand(index);
    }

    protected StoreCommand store(Task task) {
        return new StoreCommand(task);
    }

    protected MarkCommand mark(String[] tokens, boolean isMark) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (Exception exception) {
            throw new DukeException("Invalid input! Please enter the number of the task you want to mark.");
        }
        return new MarkCommand(isMark, index);
    }

    protected ListCommand list() {
        return new ListCommand();
    }
}
