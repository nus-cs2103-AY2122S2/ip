import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm Duke!");
        System.out.println("What can I do for you?\n");

        Tasklist tlist = new Tasklist();
        Scanner scn = new Scanner(System.in);
        while (scn.hasNextLine()) {
            String input = scn.nextLine();
            String[] inputArr = input.split(" ");
            String cmd = inputArr[0];
            if (cmd.equals(Handlers.BYE.label)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.equals(Handlers.LIST.label)) {
                System.out.println("Fetching all records...");
                System.out.println(tlist.toString());
            } else if (cmd.equals(Handlers.DEADLINE.label)) {
                int index = input.indexOf("/by");
                Deadline task = new Deadline(input.substring(9, index - 1), input.substring(index + 4));
                tlist.addTask(task);
                System.out.println("Deadline Added: " + task.toString());
                System.out.println("There are now " + tlist.getTotalTasks() + " tasks in the list.\n");
            } else if (cmd.equals(Handlers.EVENT.label)) {
                int index = input.indexOf("/at");
                Event task = new Event(input.substring(6, index - 1), input.substring(index + 4));
                tlist.addTask(task);
                System.out.println("Event Added: " + task.toString());
                System.out.println("There are now " + tlist.getTotalTasks() + " tasks in the list.\n");
            } else if (cmd.equals(Handlers.TODO.label)) {
                Todo task = new Todo(input.substring(5));
                tlist.addTask(task);
                System.out.println("Todo Added: " + task.toString());
                System.out.println("There are now " + tlist.getTotalTasks() + " tasks in the list.\n");
            } else if (cmd.equals(Handlers.MARK.label)) {
                System.out.println("Nice! I've marked this task as done!");
                Task t = tlist.mark(Integer.parseInt(inputArr[1]));
                System.out.println(t.toString() + "\n");
            } else if (cmd.equals(Handlers.UNMARK.label)) {
                System.out.println("Okay! I've marked this as undone!");
                Task t = tlist.unmark(Integer.parseInt(inputArr[1]));
                System.out.println(t.toString() + "\n");
            } else {
                Task task = new Task(input);
                tlist.addTask(task);
                System.out.println("Added: " + task.toString());
                System.out.println("There are now " + tlist.getTotalTasks() + " tasks in the list.\n");
            }
        }
    }
}
