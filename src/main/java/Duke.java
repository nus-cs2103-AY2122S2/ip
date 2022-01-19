import java.util.Scanner;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        int listCount = 0;
        String[] list = new String[100];
        while (true) {
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            } else if (userInput.equals("list")) {
                for (int i = 0; i < listCount; i++) {
                    System.out.println(String.format("%d. %s", i + 1, list[i]));
                }
            } else {
                System.out.println("added: " + userInput);
                list[listCount] = userInput;
                listCount++;
            }
            System.out.println(userInput);
        }

    }
}
