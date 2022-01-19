import java.util.*;

public class DukeManager {

    protected ArrayList<Task> tasks;

    public DukeManager() {
        tasks = new ArrayList<>();
    }

    public void handle(String input) {
        String[] tokens = input.split(" ");
        System.out.println(tokens[0]);
        switch (tokens[0]) {
            case "list":
                list();
                return;
            case "mark":
                mark(tokens[1]);
                return;
            case "unmark":
                unMark(tokens[1]);
                return;
            case "todo":
                createTask(ToDo.createTask(tokens));
                break;
            case "deadline":
                createTask(Deadline.createTask(tokens));
                break;
            case "event":
                createTask(Event.createTask(tokens));
                break;

        }
    }

    protected void createTask(Task task) {
        tasks.add(task);
        echo("Got it. I've added this task:\n       " + task.toString() + "\n     Now you have " + tasks.size() + " tasks in the list.");
    }

    protected void mark(String input) {
        int index = Integer.parseInt(input) - 1;
        String output = "Nice! I've marked this task as done:\n       ";
        tasks.get(index).setCompleted(true);
        echo(output + tasks.get(index).toString());
    }

    protected void unMark(String input) {
        int index = Integer.parseInt(input) - 1;
        String output = "OK, I've marked this task as not done yet:\n       ";
        tasks.get(index).setCompleted(false);
        echo(output + tasks.get(index).toString());
    }

    protected void store(Task value) {
        tasks.add(value);
        String output = "Got it. I've added this task:\n       " + value.toString() + "\n     Now you have " + tasks.size() + " tasks in the list.";
        echo(output);
    }

    protected void list() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
        int count = 0;
        for (Task var : tasks) {
            count++;
            sb.append("\n     " + count + ". " + var.toString());
        }
        echo(sb.toString().trim());
    }

    public void greet() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
    }

    protected void echo(String input) {
        System.out.println("    ____________________________________________________________\n" +
                "     " + input + "\n" +
                "    ____________________________________________________________\n");
    }

    public void bye() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
