import java.util.*;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you? =)");
        TaskList tl = new TaskList();

        while (true) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                break;
            }
            if (s.equals("list")) {
                tl.printItems();
            } else {
                String[] text = s.split(" ");
                String command = text[0];
                if (command.equals("mark")) {
                    int index = Integer.parseInt(text[1]) - 1;
                    tl.markItemDone(index);
                } else if (command.equals("unmark")) {
                    int index = Integer.parseInt(text[1]) - 1;
                    tl.markItemUndone(index);
                } else {
                    System.out.println("added o.O: ");
                    System.out.print("\t");
                    if (command.equals("todo")) {
                        tl.addTodo(s.split("todo ")[1]);
                    } else if (command.equals("deadline")) {
                        String[] inputs = s.split("deadline ")[1].split(" /by ");
                        String name = inputs[0];
                        String time = inputs[1];
                        tl.addDeadline(name,time);
                    } else if (command.equals("event")) {
                        String[] inputs = s.split("event ")[1].split(" /at ");
                        String name = inputs[0];
                        String time = inputs[1];
                        tl.addEvent(name,time);
                    } else {
                        tl.addItem(s);
                    }
                    System.out.println(tl.getLast());
                    System.out.println("Now there are " + tl.size() + " tasks in the list x)");
                }
            }
        }
        System.out.println("Bye t_t");
    }

    public static void echo(String s) {
        System.out.println("\t" + s);
    }
}
