import java.util.Scanner;

public class Duke {

    public static String answer(String input) {
        if (!input.equals("bye")) {
            return input;
        } else {
            return "Bye. Hope to see you again soon!";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke \n" + "What can I do for you?");
        String userInput = "";

        while (!userInput.equals("bye")) {
            userInput = sc.nextLine();
            System.out.println(answer(userInput));
        }
        sc.close();

    }
}
