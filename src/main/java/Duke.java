import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        greet();

        Scanner sc = new Scanner(System.in);
        String commandLine = "";

        while (!commandLine.equals("bye")) {
            System.out.print("Enter command: \t");
            commandLine = sc.nextLine().trim();

            String[] commandLineParts = commandLine.split(" ", 2);

            int taskNum;
            Task task;

            switch (commandLineParts[0].toLowerCase()) {
            case "bye":
                exit();
                break;
            case "list":
                listTasks();
                break;
            case "mark":
                taskNum = Integer.parseInt(commandLineParts[1]);
                markTask(taskNum);
                break;
            case "unmark":
                taskNum = Integer.parseInt(commandLineParts[1]);
                unmarkTask(taskNum);
                break;
            default:
                task = new Task(commandLine);
                addTask(task);
                break;
            }
        }
    }

    public void greet() {
        String logo = "\t" + " ____        _        " + System.lineSeparator()
                + "\t" + "|  _ \\ _   _| | _____" + System.lineSeparator()
                + "\t" + "| | | | | | | |/ / _" + System.lineSeparator()
                + "\t" + "| |_| | |_| |   <  __/" + System.lineSeparator()
                + "\t" + "|____/ \\__,_|_|\\_\\___|" + System.lineSeparator();

        String welcomeMessage = "\t" + "Hello! I'm Duke, your Personal Assistant ChatBot."
                /* + System.lineSeparator()
                + logo */
                + System.lineSeparator()
                + "\t" + "What can I do for you?";

        printResponse(welcomeMessage);
    }

    public void exit() {
        String exitMessage = "\t" + "Bye. Hope to see you again soon!";
        printResponse(exitMessage);
    }

    public void echo(String message) {
        printResponse(message);
    }

    public void printResponse(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public void printLine() {
        String horizontalLine = "\t" + "________________________________________________________";
        System.out.println(horizontalLine);
    }

    public void addTask(Task task) {
        tasks.add(task);
        echo("\t" + "added: " + task.getDescription());
    }

    public void listTasks() {
        StringBuilder sb = new StringBuilder();

        sb.append("\t").append("Here are the tasks in your list:").append(System.lineSeparator());

        for (int i = 0; i < tasks.size(); i++) {
            if (i != 0) {
                sb.append(System.lineSeparator());
            }

            sb.append("\t").append(i + 1).append(". ").append(tasks.get(i));
        }

        echo(sb.toString());
    }

    public void markTask(int taskNum) {
        Task task = tasks.get(taskNum - 1);
        task.setDone();

        String message = "\t" + "Nice! I've marked this task as done:"
                + System.lineSeparator() + "\t\t" + task;
        echo(message);
    }

    public void unmarkTask(int taskNum) {
        Task task = tasks.get(taskNum - 1);
        task.setNotDone();

        String message = "\t" + "OK, I've marked this task as not done yet:"
                + System.lineSeparator() + "\t\t" + task;
        echo(message);
    }
}
