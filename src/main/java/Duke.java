import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public Duke(){}

    public String answer(String input) {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else {
            return input;
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke \n" + "What can I do for you?");
        String userInput = "";

        while (!userInput.equals("bye")) {
            userInput = sc.nextLine();
            System.out.println(duke.answer(userInput));
        }
        sc.close();

    }
}

