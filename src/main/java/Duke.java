import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        initDuke();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                dukePrint("Bye. Hope to see you again soon!");
                break;
            } else {
                dukePrint(userInput);
            }
        }
        sc.close();
    }

    private static void dukePrint(String input) {
        printHorizontalLine();
        System.out.println(input);
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void initDuke() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printHorizontalLine();
    }
}
