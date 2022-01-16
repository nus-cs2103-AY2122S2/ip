import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String HORIZONTAL_LINE = "\t" + "____________________________________________________________";
        String[] storedText = new String[100];
        int counting = 0;

        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Hello! I'm Duke");
        System.out.println("\t" + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("\t" + "Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);
                break;
            } else if (userInput.equals("list")) {
                System.out.println(HORIZONTAL_LINE);
                for (int i = 0; i < counting; i++) {
                    int index = i + 1;
                    System.out.println("\t" + index + ". " + storedText[i]);
                }
                System.out.println(HORIZONTAL_LINE);
            } else {
                storedText[counting] = userInput;
                counting++;
                System.out.println(HORIZONTAL_LINE);
                System.out.println("\t" + "added: " + userInput);
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }
}
