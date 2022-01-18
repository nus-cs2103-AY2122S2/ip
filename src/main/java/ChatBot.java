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

    public boolean runCommand(String command) {
        String [] input = command.split(" ", 2);
        switch (input[0].toLowerCase()) {
            case "bye":
                quit();
                return true;

            case "list":
                printTasks();
                break;

            case "mark":
                markTask(Integer.parseInt(input[1].trim()) - 1);
                break;

            case "unmark":
                unmarkTask(Integer.parseInt(input[1].trim()) - 1);
                break;

            case "todo":
                addTask(new ToDo(input[1]));
                break;

            case "deadline":
                String[] dDetail = input[1].split(" /by ");
                addTask(new Deadline(dDetail[0], dDetail[1]));
                break;

            case "event":
                String[] eDetail = input[1].split(" /at ");
                addTask(new Event(eDetail[0], eDetail[1]));
                break;

            default:
                System.out.println("Invalid input");
                break;
        }
        return false;
    }

    public void addTask(Task t) {
        tasks.add(t);
        System.out.printf("%s%n %s%n   %s%n %s%n%s%n",
                line, "Got it. I've added this task:",
                t.toString(), "Now you have " + tasks.size() + " tasks in the list",
                line);
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
            System.out.printf(" %d.%s%n", i + 1, tasks.get(i).toString());
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
