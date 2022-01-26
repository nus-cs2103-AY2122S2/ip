package chatbot;

/**
* Ui class for the bot, handling the displayed messages to the user.
*/
public class Ui {
    private static final String STRAIGHT_LINE = "____________________________________________________________";
    
    /**
    * Returns a formatted String including straight lines above 
    * and below input String.
    *
    * @param  reply  a String to be formatted with straight lines
    * @return        the input String formatted with straight lines
    */
    public static String createReply(String reply) {
        return STRAIGHT_LINE + "\n " + reply + "\n" + STRAIGHT_LINE;
    }

    /**
    * Prints String message into the command line formatted with straight lines
    * above and below message.
    *
    * @param  message  a String to be formatted with straight lines
    */
    public static void displayMessage(String message) {
        System.out.println(createReply(message));
    }
}
