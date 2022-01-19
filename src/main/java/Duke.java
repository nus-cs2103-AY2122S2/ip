import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String lines = "____________________________________________________________";

        System.out.println(lines);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(lines);

        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int counter = 0;

        while (true) {
            String input = scanner.nextLine();
            String[] split = input.split(" ");
            if (input.equals("bye")) {
                System.out.println(lines + "\nBye. Hope to see you again soon!\n" + lines);
                break;
            }
            else if (input.equals("list")) {
                System.out.println(lines);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0;i < counter;i++) {
                    System.out.println(Integer.toString(i + 1) +
                            ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
                System.out.println(lines);

            }
            else if (split[0].equals("mark")) {
                int number = Integer.parseInt(split[1]) - 1;
                if (number < counter) {
                    tasks[number].markAsDone();
                    System.out.println(lines + "\nNice! I've marked this task as done:\n" +
                            "[X] " + tasks[number].getDescription() + "\n" + lines);
                }
                else {
                    System.out.println(lines + "\nNo such task exists. \n" + lines);
                }
            }
            else if (split[0].equals("unmark")) {
                int number = Integer.parseInt(split[1]) - 1;
                if (number < counter) {
                    tasks[number].markAsNotDone();
                    System.out.println(lines + "\nOk, I've marked this task as not done yet:\n" +
                            "[ ] " + tasks[number].getDescription() + "\n" + lines);
                }
                else {
                    System.out.println(lines + "\nNo such task exists. \n" + lines);
                }
            }
            else {
                System.out.println(lines + "\nadded: " + input + "\n" + lines);
                tasks[counter++] = new Task(input);
            }
        }
    }
}

class Task {
    String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }
}