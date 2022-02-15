import java.util.Scanner;
import java.lang.String;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello there, I'm Duke! Let's chat!");

        // Start scanner
        FastIO sc = new FastIO();

        String input = sc.nextLine();
        while(!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }
        System.out.println("Bye! It was nice having you!");
        sc.close();
    }
}
