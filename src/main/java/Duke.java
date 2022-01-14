import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();
        String logo = "███████╗ █████╗ ██╗████████╗ █████╗ ███╗   ███╗ █████╗\n" +
                "██╔════╝██╔══██╗██║╚══██╔══╝██╔══██╗████╗ ████║██╔══██╗\n" +
                "███████╗███████║██║   ██║   ███████║██╔████╔██║███████║\n" +
                "╚════██║██╔══██║██║   ██║   ██╔══██║██║╚██╔╝██║██╔══██║\n" +
                "███████║██║  ██║██║   ██║   ██║  ██║██║ ╚═╝ ██║██║  ██║\n";
        System.out.println(logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Saitama");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String str = sc.nextLine();
            System.out.println("____________________________________________________________");
            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (str.equals("list")) {
                System.out.println(toDoList);
            } else if (str.length() > 4 && str.substring(0,4).equals("mark")) {
                ToDoItem task = toDoList.get(Integer.parseInt(str.substring(5)));
                task.markAsDone();
            } else if (str.length() > 6 && str.substring(0,6).equals("unmark")) {
                ToDoItem task = toDoList.get(Integer.parseInt(str.substring(7)));
                task.markAsUndone();
            }
            else {
                toDoList.add(new ToDoItem(str));
                System.out.println("added: " + str);
            }
            System.out.println("____________________________________________________________");
        }
    }
}

class ToDoItem {
    protected String task;
    protected boolean isDone;

    ToDoItem(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[X] " + this.task);
    }

    public void markAsUndone() {
        isDone = false;
        System.out.println(" OK, I've marked this task as not done yet: ");
        System.out.println("[ ] " + this.task);
    }

    @Override
    public String toString() {
        return this.task.toString();
    }
}

class ToDoList {
    protected ArrayList<ToDoItem> ls = new ArrayList<>();

    public void add(ToDoItem task) {
        ls.add(task);
    }

    public ToDoItem get(int item) {
        return ls.get(item - 1);
    }

    @Override
    public String toString() {
        int counter = 1;
        String output = "Here are the tasks in your list:\n";
        for (ToDoItem task : ls) {
            output += counter + "." + "[" + task.getStatusIcon() + "] " + task + "\n";
            counter += 1;
        }
        return output.substring(0,output.length() - 1);
    }
}
