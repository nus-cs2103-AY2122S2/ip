import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<Task> list = new ArrayList<Task>();

    public static void handle(String input) {
        String[] split = input.split(" ");
        String command = split[0];
        if (command.equals("todo")) {
            Todo t = new Todo(input.substring(5));
            list.add(t);
            System.out.println("Got it. I've added: \n" + t.toString());
            System.out.printf("Sheesh you've now got %d tasks in the list\n", list.size());
        } else if (command.equals("deadline")) {
            String[] arr = input.substring(9).split("/by ");
            Deadline d = new Deadline(arr[0], arr[1]);
            list.add(d);
            System.out.println("Got it. I've added: \n" + d.toString());
            System.out.printf("Sheesh you've now got %d tasks in the list\n", list.size());
        } else if (command.equals("event")) {
            String[] arr = input.substring(6).split("/at ");
            System.out.println(arr[0]);
            Event e = new Event(arr[0], arr[1]);
            list.add(e);
            System.out.println("Got it. I've added: \n" + e.toString());
            System.out.printf("Sheesh you've now got %d tasks in the list\n", list.size());
        } else if (command.equals("bye")) {
            System.out.println("See you later alligator :)");
        } else if (command.equals("list")) {
            System.out.println("Here's everything on your list rn:");
            for (Integer i = 1; i <= list.size(); i++) {
                System.out.println(i.toString() + " " + list.get(i - 1));
            }
        } else if (command.equals("mark")) {
            Task t = list.get(Integer.parseInt(split[1]) - 1);
            t.makeDone();
            System.out.printf("Woohoo! I've marked task %s as done\n", split[1]);
            System.out.println(t.toString());
        } else if (command.equals("unmark")) {
            Task t = list.get(Integer.parseInt(split[1]) - 1);
            t.makeNotDone();
            System.out.printf("Ok boss I've marked task %s as incomplete\n", split[1]);
            System.out.println(t.toString());
        }
    }

    public static void main(String[] args) {

        String prince = "______       _\n"
                +  "| ___ \\     (_)\n"
                + "| |_/ /_ __  _  _ __    ___  ___\n"
                + "|  __/| '__|| || '_ \\  / __|/ _ \\ \n"
                + "| |   | |   | || | | || (__|  __/\n"
                + "\\_|   |_|   |_||_| |_| \\___|\\___|\n";

        String divider = "~~~~~~~~~~~~~~~~~~~~~~~";

        System.out.println("Hello I'm\n" + prince);
        System.out.println("How can I help you today?");
        System.out.println(divider);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            handle(command);
            System.out.println(divider);
            if (command.equals("bye")) {
                break;
            }
        }
    }
}
