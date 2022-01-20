import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Ron\nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        String input = sc.nextLine();
        String bye = "bye";
        String list = "list";
        String mark = "mark";
        String unmark = "unmark";
        List<Task> store = new ArrayList<>();

        while (!input.equals(bye)) {
            System.out.println("____________________________________________________________");
            if (input.contains(unmark)) {
                System.out.println(input);
                int index = Integer.parseInt(input.substring(7)) - 1;
                System.out.println("No problem! The following task is marked as not done yet: ");
                store.get(index).toggleStatus();
                printTask(store.get(index));
            } else if (input.contains(mark)) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                System.out.println("Good job! The following task is marked as done: ");
                store.get(index).toggleStatus();
                printTask(store.get(index));
            } else if (input.equals(list)) {
                System.out.println("The tasks on your list are as follows: ");
                int i = 1;
                for (Task task : store) {
                    System.out.print(i + ".");
                    printTask(task);
                    i++;
                }
            } else {
                System.out.println("Task added!");
                if (input.contains("todo")) {
                    Task task = new Todo(input);
                    store.add(task);
                    System.out.println(task);
                } else if (input.contains("deadline")) {
                    Task task = new Deadline(input);
                    store.add(task);
                    System.out.println(task);
                } else if (input.contains("event")) {
                    Task task = new Event(input);
                    store.add(task);
                    System.out.println(task);
                }

                System.out.println("There are " + store.size() + " task(s) in the list.");
            }
            System.out.println("____________________________________________________________");
            input = sc.nextLine();
        }

        System.out.println("Bye. Stay safe and have a nice day!");
    }

    public static void printTask(Task task) {
        System.out.println(task);
    }
}
