import java.util.Scanner;

/**
 * This class encapsulates the chatbot Fluffers for CS2103T's Individual Project.
 * As there can only be one Fluffers, most fields and methods will be static, so
 * that users do not have to initialise a Fluffers Object.
 *
 * ASCII art credit: All ASCII art was found on https://www.asciiart.eu/animals/cats .
 *
 * @author Ong Han Yang
 * @version 0
 */
public class Fluffers {
    /** ASCII art for when Fluffers just wakes up*/
    private static String AWAKE =
            "    /\\_____/\\\n" +
            "   /  o   o  \\\n" +
            "  ( ==  ^  == )\n" +
            "   )         (\n" +
            "  (           )\n" +
            " ( (  )   (  ) )\n" +
            "(__(__)___(__)__)";

    /** ASCII art for when Fluffers goes back to sleep*/
    private static String ASLEEP =
            "      |\\      _,,,---,,_\n" +
            "ZZZzz /,`.-'`'    -.  ;-;;,_\n" +
            "     |,4-  ) )-,_. ,\\ (  `'-'\n" +
            "    '---''(_/--'  `-'\\_)";

    private static String LIST_TOP =
            "    |\\__/,|   (`\\\n" +
            "  _.|o o  |_   ) )\n" +
            "-(((---(((--------";

    /**
     * Asks Fluffers to speak with fancy formatting.
     *
     * @param input the text that Fluffers is asked to speak.
     * @return the formatted String that Fluffers is asked to speak.
     */
    private static String speak(String input) {
        return String.format("Meow! (%s)\n", input);
    }

    /**
     * Overloaded speak method to include a isQuestion prompt, to ask Fluffers
     * to speak with fancy formatting.
     *
     * @param input the text that Fluffers is asked to speak.
     * @param isQuestion whether the text is meant to be a question or not.
     * @return the formatted String that Fluffers is asked to speak.
     */
    private static String speak(String input, boolean isQuestion) {
        return String.format("Meow%s (%s)\n", isQuestion ? "?" : "!", input);
    }

    /**
     * The main method to start the program/wake Fluffers up
     * @param args input CLI arguments.
     */
    public static void main(String[] args) {
        String openingText = "Activating Cat Translator 2000...\n" +
                "Waking Fluffers up...\n" +
                "Meow! (Hello!)\n\n" +
                Fluffers.AWAKE;

        System.out.println(openingText);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(Fluffers.speak("Goodbye!"));
                System.out.println(Fluffers.ASLEEP);
                break;
            } else {
                System.out.println(Fluffers.speak(input));
            }
        }

    }
}
