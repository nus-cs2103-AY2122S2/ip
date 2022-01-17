import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> taskList;
    private Scanner scanner;

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    public void addTask(String input) {
        Task newTask = new Task(input);
        System.out.println("____________________________________________________________");
        System.out.printf("added: %s%n", input);
        System.out.println("____________________________________________________________");
        taskList.add(newTask);
    }

    public void completeTask(int taskIndex) {
        Task foundTask = taskList.get(taskIndex - 1);
        foundTask.markDone();
    }

    public void undoTask(int taskIndex) {
        Task foundTask = taskList.get(taskIndex - 1);
        foundTask.markUndone();
    }

    public void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.printf("%d. %s%n", i, taskList.get(i - 1));
        }
        System.out.println("____________________________________________________________");
    }

    public void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public void start() {
        this.greet();
        this.taskList = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        String input = "";
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                this.listTasks();
            } else if (input.startsWith("mark")) {
                String[] string = input.split(" ");
                int index = Integer.parseInt(string[1]);
                completeTask(index);
            } else if (input.startsWith("unmark")) {
                String[] string = input.split(" ");
                int index = Integer.parseInt(string[1]);
                undoTask(index);
            } else {
                this.addTask(input);
            }
        }
        this.exit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
