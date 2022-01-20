import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Boolean end = false;
        String[] store = new String[100];
        int curr = 0;

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        // While loop ends when user inputs bye
        while(!end) {
            input = scanner.nextLine();
            // If user inputs bye, set end to be true and hence exiting out of the program
            if (input.equals("bye")) {
                end = true;
            }
            // If user inputs list, run through the to do list, array store[], and list out all the to dos
            else if (input.equals("list")) {
                for (int n = 0; store[n] != null; n++) {
                    int temp = n + 1;
                    System.out.println(temp + ". " + store[n]);
                }
            }
            // If user inputs anything else, program would add the input into the to do list in order
            else {
                store[curr] = input;
                System.out.println("added: " + input);
                curr++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
