public class Duke {

    private final static String divPadding = "    ";
    private final static String strPadding = "      ";

    public static void main(String[] args) {
        initialiseBot();
    }

    private static void initialiseBot() {
        String divString = "-----------------------------------------";
        String botName = "Baymax";

        System.out.println(divPadding + divString);
        System.out.println(strPadding + "Greetings, I am " + botName + ".");
        System.out.println(strPadding + "What can I do you for?");
        System.out.println(divPadding + divString);

    }
}
