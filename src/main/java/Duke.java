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
            } else {
                toDoList.add(new ToDoItem(str));
                System.out.println("added: " + str);
            }
            System.out.println("____________________________________________________________");
        }
    }
}

class ToDoItem {
    String task;

    ToDoItem(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return this.task.toString();
    }
}

class ToDoList {
    ArrayList<ToDoItem> ls = new ArrayList<>();

    public void add(ToDoItem task) {
        ls.add(task);
    }

    @Override
    public String toString() {
        int counter = 1;
        String output = "";
        for (ToDoItem task : ls) {
            output += counter + ". " + task + "\n";
            counter += 1;
        }
        return output.substring(0,output.length() - 1);
    }
}
