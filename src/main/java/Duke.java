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

        Todolist tlist = new Todolist();
        while (true) {
            Scanner scn = new Scanner(System.in);
            String input = scn.nextLine();
            String[] inputArr = input.split(" ");
            String cmd = inputArr[0];
            if (cmd.equals(Handlers.BYE.label)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.equals(Handlers.LIST.label)) {
                System.out.println("Fetching all records...");
                System.out.println(tlist.toString());
            } else if (cmd.equals(Handlers.MARK.label)) {
                System.out.println("Nice! I've marked this task as done!");
                Todo t = tlist.mark(Integer.parseInt(inputArr[1]));
                System.out.println(t.toString() + "\n");
            } else if (cmd.equals(Handlers.UNMARK.label)) {
                System.out.println("Okay! I've marked this as undone!");
                Todo t = tlist.unmark(Integer.parseInt(inputArr[1]));
                System.out.println(t.toString() + "\n");
            } else {
                Todo todo = new Todo(input);
                tlist.addTodo(todo);
                System.out.println("Added: " + todo.toString() + "\n");
            }
        }
    }
}
