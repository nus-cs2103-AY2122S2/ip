import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Bot Duke = new Bot();
        Duke.greet();
        Scanner sc = new Scanner(System.in);

        String userInput = sc.nextLine();
        // Repeat till bye is entered
        while (!userInput.equals("bye")) {
            try {
                Duke.communicate(userInput);
            } catch (DukeException e) {
                System.out.println(Duke.encloseWithin(e.getMessage()));
            }
            // Get next input
            userInput = sc.nextLine();
        }
        Duke.sayGoodbye();
    }
}


