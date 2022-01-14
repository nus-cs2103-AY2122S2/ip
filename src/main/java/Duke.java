import javax.swing.*;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Welcome to Duke! \nWhat can i do for you?\n");

        Scanner sc = new Scanner(System.in);
        ActionList list = new ActionList();

        String input = sc.nextLine();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                list.print();
            } else {
                boolean addSuccess = list.add(input);
                if (addSuccess) {
                    System.out.println("added: " + input);
                } else {
                    System.out.println("Failed to add " + input);
                }
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
