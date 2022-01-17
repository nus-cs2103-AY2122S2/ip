import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    ArrayList<Task> tasks = new ArrayList<>();

    private void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }

    private void mark(int taskNumber) {
        tasks.get(taskNumber - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNumber - 1));
    }

    private void unmark(int taskNumber) {
        tasks.get(taskNumber - 1).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNumber - 1));
    }

    private void addToDo(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n"
                + task + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size()));
    }

    private void addDeadline(Deadline deadline) {
        tasks.add(deadline);
        System.out.println("Got it. I've added this task:\n"
                + deadline + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size()));
    }

    private void addEvent(Event event) {
        tasks.add(event);
        System.out.println("Got it. I've added this task:\n"
                + event + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size()));
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String intro = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(intro);

        String cmd = sc.next();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                listTasks();

            } else if (cmd.equals("mark")) {
                int taskNumber = sc.nextInt();
                mark(taskNumber);

            } else if (cmd.equals("unmark")) {
                int taskNumber = sc.nextInt();
                unmark(taskNumber);

            } else if (cmd.equals("todo")){
                ToDo todo = new ToDo(sc.nextLine());
                addToDo(todo);

            } else if (cmd.equals("deadline")) {
                String info = sc.nextLine();
                String[] split = info.split("/by ");
                String description = split[0];
                String time = split[1];
                Deadline deadline = new Deadline(description, time);
                addDeadline(deadline);

            } else if (cmd.equals("event")) {
                String info = sc.nextLine();
                String[] split = info.split("/at ");
                String description = split[0];
                String time = split[1];
                Event event = new Event(description, time);
                addEvent(event);
            }
            cmd = sc.next();

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
