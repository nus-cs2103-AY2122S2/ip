public class Duke {

    private final static String paddingStr = "    ";

    public static void main(String[] args) {
        initialiseBot();
    }

    private static void initialiseBot() {
        String divString = "-----------------------------------------";
        String botName = "Baymax";

        System.out.println(paddingStr + divString);
        System.out.println(paddingStr + paddingStr + "Greetings, I am " + botName + ".");
        System.out.println(paddingStr + paddingStr + "What can I do you for?");
        System.out.println(paddingStr + divString);

    }
}
