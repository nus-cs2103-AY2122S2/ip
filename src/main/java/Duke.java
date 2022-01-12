import java.util.Scanner;

public class Duke {
    private static boolean isEnd = false;

    public static void horizontalLine(){
        System.out.println("____________________________________________________________");
    }

    public static void greet(){
        horizontalLine();
        System.out.println("\tHello I am DDX");
        System.out.println("\tWhat can I do for you?");
        System.out.println();
        horizontalLine();
    }

    public static void echo(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String output = myObj.nextLine();
        // detect bye
        if (output.equals("bye")) {
            Duke.isEnd = true;
            output = "\tBye. Hope to see you again soon!";
        }
        horizontalLine();
        System.out.println(output);
        System.out.println();
        horizontalLine();
    }

    public static void main(String[] args) {
        greet();
        while (!isEnd) {
            echo();
        }
    }
}
