import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> toDoList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("enter a command");

        String userInput = sc.nextLine();
        while (!(userInput.equals("bye")))
        {
            if (userInput.equals("list"))
            {
                for (int i = 0; i < toDoList.size(); i++)
                {
                    System.out.printf("%d. " + toDoList.get(i) +"\n", i + 1);
                }
                userInput = sc.nextLine();
            }
            else
            {
                System.out.println("task added: " + userInput);
                toDoList.add(userInput);
                userInput = sc.nextLine();
            }
        }
        System.out.println("goodbye!");
    }
}
