import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "\n" +
                "    ___   ____________\n" +
                "   /   | / ____/ ____/\n" +
                "  / /| |/ /   / __/   \n" +
                " / ___ / /___/ /___   \n" +
                "/_/  |_\\____/_____/   \n" +
                "                      \n";
        Scanner reader = new Scanner(System.in);

        printAce(logo);
        printAce("Hey, I'm Ace. What can I help you with?");

        String userInput = reader.nextLine();

        while(!userInput.equals("bye")) {
            printAce(userInput);
            userInput = reader.nextLine();
        }
        printAce("See you later!");
        reader.close();
    }

    private static void printAce(String str) {
        System.out.println("________\n" + str.indent(4));
    }
}
