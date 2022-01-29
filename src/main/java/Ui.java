public class Ui {

    public static final String LINE_SEPARATOR = System.lineSeparator();

    public static final String EXCLAMATION = "!";
    public static final String DIVIDER = "================================================================";

    public static final String LINE_PREFIX = "|| ";

    public static final String BOT_NAME = "Feline";

    public static final String WELCOME_MESSAGE = "Yoooo! My name is "
            + BOT_NAME + EXCLAMATION + LINE_SEPARATOR + "How can i help you bro?";

    public static final String FAREWELL_MESSAGE = "See you next time" + EXCLAMATION;

    public static final String COMMANDS = "list, todo, deadline (using /by)," +
                                        " event (using /at), mark, unmark, delete";
    public void greet() {
        showToUser(DIVIDER, WELCOME_MESSAGE);
    }

    public void farewell() {
        showToUser(DIVIDER, FAREWELL_MESSAGE);
    }

    public String getCommands() {
        return COMMANDS;
    }

    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(LINE_PREFIX + m.replace("\n", LINE_SEPARATOR + LINE_PREFIX));
        }
    }
}