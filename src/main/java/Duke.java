import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> list = new ArrayList<Task>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob :) \nWhat can I do for you? :D");
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
            } else if (inputstring[0].equals("todo") || inputstring[0].equals("deadline")
                    || inputstring[0].equals("event")) {
                addtolist(inputstring);
            } else if (inputstring[0].equals("delete")) {
                delete(inputstring[1]);
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means O.o");
            }
            inputstring1 = sc.nextLine();
            inputstring = inputstring1.split(" ");
        }
        System.out.println("Bye. Hope to see you again soon! :))");
    }

    public static void displaylist() {
        String returnstring = "";
        for (int i = 0; i < list.size(); i++) {
            returnstring = returnstring + (i + 1) + ". [" + list.get(i).symbol() + "][" + list.get(i).getStatusIcon() +
                    "] " + list.get(i) + "\n";
        }
        System.out.println(returnstring);
    }

    public static void addtolist(String[] stringtoadd) {
        if (stringtoadd.length < 2) {
            System.out.println("OOPS!! The description of a " + stringtoadd[0] + " cannot be empty.");
        } else {
            Task task;
            String returnstring = "";
            for (int i = 1; i < stringtoadd.length; i++) {
                returnstring = returnstring + stringtoadd[i] + " ";
            }
            if (stringtoadd[0].equals("todo")) {
                task = new Todo(returnstring);
            } else if (stringtoadd[0].equals("deadline")) {
                task = new Deadline(returnstring);
            } else {
                task = new Event(returnstring);
            }
            list.add(task);
            System.out.println("Got it!! :D I've added this task:\n" + " [" + task.symbol() + "][] " + returnstring +
                    "\nNow you have " + list.size() + " tasks in the list.");
        }
    }

    public static void mark(String number) {
        int num = Integer.parseInt(number);
        list.get(num - 1).setAsDone();
        Task temp = list.get(num - 1);
        System.out.println("Nice! :P I've marked this tast as done:\n [" + temp.symbol() + "][" +
                temp.getStatusIcon() + "] " + temp);
    }

    public static void unmark(String number) {
        int num = Integer.parseInt(number);
        list.get(num - 1).setAsNotDone();
        Task temp = list.get(num - 1);
        System.out.println("OK ._. , I've marked this task as not done yet:\n [" + temp.symbol() + "][" +
                temp.getStatusIcon() + "] " + temp);
    }

    public static void delete(String number) {
        int num = Integer.parseInt(number);
        Task temp = list.get(num - 1);
        System.out.println("Noted. I've removed this task:\n [" + temp.symbol() + "][" +
                temp.getStatusIcon() + "] " + temp +
                    "\nNow you have " + (list.size() - 1) + " tasks left in this list");
        list.remove(num - 1);
    }
}
