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
            String[] tempList = text.split(" ", 2);

            System.out.println("------------------------------------------------------------");
            if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (text.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks; i++) {
                    System.out.println((i + 1) + "." + list[i].toString());
                }
            } else if (tempList[0].equals("mark")) {
                list[Integer.parseInt(tempList[1]) - 1].markAsDone();
                System.out.println(list[Integer.parseInt(tempList[1]) - 1].toString());
            } else if (tempList[0].equals("unmark")) {
                list[Integer.parseInt(tempList[1]) - 1].markAsNotDone();
                System.out.println(list[Integer.parseInt(tempList[1]) - 1].toString());
            } else {
                System.out.println("Got it. I've added this task:");
                if (tempList[0].equals("todo")) {
                    list[tasks] = new Todo(tempList[1]);
                    System.out.println(list[tasks].toString());
                } else if (tempList[0].equals("deadline")){
                    String[] restOfPara  = tempList[1].split("/by ", 2);
                    list[tasks] = new Deadline(restOfPara[0], restOfPara[1]);
                    System.out.println(list[tasks].toString());
                } else if (tempList[0].equals("event")){
                    String[] restOfPara  = tempList[1].split("/at ", 2);
                    list[tasks] = new Event(restOfPara[0], restOfPara[1]);
                    System.out.println(list[tasks].toString());
                }
                tasks++;
                if (tasks == 1) {
                    System.out.println("Now you have " + tasks + " task in the list.");
                } else {
                    System.out.println("Now you have " + tasks + " tasks in the list.");
                }
            }
            System.out.println("------------------------------------------------------------");
        }

    }
}

class Task {
    protected String description;
    protected String task;
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
        System.out.println("Nice! I've marked this task as done:");
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
    }

    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}

class Event extends Task {
    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(by: " + by + ")";
    }
}
