import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<String> list = new ArrayList<String>();
    public static ArrayList<Boolean> status = new ArrayList<Boolean>();
    public static ArrayList<String> type = new ArrayList<String>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo + "Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String inputstring1 = sc.nextLine();
        String[] inputstring = inputstring1.split(" ");
        while (!inputstring[0].equals("bye")) {
            if (inputstring[0].equals("list")) {
                displaylist();
            } else if (inputstring[0].equals("mark")) {
                mark(inputstring[1]);
            } else if (inputstring[0].equals("unmark")) {
                unmark(inputstring[1]);
            } else if (inputstring[0].equals("todo") || inputstring[0].equals("deadline") || inputstring[0].equals("event")) {
                addtolist(inputstring);
            } else if (inputstring[0].equals("delete")) {
                delete(inputstring[1]);
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            inputstring1 = sc.nextLine();
            inputstring = inputstring1.split(" ");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void displaylist() {
        String returnstring = "";
        for (int i = 0; i < list.size(); i++) {
            if (status.get(i)) {
                returnstring = returnstring + (i + 1) + ". [" + type.get(i) + "][X] " + list.get(i) + "\n";
            } else {
                returnstring = returnstring + (i + 1) + ". [" + type.get(i) + "][ ] " + list.get(i) + "\n";
            }
        }
        System.out.println(returnstring);
    }

    public static void addtolist(String[] stringtoadd) {
        if (stringtoadd.length < 2) {
            System.out.println("OOPS!! The description of a " + stringtoadd[0] + " cannot be empty.");
        } else {
            String initial = "";
            String returnstring = "";
            for (int i = 1; i < stringtoadd.length; i++) {
                returnstring = returnstring + stringtoadd[i] + " ";
            }
            if (stringtoadd[0].equals("todo")) {
                initial = "T";
            } else if (stringtoadd[0].equals("deadline")) {
                initial = "D";
            } else {
                initial = "E";
            }
            type.add(initial);
            status.add(false);
            list.add(returnstring);
            System.out.println("Got it. I've added this task:\n" + " [" + initial + "][] " + returnstring + "\nNow you have " +
                    list.size() + " tasks in the list.");
        }
    }

    public static void mark(String number) {
        int num = Integer.parseInt(number);
        status.set(num - 1, true);
        System.out.println("Nice! I've marked this tast as done:\n [" + type.get(num - 1) + "][X] " + list.get(num - 1));
    }

    public static void unmark(String number) {
        int num = Integer.parseInt(number);
        status.set(num - 1, false);
        System.out.println("OK, I've marked this task as not done yet:\n [" + type.get(num - 1) + "][ ] " + list.get(num - 1));
    }

    public static void delete(String number) {
        int num = Integer.parseInt(number);
        if (status.get(num - 1)) {
            System.out.println("Noted. I've removed this task:\n [" + type.get(num - 1) + "][X] " + list.get(num - 1) +
                    "\nNow you have " + (list.size() - 1) + " tasks left in this list");
        } else {
            System.out.println("Noted. I've removed this task:\n [" + type.get(num - 1) + "][ ] " + list.get(num - 1) +
                    "\nNow you have " + (list.size() - 1) + " tasks left in this list");
        }
        list.remove(num - 1);
        type.remove(num - 1);
        status.remove(num - 1);
    }
}
