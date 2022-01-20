import java.util.Scanner;
//import ip.src.main.java.Task;

public class Duke {
    /**
     * main functions like a to-do list
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Boolean end = false;
        Task[] store = new Task[100];
        int curr = 0;

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        // While loop ends when user inputs bye
        while(!end) {
            input = scanner.nextLine();
            String[] splitInput = input.split(" ", 2);
            // If user inputs bye, set end to be true and hence exiting out of the program
            if (input.equals("bye")) {
                end = true;
            }
            // If user inputs list, run through the to do list, array store[], and list out all the to dos
            else if (input.equals("list")) {
                for (int n = 0; store[n] != null; n++) {
                    int temp = n + 1;
                    System.out.println(temp + "." + store[n].toString());
                }
            }
            // If user inputs keyword mark, mark the task
            else if (splitInput[0].equals("mark")) {
                Integer num = Integer.parseInt(splitInput[1]) - 1;
                store[num].setMark();
                System.out.println("Nice! I've marked this task as done:\n  " + store[num].toString());
            }
            // If user inputs keyword unmark, unmark the task
            else if (splitInput[0].equals("unmark")){
                Integer num = Integer.parseInt(splitInput[1]) - 1;
                store[num].setUnmark();
                System.out.println("OK, I've marked this task as not done yet:\n  " + store[num].toString());
            }
            // If user inputs keyword to-do, add this task as a Todo class
            else if (splitInput[0].equals("todo")) {
                store[curr] = new Todo(splitInput[1]);
                int temp = curr + 1;
                System.out.println("Got it. I've added this task:\n  " + store[curr].toString()
                        + "\nNow you have " + temp + " tasks in the list.");
                curr++;
            }
            // If user inputs keyword deadline, add this task as a Deadline class
            else if (splitInput[0].equals("deadline")) {
                String[] splitInput2 = splitInput[1].split("/by", 2);
                store[curr] = new Deadline(splitInput2[0], splitInput2[1]);
                int temp = curr + 1;
                System.out.println("Got it. I've added this task:\n  " + store[curr].toString()
                        + "\nNow you have " + temp + " tasks in the list.");
                curr++;
            }
            // If user inputs keyword event, add this task as a Event class
            else if (splitInput[0].equals("event")) {
                String[] splitInput2 = splitInput[1].split("/at", 2);
                store[curr] = new Event(splitInput2[0], splitInput2[1]);
                int temp = curr + 1;
                System.out.println("Got it. I've added this task:\n  " + store[curr].toString()
                        + "\nNow you have " + temp + " tasks in the list.");
                curr++;
            }
            // Unused
            else {}
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

