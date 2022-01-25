import java.util.Arrays;

public class Logic {
    private final Reminder reminder;
    private static final String[] VALID_COMMAND =
            {"bye", "deadline", "delete", "event",
            "list", "mark", "unmark", "todo"};

    public Logic(Reminder reminder) {
        this.reminder = reminder;
    }

    private void doBye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    private void doList() {
        this.reminder.list();
    }

    private void doMark(String description) throws InvalidTaskIndexException {
        int position = Integer.parseInt(description);
        reminder.mark(position);
    }

    private void doUnmark(String description) throws InvalidTaskIndexException {
        int position = Integer.parseInt(description);
        reminder.unmark(position);
    }

    private void doDelete(String description) throws InvalidTaskIndexException {
        int position = Integer.parseInt(description);
        reminder.delete(position);
    }

    private void doTodo(String description) {
        reminder.add(new Todo(description, TaskType.TODO.getTaskType()));
    }

    private void doDeadline(String description) {
        reminder.add(new Deadline(description, TaskType.DEADLINE.getTaskType()));
    }

    private void doEvent(String description) {
        reminder.add(new Event(description, TaskType.EVENT.getTaskType()));
    }

    private void doDefault() throws InvalidCommandException {
        throw new InvalidCommandException();
    }

    private String getCommand(String input) throws InvalidCommandException {
        String[] arr = input.split(" ");
        if (arr.length < 1 || input.equals("") || !Arrays.asList(VALID_COMMAND).contains(arr[0])) {
            throw new InvalidCommandException();
        }
        return arr[0];
    }

    private String getDescription(String input, String command) throws IncompleteCommandException {
        if (input.startsWith("bye") || input.startsWith("list")) {
            return "";
        }
        if (input.split(command + " ").length < 2) {
            throw new IncompleteCommandException(command);
        }
        if (command.equals("todo")) {
            return input.split("todo ")[1];
        } else if (command.equals("deadline") || command.equals("event")) {
            String[] arr = input.split(" /by | /at ");
            if (arr.length < 2) {
                throw new IncompleteCommandException("'date' in " + command);
            } else {
                boolean isDeadline = command.equals("deadline");
                return isDeadline ? (arr[0].substring(9) + " (by: " + arr[1] + ")") :
                        arr[0].substring(6) + " (at: " + arr[1] + ")";
            }
        } else {
            return input.split(command + " ")[1];
        }
    }

    public void run(String input) {
        try {
            String command = getCommand(input);
            String description = getDescription(input, command);
            switch (command) {
            case "bye":
                doBye();
                break;
            case "mark":
                doMark(description);
                break;
            case "unmark":
                doUnmark(description);
                break;
            case "delete":
                doDelete(description);
                break;
            case "list":
                doList();
                break;
            case "todo":
                doTodo(description);
                break;
            case "deadline":
                doDeadline(description);
                break;
            case "event":
                doEvent(description);
                break;
            default:
                doDefault();
                break;
            }
        } catch (DazzException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\tOOPS!!! The index you have provided is invalid!");
        }

    }
}
