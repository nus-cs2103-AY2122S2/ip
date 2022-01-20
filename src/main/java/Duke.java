import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        int counter = 0;
        String[] items = new String[100];
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (true) {
            String input = myObj.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")){
                for(int i = 0; i < counter; i++) {
                    System.out.println(Integer.toString(i + 1) +". " + items[i]);
                }
            } else {
                items[counter] = input;
                counter = counter + 1;
                System.out.println("added: " + input);
            }
        }
    }
}
