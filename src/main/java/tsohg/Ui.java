package tsohg;

/**
 * The class handling UI and IO from users.
 */
public class Ui {

    private final TaskList tasks;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public String takeInput(String input) throws TsohgException {
        String[] splitInput = input.split("\\s+", 2);
        String command = splitInput[0];
        String argument = splitInput.length == 2 ? splitInput[1] : null;
        switch (command) {
        case "bye":
            return "Seeeee youuuu sooon...";
        case "list":
            return list();
        case "delete":
            return delete(argument);
        case "mark":
            return mark(argument);
        case "unmark":
            return unmark(argument);
        case "todo":
            return todo(argument);
        case "deadline":
            return deadline(argument);
        case "event":
            return event(argument);
        case "find":
            return find(argument);
        default:
            throw new TsohgException("OOPS!!! I'm sorry,\n but I don't know what that means :-(");
        }
    }

    private String list() {
        return "Here are the tasks in your list:\n"
                + tasks.toString();
    }

    private String delete(String argument) throws TsohgException {
        int index = parseIndex(argument);
        return "Noted. I've removed this task:\n"
                + tasks.deleteItem(index) + "\n"
                + tasks.listCount();
    }

    private String mark(String argument) throws TsohgException {
        int index = parseIndex(argument);
        return "Nice! I've marked this task as done:\n"
                + tasks.markItem(index);
    }

    private String unmark(String argument) throws TsohgException {
        int index = parseIndex(argument);
        return "OK, I've marked this task as not done yet:\n"
                + tasks.unmarkItem(index);
    }

    private String todo(String argument) throws TsohgException {
        if (argument == null) {
            throw new TsohgException("OOPS!!! The description of a todo cannot be empty.");
        }
        return "Got it. I've added this task:\n"
                + tasks.addTodo(argument) + "\n"
                + tasks.listCount();
    }

    private String deadline(String argument) throws TsohgException {
        String[] split = argument.split(" /by ", 2);
        if (split.length != 2) {
            throw new TsohgException("Date is not provided!");
        }
        String name = split[0];
        String date = split[1];
        return "Got it. I've added this task:\n"
                + tasks.addDeadline(name, date) + "\n"
                + tasks.listCount();
    }

    private String event(String argument) throws TsohgException {
        String[] split = argument.split(" /at ", 2);
        if (split.length != 2) {
            throw new TsohgException("Date is not provided!");
        }
        String name = split[0];
        String date = split[1];
        return "Got it. I've added this task:\n"
                + tasks.addEvent(name, date) + "\n"
                + tasks.listCount();
    }

    private String find(String argument) throws TsohgException {
        if (argument == null) {
            throw new TsohgException("OOPS!!! The description of find cannot be empty.");
        }
        return "Here are the matching tasks in your list:\n"
                + tasks.find(argument);
    }

    private int parseIndex(String argument) throws TsohgException {
        try {
            int index = Integer.parseInt(argument) - 1;
            if (index >= tasks.size()) {
                throw new TsohgException("Index must smaller than\nthe size of the list.");
            }
            if (0 > index) {
                throw new TsohgException("Index must be greater than zero.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new TsohgException("Index must be a valid number.");
        }
    }
}
