import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String lines = "____________________________________________________________";

        System.out.println(lines);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(lines);

        Scanner scanner = new Scanner(System.in);

        String[] strings = new String[100];
        int counter = 0;

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(lines + "\nBye. Hope to see you again soon!\n" + lines);
                break;
            }
            else if (input.equals("list")) {
                System.out.println(lines);
                for (int i = 0;i < counter;i++) {
                    System.out.println(Integer.toString(i + 1) + ". " + strings[i]);
                }
                System.out.println(lines);

            }
            else {
                System.out.println(lines + "\nadded: " + input + "\n" + lines);
                strings[counter++] = input;
            }
        }
    }
}
