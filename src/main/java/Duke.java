public class Duke {
    private static final String BOT_ART =
            "     /\\   | |   | |          \n" +
            "    /  \\  | |__ | |__  _   _ \n" +
            "   / /\\ \\ | '_ \\| '_ \\| | | |\n" +
            "  / ____ \\| |_) | |_) | |_| |\n" +
            " /_/    \\_\\_.__/|_.__/ \\__, |\n" +
            "                        __/ |\n" +
            "                       |___/ \n";
    private static final String BOT_NAME = "Abby";

    public static void main(String[] args) {
        System.out.printf("Hello! I'm %s\nWhat can I do for you?\n", BOT_NAME);
        System.out.printf("Can I just show off my art?\n%s", BOT_ART);
    }
}
