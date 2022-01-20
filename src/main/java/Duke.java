import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> database = new ArrayList<>();
        String line = "_______________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = line + "\nHello! I'm YQ\n" + "What can I do for you?\n" + logo + line;
        System.out.println(intro);

        Scanner sc = new Scanner(System.in);
        Task input = new Task(sc.nextLine());
        while (!input.description.equals("bye")) {
            System.out.println(line);
            if (input.description.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < database.size(); i++) {
                    System.out.println(i + 1 + "." + database.get(i));
                }
            } else if (input.description.startsWith("mark")) {
                String itemNumber = input.description.substring(5);
                int num = Integer.parseInt(itemNumber) - 1;
                Task taskToMark = database.get(num);
                if (!taskToMark.getStatusIcon().equals("X")) {
                    taskToMark.markAsDone();
                    database.set(num, taskToMark);
                }
                System.out.println("Nice! I've marked this task as done:\n  " + taskToMark);
            } else if (input.description.startsWith("unmark")) {
                String itemNumber = input.description.substring(7);
                int num = Integer.parseInt(itemNumber) - 1;
                Task taskToUnmark = database.get(num);
                if (taskToUnmark.getStatusIcon().equals("X")) {
                    taskToUnmark.markAsUndone();
                    database.set(num, taskToUnmark);
                }
                System.out.println("OK, I've marked this task as not done yet:\n  " + taskToUnmark);
            } else if (input.description.startsWith("todo")) {
                try{
                    String description = input.description.substring(5);
                    Todo todoItem = new Todo(description);
                    database.add(todoItem);
                    System.out.println("Got it. I've added this task:\n  " + todoItem);
                    System.out.println("Now you have " + database.size() + " tasks in the list.");
                }catch(StringIndexOutOfBoundsException e){
                    DukeException error = new DukeException(e);
                    System.out.println(error);
                }
            } else if (input.description.startsWith("deadline")) {
                String[] itemArr = input.description.substring(9).split(" /by ");
                Deadline deadlineItem = new Deadline(itemArr[0], itemArr[1]);
                database.add(deadlineItem);
                System.out.println("Got it. I've added this task:\n  " + deadlineItem);
                System.out.println("Now you have " + database.size() + " tasks in the list.");
            } else if (input.description.startsWith("event")) {
                String[] itemArr = input.description.substring(6).split(" /at ");
                Event eventItem = new Event(itemArr[0], itemArr[1]);
                database.add(eventItem);
                System.out.println("Got it. I've added this task:\n  " + eventItem);
                System.out.println("Now you have " + database.size() + " tasks in the list.");
            } else if (input.description.startsWith("delete")) {
                String itemNumber = input.description.substring(7);
                int num = Integer.parseInt(itemNumber) - 1;
                Task itemToDelete = database.get(num);
                database.remove(num);
                System.out.println("Noted. I've removed this task:\n" + itemToDelete);
                System.out.println("Now you have " + database.size() + " tasks in the list.");
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println(line);
            input = new Task(sc.nextLine());
        }
        System.out.println(line + "\n" + "Bye. Hope to see you again soon!" + "\n" + line);
    }
}
