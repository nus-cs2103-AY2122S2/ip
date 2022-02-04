import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private static final String INDENT = "    ";
    private final List<Task> tasks;

    public TaskList(String path) {
        try {
            File file = new File(path);
            file.createNewFile();
            Scanner sc = new Scanner(file);
            sc.useDelimiter(" | ");
            tasks = new ArrayList<>();
            while (sc.hasNextLine()) {
                String taskType = sc.next();
                String isDoneSymbol = sc.next();
                String content = sc.next();
                boolean isDone = switch (isDoneSymbol) {
                    case "0" -> false;
                    case "1" -> true;
                    default -> throw new DukeException("I cannot read the file. It is not in the expected format.");
                };
                switch (taskType) {
                case "T":
                    tasks.add(new Todo(content, isDone));
                    break;
                case "D":
                    tasks.add(new Deadline(content, sc.next(), isDone));
                    break;
                case "E":
                    tasks.add(new Event(content, sc.next(), isDone));
                    break;
                default:
                    throw new DukeException("I cannot read the file. It is not in the expected format.");
                }
            }
        } catch (IOException e) {
            throw new DukeException("I cannot create the data file.");
        }
    }

    public void addTodo(String s) {
        Todo t = new Todo(s);
        tasks.add(t);
        this.printAdd();
    }

    public void addDdl(String s) {
        int i = s.indexOf(" /by ");
        if (i > 0 && i + 5 < s.length()) {
            Deadline t = new Deadline(s.substring(0, i), s.substring(i + 5));
            tasks.add(t);
            this.printAdd();
        } else {
            throw new DukeException("The description of a deadline should be \"<task> /by <time>\".");
        }
    }

    public void addEvt(String s) {
        int i = s.indexOf(" /at ");
        if (i > 0 && i + 5 < s.length()) {
            Event t = new Event(s.substring(0, i), s.substring(i + 5));
            tasks.add(t);
            this.printAdd();
        } else {
            throw new DukeException("The description of an event should be \"<task> /at <time>\".");
        }
    }

    private void printAdd() {
        System.out.println(INDENT + "Got it. I've added this task:");
        int n = tasks.size();
        System.out.println(INDENT + "  " + tasks.get(n - 1));
        System.out.println(INDENT + "Now you have " + n + " tasks in the list.");
    }

    public void list() {
        if (tasks.size() == 0) {
            System.out.println(INDENT + "You don't have tasks listed.");
        } else {
            System.out.println(INDENT + "Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print(INDENT);
                System.out.print(i + 1);
                System.out.println("." + tasks.get(i));
            }
        }
    }

    public void mark(int index, boolean done) {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Please enter a valid index.");
        }
        tasks.set(index, tasks.get(index).mark(done));
        if (done) {
            System.out.println(INDENT + "Nice! I've marked this task as done:");
        } else {
            System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        }
        System.out.println(INDENT + "  " + tasks.get(index));
    }

    public void delete(int index) {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Please enter a valid index.");
        }
        Task t = tasks.remove(index);
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + "  " + t);
        System.out.println(INDENT + "Now you have " + tasks.size() + " tasks in the list.");
    }

    public String fileUpdated() {
        String result = "";
        for (Task t : tasks) {
            result += t.fileFormat() + System.lineSeparator();
        }
        return result;
    }
}
