import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Hello, traveller! My name in Paimon.\nHow can I help you today?");
        String input = readInput(reader);
        String userData[] = new String[100]; //100 is an arbitrary constant
        int pointer = 0;
        String commands[] = new String[10]; //10 is an arbitrary constant
        Arrays.fill(commands, "");
        //commands[0] = "list";

        while (!input.equals("bye")){
            System.out.println(input);

            if (input.equals("list")) {
                printList(userData);
            }
            else {
                userData[pointer] = input;
                pointer++;
            }

            input = readInput(reader);
        }

        if (input.equals("bye")) {
            System.out.println("Bye, hope to see you again soon!");
            reader.close();
        }
    }

    public static String readInput(Scanner s) {
        System.out.print("| \r");
        return s.nextLine();
    }

    /* Returns true if an item is in a given array, and false otherwise.
     * Only compatible with Strings at the moment.
     */
    public static boolean inList(String[] list, String s) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(s)) {
                return true;
            }
        }
        return false;
    }

    /* Prints out a given list.
     * Only compatible with Strings at the moment.
     */
    public static void printList(String[] s) {
        System.out.println("Hmm... Paimon keeps a clear record in her diary.");
        for (int i = 0; i < s.length; i++) {
            if (s[i] != null) {
                System.out.println(String.format("  %d. %s", i + 1, s[i]));
            }
            else {
                return;
            }
        }
    }
}
