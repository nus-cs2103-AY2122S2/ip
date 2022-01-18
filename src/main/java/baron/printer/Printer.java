package baron.printer;

public class Printer {
    public static final String separator = "_____________________________________\n";

    public static void printWelcomeMessage() {
        String logo = "______                       \n" +
                "| ___ \\                      \n" +
                "| |_/ / __ _ _ __ ___  _ __  \n" +
                "| ___ \\/ _` | '__/ _ \\| '_ \\ \n" +
                "| |_/ / (_| | | | (_) | | | |\n" +
                "\\____/ \\__,_|_|  \\___/|_| |_|\n";
        String message = "What can I do for you?";
        System.out.println("Hello from\n" + logo + message);
    }

    public static void printCommandOutput(String commandOutput) {
        System.out.println(Printer.separator + commandOutput + "\n" + Printer.separator);
    }
}
