import java.util.ArrayList;

public class ChatBot {
    private String name;
    private String line = "-------------------------------------------------";
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public ChatBot(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.printf("%s%n %s%s%n %s%n%s%n", line, "Hello! I'm ", name, "What can I do for you", line);
    }

    public void addTask(String description) {
        tasks.add(new Task(description));
        System.out.printf("%s%n added: %s%n%s%n", line, description, line);
    }

    public void markTask(int index) {
        tasks.get(index).setDone(true);
        System.out.printf("%s%n %s%n    %s%n%s%n", line, "Nice! I've marked this task as done:",
                tasks.get(index).toString(), line);
    }

    public void unmarkTask(int index) {
        tasks.get(index).setDone(false);
        System.out.printf("%s%n %s%n    %s%n%s%n", line, "Nice! I've marked this task as not done yet:",
                tasks.get(index).toString(), line);
    }

    public void printTasks() {
        String title = tasks.isEmpty() ? "You got no task now! Start by adding new tasks."
                : "Here are the tasks in your list:";
        System.out.println(line);
        System.out.println(title);
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.printf(" %d.%s%n", i + 1, tasks.get(i));
        }
        System.out.println(line);
    }

    public void echo(String input) {
        System.out.printf("%s%n %s%n%s%n", line, input, line);
    }

    public void quit() {
        System.out.printf("%s%n %s%n%s%n", line, "Bye. Hope to see you again soon!", line);
    }
}
