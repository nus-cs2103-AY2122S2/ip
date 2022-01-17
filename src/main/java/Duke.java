import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetings = "Greetings from Ann \n" + "What can I do for you?";
        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine();
            if(input.toLowerCase().equals("bye")) {
                System.out.println("Sad to see you go :( See you again soon!");
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
