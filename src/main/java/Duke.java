import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("enter a command");
        
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while (!(userInput.equals("bye")))
        {
            System.out.println(userInput);
            userInput = sc.nextLine();
        }
        System.out.println("goodbye!");
    }
}
