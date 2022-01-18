import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Runs a Personal Assistant Chatbot which helps users
 *  keep track of tasks and other things. The current
 *  iteration of the Chatbot is named Luca.
 */
public class Duke {

    /**
     * Logo of the Chatbot.
     */
   public static final String logo = "888      888     888  .d8888b.         d8888 \n"
           + "888      888     888 d88P  Y88b       d88888 \n"
           + "888      888     888 888    888      d88P888 \n"
           + "888      888     888 888            d88P 888 \n"
           + "888      888     888 888           d88P  888 \n"
           + "888      888     888 888    888   d88P   888 \n"
           + "888      Y88b. .d88P Y88b  d88P  d8888888888 \n"
           + "88888888  \"Y88888P\"   \"Y8888P\"  d88P     888 \n";

    /**
     * The divder line used to wrap string before output.
     */
    private static final String dividerLine = "___________________"
            + "_________________________________________\n";

    /**
     * List to store items. There will be no more than 100 items.
     */
    private static ArrayList<String> list = new ArrayList<>();

    /**
     * Adds texts to the list object and outputs the formatted
     * string with the text
     *
     * @param text text added to the list.
     * @return formatted string with added text
     */
    private static String addToList(String text) {
        list.add(text);
        return formatString("Added: " +  text);
    }

    /**
     * Returns the string containg the text on the list with
     * formatting
     * @return formatted string with list text
     */
    private static String listToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append((i + 1) + ". " +list.get(i));
            if (i != list.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        return formatString(stringBuilder.toString());
    }

    /**
     * Formats the string by indenting with 4 spaces
     * and wrapping the string with divider lines.
     *
     * @param input string to be formatted.
     * @return indented string with divider lines.
     */
    private static String formatString(String input) {
        String temp = dividerLine + input + "\n" + dividerLine;
        String indented = temp.replaceAll("(?m)^", "    ");
        return indented;
    }

    /**
     * Runs the Personal Assistant Chatbot where
     * the user can input commands.
     *
     * @param args initial arguments.
     */
    public static void main(String[] args) {

        String startPrompt = "Hi! I'm Luca\n"
                + "How may I help you?\n";

        System.out.println(Duke.formatString("\n" + logo + "\n"
                + startPrompt));

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Exit loop only after the user enters "Bye"
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(Duke.listToString());
            } else {
                System.out.println(Duke.addToList(input));
            }
            input = scanner.nextLine();
        }

        System.out.println(Duke.formatString("Bye. See you again soon!"));
    }
}
