import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final String INDENT = "    ";
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void readFromFile(Task task) {
        tasks.add(task);
    }

    public void addTodo(String s) {
        Todo t = new Todo(s);
        tasks.add(t);
        this.printAdd();
    }

    public void addDdl(String s) {
        int i = s.indexOf(" /by ");
        if (i > 0 && i + 5 < s.length()) {
            Deadline t = new Deadline(s.substring(0, i), LocalDate.parse(s.substring(i + 5)));
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
        StringBuilder fileContent = new StringBuilder();
        for (Task t : tasks) {
            fileContent.append(t.fileFormat());
        }
        return fileContent.toString();
    }
}
