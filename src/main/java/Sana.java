import java.util.Scanner; // Import the Scanner class

/**
 * Sana is a BIG program!
 *
 * @author  Jan Alfenson Tan
 * @version 1.0
 */
public class Sana {
    public static void main(String[] args) {
        String border = "_____________________________________________";
        System.out.println(border);
        System.out.println("Hi! I'm BEEEEEEEG\nWhats up?");
        System.out.println(border);

        Scanner userInput = new Scanner(System.in);
        while (true) {
            String input = userInput.nextLine();
            if (input.equals("bye")) {
                System.out.println(border);
                System.out.println("See you next time!");
                System.out.println(border);
                break;
            } else {
                System.out.println(border);
                System.out.println(input);
                System.out.println(border);
            }
        }
    }
}
