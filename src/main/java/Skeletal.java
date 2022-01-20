import java.util.*;

public class Skeletal {

    /**
     * This skeletal version is just supposed to take in the commands,
     * and return them, thats all.
     */

    public static String LINE_BREAK = "---------------";
    public static String GREETING = "Wassup buddddyyyyyy! How you doin? \nWhat can I do you for fine sire?";
    public static String BYE = LINE_BREAK + "\n  Byeeeee, come back again ah!\n" + LINE_BREAK;

    public static void main(String[] args) {

        String[] list = new String[100];
        int counter = 0;

        System.out.println(GREETING);

        // takes in the incoming prompt
        Scanner sc = new Scanner(System.in);

        // Outputs
        while (true) {

            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(BYE);
                break;
            } else if (input.equals("list")) {

                System.out.println(LINE_BREAK);
                int internalCounter = 1;

                // iterate through the list
                for (String item : list) {
                    if (item != null) {
                        System.out.println(" " + internalCounter + ". " + item);
                        ++internalCounter;
                    } else {
                        break;
                    }
                }
                System.out.println(LINE_BREAK);
            } else {
                list[counter] = input;
                System.out.println(LINE_BREAK);
                System.out.println(" ok added alr bro: " + input);
                System.out.println(LINE_BREAK);
                ++counter;
            }
        }
    }

}
