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
            String[] splitInput = input.split("\\s+");
            // If user inputs bye, set end to be true and hence exiting out of the program
            if (input.equals("bye")) {
                end = true;
            }
            // If user inputs list, run through the to do list, array store[], and list out all the to dos
            else if (input.equals("list")) {
                for (int n = 0; store[n] != null; n++) {
                    int temp = n + 1;
                    System.out.println(temp + ".[" + store[n].getStatusIcon() + "] " + store[n].getDescription());
                }
            }
            // If user inputs keyword mark, mark the task
            else if (splitInput[0].equals("mark")) {
                Integer num = Integer.parseInt(splitInput[1]) - 1;
                store[num].setMark();
                System.out.println("Nice! I've marked this task as done:\n  [X] " + store[num].getDescription());
            }
            // If user inputs keyword unmark, unmark the task
            else if (splitInput[0].equals("unmark")){
                Integer num = Integer.parseInt(splitInput[1]) - 1;
                store[num].setUnmark();
                System.out.println("OK, I've marked this task as not done yet:\n  [ ] " + store[num].getDescription());
            }
            // If user inputs anything else, program would add the input into the to do list in order
            else {
                store[curr] = new Task(input);
                System.out.println("added: " + input);
                curr++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

