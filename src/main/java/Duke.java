import java.util.Scanner;
import java.util.logging.Handler;

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
            if (input.equals(Handlers.BYE.label)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals(Handlers.LIST.label)) {
                System.out.println("Fetching all records...");
                System.out.println(tlist.toString());
            } else {
                Todo todo = new Todo(input);
                tlist.addTodo(todo);
                System.out.println("Added: " + todo.toString() + "\n");
            }
        }
    }
}
