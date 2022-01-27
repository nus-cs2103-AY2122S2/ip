import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * main functions like a to-do list
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Boolean end = false;
        ArrayList<Task> store = new ArrayList(100);
        int curr = 0;

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        // While loop ends when user inputs bye
        while(!end) {
            input = scanner.nextLine();
            String[] splitInput = input.split(" ", 2);
            try {
                new DukeException().checker(splitInput, curr);
            } catch (DukeException e) {
                System.err.println(e);
                continue;
            }
            // If user inputs bye, terminate the program
            if (input.equals("bye")) {
                end = true;
            }
            // If user inputs list, run through the to-do list, array store[], and list out all the to-dos
            else if (input.equals("list")) {
                for (int n = 0; n < curr; n++) {
                    int temp = n + 1;
                    System.out.println(temp + "." + store.get(n).toString());
                }
            }
            // If user inputs keyword mark, mark the task corresponding to the index number as done
            else if (splitInput[0].equals("mark")) {
                Integer num = Integer.parseInt(splitInput[1]) - 1;
                store.get(num).setMark();
                System.out.println("Nice! I've marked this task as done:\n  " + store.get(num).toString());
            }
            // If user inputs keyword unmark, mark the task corresponding to the index number as not done
            else if (splitInput[0].equals("unmark")){
                Integer num = Integer.parseInt(splitInput[1]) - 1;
                store.get(num).setUnmark();
                System.out.println("OK, I've marked this task as not done yet:\n  " + store.get(num).toString());
            }
            // If user inputs keyword to-do, add this task as a Todo class
            else if (splitInput[0].equals("todo")) {
                store.add(new Todo(splitInput[1]));
                int temp = curr + 1;
                System.out.println("Got it. I've added this task:\n  " + store.get(curr).toString()
                        + "\nNow you have " + temp + " tasks in the list.");
                curr++;
            }
            // If user inputs keyword deadline, add this task as a Deadline class
            else if (splitInput[0].equals("deadline")) {
                String[] splitInput2 = splitInput[1].split(" /by ", 2);
                store.add(new Deadline(splitInput2[0], splitInput2[1]));
                int temp = curr + 1;
                System.out.println("Got it. I've added this task:\n  " + store.get(curr).toString()
                        + "\nNow you have " + temp + " tasks in the list.");
                curr++;
            }
            // If user inputs keyword event, add this task as an Event class
            else if (splitInput[0].equals("event")) {
                String[] splitInput2 = splitInput[1].split(" /at ", 2);
                store.add(new Event(splitInput2[0], splitInput2[1]));
                int temp = curr + 1;
                System.out.println("Got it. I've added this task:\n  " + store.get(curr).toString()
                        + "\nNow you have " + temp + " tasks in the list.");
                curr++;
            }
            // If user inputs keyword delete, delete the task corresponding to the index given
            else if (splitInput[0].equals("delete")) {
                Integer num = Integer.parseInt(splitInput[1]) - 1;
                curr--;
                System.out.println("Noted. I've removed this task:\n  " + store.get(num).toString()
                        + "\nNow you have " + curr + " tasks in the list.");
                store.remove(num);
            }
            // Unused
            else {}
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

