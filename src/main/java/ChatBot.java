import java.util.ArrayList;

public class ChatBot {
    private String name;
    private String line = "-------------------------------------------------";
    private ArrayList<String> tasks = new ArrayList<String>();

    public ChatBot(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.printf("%s%n %s%s%n %s%n%s%n", line, "Hello! I'm ", name, "What can I do for you", line);
    }

    public void addTask(String task) {
        tasks.add(task);
        System.out.printf("%s%n added: %s%n%s%n", line, task, line);
    }

    public void printTasks() {
        System.out.println(line);
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.printf(" %d. %s%n", i + 1, tasks.get(i));
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
