import java.util.Scanner;

public class Duke {
    static String indent = "    ";
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        hLineBreak();
        printlnWithIndent("Hello! I'm YourBoss.\n" +
                indent +
                "What can you do for me?");
        hLineBreak();

        String userInput = "";

        while(userInput != "bye" || userInput != "Bye") {
            userInput = scanner.nextLine();
            hLineBreak();
            printlnWithIndent(userInput);
            hLineBreak();
        }

        hLineBreak();
        printlnWithIndent("Bye. Hope I never see you again!");
        hLineBreak();

        scanner.close();
    }

    static void hLineBreak() {
        System.out.println(indent+"____________________________________________________________");
    }

    static void printlnWithIndent(String input) {
        System.out.print(indent);
        System.out.println(input);
    }
}
