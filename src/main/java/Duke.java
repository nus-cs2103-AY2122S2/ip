import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        int counter = 0;
        String[] items = new String[100];
        String[] doneOrUndone = new String[100];
        for(int i = 0; i < 100; i++) {
            doneOrUndone[i] = " ";
        }
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (true) {
            String input = myObj.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < counter; i++) {
                    System.out.println( Integer.toString(i + 1) + "." + "[" + doneOrUndone[i] + "] " + items[i]);
                }
            } else if(input.substring(0,4).equals("mark")){
                int toBeMarked = Integer.parseInt(input.substring(5));
                doneOrUndone[toBeMarked - 1] = "X";
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + items[toBeMarked - 1]);
            } else if(input.substring(0,6).equals("unmark")){
                int toBeUnmarked = Integer.parseInt(input.substring(7));
                doneOrUndone[toBeUnmarked - 1] = " ";
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println("[ ] " + items[toBeUnmarked - 1]);
            } else {
                items[counter] = input;
                counter = counter + 1;
                System.out.println("added: " + input);
            }
        }
    }
}
