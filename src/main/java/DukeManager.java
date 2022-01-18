import java.util.*;

public class DukeManager {

    protected ArrayList<Task> tasks;

    public DukeManager() {
        tasks = new ArrayList<>();
    }

    public void handle(String input) {
        if (input.equals("list")) {
            list();
        } else {
            String[] tokens = input.split(" ");
            if (tokens[0].equals("mark")) {
                mark(tokens[1]);
            } else if (tokens[0].equals("unmark")) {
                unMark(tokens[1]);
            } else {
                Task newTask = new Task(input);
                store(newTask);
            }
        }
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
        echo("added: " + value.toString());
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
