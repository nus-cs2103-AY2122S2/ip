import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello! I'm Duke\nWhat can i do for you?\n");

        Scanner input = new Scanner(System.in);
        String text = "";
        Task[] list = new Task[100];
        int tasks = 0;

        while (!text.equals("bye")) {
            text = input.nextLine();
            String[] tempList = text.split(" ");

            System.out.println("------------------------------------------------------------");
            if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (tempList[0].equals("mark")) {
                list[Integer.parseInt(tempList[1]) - 1].markAsDone();
            } else if (tempList[0].equals("unmark")) {
                list[Integer.parseInt(tempList[1]) - 1].markAsNotDone();
            } else if (text.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks; i++) {
                    System.out.println((i + 1) + "." + list[i].getStatusIcon() + " " + list[i].description);
                }
            } else {
                list[tasks] = new Task(text);
                tasks++;
                System.out.println("added: " + text);
            }
            System.out.println("------------------------------------------------------------");
        }

    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(getStatusIcon() + " " + description);
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(getStatusIcon() + " " + description);
    }
}
