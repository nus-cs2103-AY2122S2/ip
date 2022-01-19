import java.util.*;

public class DukeManager {

    protected ArrayList<Task> tasks;

    public DukeManager() {
        tasks = new ArrayList<>();
    }

    public void handle(String input) throws DukeException {
        String[] tokens = input.split(" ");
        switch (tokens[0]) {
            case "list":
                list();
                return;
            case "mark":
                mark(tokens);
                return;
            case "unmark":
                unMark(tokens);
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
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");

        }
    }

    protected void createTask(Task task) {
        tasks.add(task);
        echo("Got it. I've added this task:\n       " + task.toString() + "\n     Now you have " + tasks.size() + " tasks in the list.");
    }

    protected void mark(String[] tokens) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (Exception exception) {
            throw new DukeException("Invalid input! Please enter the number of the task you want to mark.");
        }
        if(index >= tasks.size() || index < 0)
            throw new DukeException("Invalid input! This task number does not exist.");
        String output = "Nice! I've marked this task as done:\n       ";
        tasks.get(index).setCompleted(true);
        echo(output + tasks.get(index).toString());
    }

    protected void unMark(String[] tokens) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (Exception exception) {
            throw new DukeException("Invalid input! Please enter the number of the task you want to unmark.");
        }
        if(index >= tasks.size() || index < 0)
            throw new DukeException("Invalid input! This task number does not exist.");
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
                "    ____________________________________________________________");
    }

    public void echo(String input) {
        System.out.println("    ____________________________________________________________\n" +
                "     " + input + "\n" +
                "    ____________________________________________________________");
    }

    public void bye() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
