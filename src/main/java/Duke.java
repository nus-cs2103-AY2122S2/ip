import java.util.Scanner;

public class Duke {

    private static String getFirstWord(String text) {
        int index = text.indexOf(' ');
        String word;
        if (index > -1) {
            word = text.substring(0, index);
        } else {
            word = text;
        }
        return word;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.exit(0);
                sc.close();
            } else {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(String.format("%s", command));
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
    }
}
