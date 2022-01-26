import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private final TaskList taskList;
    private static final String[] VALID_COMMAND =
            {"bye", "date", "deadline", "delete", "event",
                    "list", "mark", "unmark", "todo"};

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    private void doBye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    private void doList() {
        this.taskList.list();
    }

    private void doMark(String description) throws InvalidTaskIndexException {
        int position = Integer.parseInt(description);
        taskList.mark(position);
    }

    private void doUnmark(String description) throws InvalidTaskIndexException {
        int position = Integer.parseInt(description);
        taskList.unmark(position);
    }

    private void doDelete(String description) throws InvalidTaskIndexException {
        int position = Integer.parseInt(description);
        taskList.delete(position, true);
    }

    private void doTodo(String description) {
        taskList.add(new Todo(TaskType.TODO.getTaskType(), description), true);
    }

    private void doDeadline(String description, String date) {
        taskList.add(new Deadline(TaskType.DEADLINE.getTaskType(), description, date), true);
    }

    private void doEvent(String description, String date) {
        taskList.add(new Event(TaskType.EVENT.getTaskType(), description, date), true);
    }

    private void doDate(String description) throws IncompleteCommandException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd-MM-yyyy]" + "[dd/MM/yyyy]");
            LocalDate.parse(description, formatter);
            List<Task> list = findSpecificTasksByDate(description);
            if (list.size() > 0) {
                list.forEach(x -> System.out.println("\t" + x));
            } else {
                System.out.println("\tNo deadline/ event on this date");
            }
        } catch (DateTimeParseException e) {
            System.out.println("\tNot a valid date");
        }
    }

    private void doDefault() throws InvalidCommandException {
        throw new InvalidCommandException();
    }

    private String getValidCommand(String input) throws InvalidCommandException {
        String[] arr = input.split(" ");
        if (input.startsWith("bye") || input.startsWith("list") || input.startsWith("date")) {
            return arr[0];
        }
        if (arr.length < 1 || input.equals("") || !Arrays.asList(VALID_COMMAND).contains(arr[0])) {
            throw new InvalidCommandException();
        }
        return arr[0];
    }

    private String getDescription(String input, String command) throws IncompleteCommandException {
        if (!(command.equals("bye") || command.equals("list"))) {
            String[] arr = input.split(command + " ");
            if (arr.length < 2) {
                throw new IncompleteCommandException(command);
            }
            arr = arr[1].split(" /by | /at ");
            return arr[0];
        }
        return "";
    }

    private String getDate(String input, String command) throws IncompleteCommandException {
        if (command.equals("deadline") || command.equals("event")) {
            String[] arr = input.split(command + " ");
            arr = arr[1].split(" /by | /at ");
            if (arr.length < 2) {
                throw new IncompleteCommandException("'date' in " + command);
            } else {
                return arr[1];
            }
        } else {
            return "";
        }
    }

    private List<Task> findSpecificTasksByDate(String description) {
        List <Task> list = new ArrayList<>();
        for (Task task : taskList.getReminders()) {
            if (task.matchDate(description)) {
                list.add(task);
            }
        }
        return list;
    }

    public void run(String input) {
        try {
            String command = getValidCommand(input);
            String description = getDescription(input, command);
            String date = getDate(input, command);
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
                doDeadline(description, date);
                break;
            case "event":
                doEvent(description, date);
                break;
            case "date":
                doDate(description);
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