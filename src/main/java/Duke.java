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
            try {
                if (cmd.equals(Handlers.BYE.label)) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (cmd.equals(Handlers.LIST.label)) {
                    System.out.println("Fetching all records...");
                    System.out.println(tlist.toString());
                } else if (cmd.equals(Handlers.DEADLINE.label)) {
                    try {
                        int index = input.indexOf("/by");
                        DukeException.deadlineValidity(index, input);
                        Deadline task = new Deadline(input.substring(9, index - 1), input.substring(index + 4));
                        tlist.addTask(task);
                        System.out.println("Deadline Added: " + task.toString());
                        System.out.println("There are now " + tlist.getTotalTasks() + " tasks in the list.\n");
                    } catch (DukeException err) {
                        System.out.println(err.getMessage());
                    }
                } else if (cmd.equals(Handlers.EVENT.label)) {
                    try {
                        int index = input.indexOf("/at");
                        DukeException.eventValidity(index, input);
                        Event task = new Event(input.substring(6, index - 1), input.substring(index + 4));
                        tlist.addTask(task);
                        System.out.println("Event Added: " + task.toString());
                        System.out.println("There are now " + tlist.getTotalTasks() + " tasks in the list.\n");
                    } catch (DukeException err) {
                        System.out.println(err.getMessage());
                    }
                } else if (cmd.equals(Handlers.TODO.label)) {
                    try {
                        DukeException.todoValidity(input);
                        Todo task = new Todo(input.substring(5));
                        tlist.addTask(task);
                        System.out.println("Todo Added: " + task.toString());
                        System.out.println("There are now " + tlist.getTotalTasks() + " tasks in the list.\n");
                    } catch (DukeException err) {
                        System.out.println(err.getMessage());
                    }
                } else if (cmd.equals(Handlers.MARK.label)) {
                    try {
                        int index = DukeException.indexValidity(input, tlist);
                        System.out.println("Nice! I've marked this task as done!");
                        Task t = tlist.mark(index);
                        System.out.println(t.toString() + "\n");
                    } catch (DukeException err) {
                        System.out.println(err.getMessage());
                    }
                } else if (cmd.equals(Handlers.UNMARK.label)) {
                    try {
                        int index = DukeException.indexValidity(input, tlist);
                        System.out.println("Okay! I've marked this as undone!");
                        Task t = tlist.unmark(index);
                        System.out.println(t.toString() + "\n");
                    } catch (DukeException err) {
                        System.out.println(err.getMessage());
                    }
                } else if (cmd.equals(Handlers.DELETE.label)) {
                    try {
                        int index = DukeException.indexValidity(input, tlist);
                        System.out.println("Noted. Deleting this task...");
                        Task t = tlist.delete(index);
                        System.out.println(t.toString());
                        System.out.println("There are now " + tlist.getTotalTasks() + " tasks in the list.\n");
                    } catch (DukeException err) {
                        System.out.println(err.getMessage());
                    }
                } else {
                    throw new IllegalArgumentException("Sorry, this is not a recognized command.\n");
                }
            } catch (IllegalArgumentException err) {
                System.out.println(err.getMessage());
            }
        }
    }
}
