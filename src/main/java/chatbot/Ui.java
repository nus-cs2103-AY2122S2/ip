package chatbot;

public class Ui {
    private static final String STRAIGHT_LINE = "____________________________________________________________";
    
    // inserts and formats the straight lines into the reply
    public static String createReply(String reply) {
        return STRAIGHT_LINE + "\n " + reply + "\n" + STRAIGHT_LINE;
    }

    // displays message in the command line
    public static void displayMessage(String message) {
        System.out.println(createReply(message));
    }
}
