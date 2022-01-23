import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {

    private final ArrayList<Task> items;
    private int count;

    public Duke() {
        this.items = new ArrayList<>();
        this.count = 0;
    }

    public void printMsg(String input) {
        try {
            inputMsg(input);
        } catch (ToDoException e) {
            System.out.println("\t ☹ OOPS!!! The description of a todo cannot be empty.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inputMsg(String input) throws DukeException, ToDoException {
        if (input.equals("bye")) {
            System.out.println("\t Bye. Hope to see you again soon!");
        } else if (input.equals("list")) {
            System.out.println("\t Here are the tasks in your list:");
            for (int i=0; i<count; i++) {
                System.out.println("\t " + (i+1) + "." + items.get(i));
            }
        } else if (input.split(" ", 2)[0].equals("mark")) {
            int pos = Integer.parseInt(input.split(" ", 2)[1]);
            items.get(pos-1).markAsDone();
            System.out.println("\t Nice! I've marked this task as done:\n" + "\t  " + items.get(pos-1));
        } else if (input.split(" ", 2)[0].equals("deadline")) {
            String date = input.split("/", 2)[1].split(" ", 2)[1];
            String des = input.split(" /", 2)[0].split(" ", 2)[1];
            items.add(new Deadline(des, date));
            count++;
            System.out.println("\t Got it, I've added this task:");
            System.out.println("\t  " + items.get(count-1));
            System.out.println("\t Now you have " + count + " tasks in the list.");
        } else if (input.split(" ", 2)[0].equals("todo")) {
            String[] ls = input.split(" ", 2);
            if (ls.length <= 1) {
                throw new ToDoException();
            }
            String des = ls[0];
            items.add(new Todo(des));
            count++;
            System.out.println("\t Got it, I've added this task:");
            System.out.println("\t  " + items.get(count-1));
            System.out.println("\t Now you have " + count + " tasks in the list.");
        } else if (input.split(" ", 2)[0].equals("event")) {
            String date = input.split("/", 2)[1].split(" ", 2)[1];
            String des = input.split(" /", 2)[0].split(" ", 2)[1];
            items.add(new Deadline(des, date));
            count++;
            System.out.println("\t Got it, I've added this task:");
            System.out.println("\t  " + items.get(count-1));
            System.out.println("\t Now you have " + count + " tasks in the list.");
        }else if (input.split(" ", 2)[0].equals("delete")) {
            int pos = Integer.parseInt(input.split(" ", 2)[1]);
            Task t = items.remove(pos-1);
            count--;
            System.out.println("\t Noted, I've removed this task:");
            System.out.println("\t  " + t);
            System.out.println("\t Now you have " + count + " tasks in the list.");
        }else {
            throw new DukeException();
        }
    }

    public static void main(String[] args) throws IOException {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        String line = "\t_______________________________________________";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Duke duke = new Duke();

        System.out.println(line + "\n" + logo + "\t Hello! I'm Duke\n" + "\t What can I do for you?\n" + line);

        while (true) {
            String input = reader.readLine();

            System.out.println(line);
            duke.printMsg(input);
            System.out.println(line);

            if (input.equals("bye")) {
                break;
            }
        }

    }
}
