import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {

    private Task[] items;
    private int count;

    public Duke() {
        this.items = new Task[100];
        this.count = 0;
    }

    public void printMsg(String input) {
        try {
            inputMsg(input);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t ☹ OOPS!!! The description of a todo cannot be empty.");
        } catch (DukeException e) {
            System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void inputMsg(String input) throws DukeException {
        if (input.equals("bye")) {
            System.out.println("\t Bye. Hope to see you again soon!");
        } else if (input.equals("list")) {
            System.out.println("\t Here are the tasks in your list:");
            for (int i=0; i<count; i++) {
                System.out.println("\t " + (i+1) + "." + items[i]);
            }
        } else if (input.split(" ", 2)[0].equals("mark")) {
            int pos = Integer.parseInt(input.split(" ", 2)[1]);
            items[pos-1].markAsDone();
            System.out.println("\t Nice! I've marked this task as done:\n" + "\t  " + items[pos-1]);
        } else if (input.split(" ", 2)[0].equals("deadline")) {
            String date = input.split("/", 2)[1].split(" ", 2)[1];
            String des = input.split(" /", 2)[0].split(" ", 2)[1];
            items[count] = new Deadline(des, date);
            count++;
            System.out.println("\t Got it, I've added this task:");
            System.out.println("\t  " + items[count-1]);
            System.out.println("\t Now you have " + count + " tasks in the list.");
        } else if (input.split(" ", 2)[0].equals("todo")) {
            String des = input.split(" ", 2)[1];
            items[count] = new Todo(des);
            count++;
            System.out.println("\t Got it, I've added this task:");
            System.out.println("\t  " + items[count-1]);
            System.out.println("\t Now you have " + count + " tasks in the list.");
        } else if (input.split(" ", 2)[0].equals("event")) {
            String date = input.split("/", 2)[1].split(" ", 2)[1];
            String des = input.split(" /", 2)[0].split(" ", 2)[1];
            items[count] = new Deadline(des, date);
            count++;
            System.out.println("\t Got it, I've added this task:");
            System.out.println("\t  " + items[count-1]);
            System.out.println("\t Now you have " + count + " tasks in the list.");
        } else {
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
