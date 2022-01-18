import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Introduction
        String introduction = "____________________________________________________________\nHello! I'm Duke\nWhat can I do for you?\n____________________________________________________________";
        System.out.println(introduction);

        // Scanner for new input
        Scanner sc = new Scanner(System.in);

        // ToDoList for the program
        Calendar calendar = new Calendar();

        while (true) {
            String command = sc.next();
            String input = sc.nextLine();
            command = command.trim(); //remove whitespace at back of input
            input = input.trim(); // remove whitespace at front of input

            switch(command) {
                case "bye":
                    output("Bye! Hope to see you again soon.");
                    return;
                case "list":
                    output(calendar.toString());
                    continue;
                case "todo":
                    Todo newTodo = new Todo(input);
                    calendar.add(newTodo);
                    taskAdded(newTodo.toString(), calendar.size());
                    continue;
                case "deadline":
                    String[] processedDeadline = input.split("/", 2);
                    Deadline newDeadline = new Deadline(processedDeadline[0], processedDeadline[1]);
                    calendar.add(newDeadline);
                    taskAdded(newDeadline.toString(), calendar.size());
                    continue;
                case "event":
                    String[] processedEvent = input.split("/", 2);
                    Event newEvent = new Event(processedEvent[0], processedEvent[1]);
                    calendar.add(newEvent);
                    taskAdded(newEvent.toString(), calendar.size());
                    continue;
                default:
                    output("OOPS!! I'm sorry, I don't know what that means");
                    continue;
            }
        }
    }

    static void output(String message) {
        System.out.println(String.format("____________________________________________________________\n%s\n____________________________________________________________", message));
    }

    static void taskAdded(String task, int size) {
        output(String.format("Got it. I've added this task:\n   %s\nNow you have %s tasks in the list.", task, size));
    }
}
