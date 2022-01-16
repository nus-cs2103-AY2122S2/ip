import java.util.*; // Import java utils

/**
 * Sana is a BIG program!
 *
 * @author  Jan Alfenson Tan
 * @version 1.0
 */
public class Sana {
    private static String border = "_____________________________________________";
    /**
     * userCommands stores the commands given to Sana from the user
     */
    private LinkedList<String> userCommands;

    public Sana() {
        this.userCommands = new LinkedList<>();
    }

    public void greet() {
        System.out.println("Hi! I'm BEEEEEEEG\nWhats up?");
    }
    public static void main(String[] args) {
        Sana sana = new Sana();
        sana.greet();

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
