import java.util.Scanner;

public class Siri {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Siri!");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String inputText = sc.next();

            if (inputText.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.printf("%s\n", inputText);
            }
        }
    }

}
