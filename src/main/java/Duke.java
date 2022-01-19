import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        greet();

        Scanner sc = new Scanner(System.in);

        loop: while (true) {
            String rawInput = sc.nextLine();
            String[] input = rawInput.split(" ", 2);
            String command = input[0];
            switch(command) {
                case "bye":
                    sayBye();
                    break loop;
                case "list":
                    list();
                    break;
                case "mark":
                    mark(Integer.parseInt(input[1]) - 1);
                    break;
                case "unmark":
                    unmark(Integer.parseInt(input[1]) - 1);
                    break;
                case "todo":
                    String todo = rawInput.split(" ", 2)[1];
                    addTodo(todo);
                    break;
                case "deadline": {
                    String[] parsedDeadline = input[1].split(" /by ");
                    String description = parsedDeadline[0];
                    String deadline = parsedDeadline[1];
                    addDeadline(description, deadline);
                    break;
                }
                case "event": {
                    String[] parsedEvent = input[1].split(" /at ");
                    String description = parsedEvent[0];
                    String time = parsedEvent[1];
                    addEvent(description, time);
                    break;
                }
                default:
                    break;
            }
        }
    }

    static void mark(int index) {
        Task task = tasks.get(index);
        task.mark();
        System.out.println("Nice! I've marked this task as done:\n\t" + task);
    }

    static void unmark(int index) {
        Task task = tasks.get(index);
        task.unmark();
        System.out.println("Ok, I've marked this task as not done yet:\n\t" + task);
    }

    static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        System.out.println("Got it. I've added this task:\n\t"
                + todo
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    static void addDeadline(String description, String dueDateTime) {
        Deadline deadline = new Deadline(description, dueDateTime);
        tasks.add(deadline);
        System.out.println("Got it. I've added this task:\n\t"
                + deadline
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    static void addEvent(String description, String time) {
        Event event = new Event(description, time);
        tasks.add(event);
        System.out.println("Got it. I've added this task:\n\t"
                + event
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }
}
