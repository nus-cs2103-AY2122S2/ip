public class Ui {
    private static final String NORMAL_SPACE = "      ";
    private static final String TASK_SPACE = "        ";
    private static final String LOGO =
            " _____           _ _   \n" +
            "|  ___|         (_) |   \n" +
            "| |__ _ __   ___ _| | __\n" +
            "|  __| '_ \\ / __| | |/ /\n" +
            "| |__| | | | (__| |   < \n" +
            "\\____/_| |_|\\___|_|_|\\_\\";
    private static final String BORDER = "   ____________________________________________________________";

    public static void welcome() {
        System.out.println("GOOD MORNING GENNERMEN from");
        System.out.println(LOGO);
        printBorder();
        print("GOOD MORNING CHAO RECRUIT! YOU MAY CALL ME ENCIK ENCIK");
        print("WHAT YOU WANT?");
        printBorder();
    }

    public static void printBorder() {
        System.out.println(BORDER);
    }

    public static void print(String input) {
        System.out.println(NORMAL_SPACE + input);
    }

    public static void print(Task taskInput) {
        System.out.println(TASK_SPACE + taskInput.toString());
    }
}
