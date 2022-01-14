import java.util.Scanner;

public class Spark {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // print welcome message
        System.out.println("-----------------------------------");
        System.out.println("Hello! I'm Spark");
        System.out.println("What can I do for you?");
        System.out.println("-----------------------------------");

        // get command from user
        String command = sc.nextLine();

        // process commands that are not "bye"
        while (!command.equals("bye")) {
            System.out.println("-----------------------------------");
            System.out.println(command);
            System.out.println("-----------------------------------");

            command = sc.nextLine();
        }

        // print goodbye message
        System.out.println("Cool, see you around!");
    }
}
