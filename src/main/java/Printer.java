public class Printer {
    private static final String logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String WELCOME_MESSAGE = logo + "\n"
            + "    ____________________________________________________________\n"
            + "     Hello! I'm SquishyBot" + "\n"
            + "     What can I do for you?" + "\n"
            + "    ____________________________________________________________\n";

    private static final String GOODBYE_MESSAGE = "\n"
            + "    ____________________________________________________________\n"
            + "     Bye. Hope to see you again soon!" + "\n"
            + "    ____________________________________________________________\n";

    private static final String BLANK_LINE = "    ____________________________________________________________\n";

    public static void welcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void exit() {
        System.out.println(GOODBYE_MESSAGE);
        System.exit(0);
    }

    public static void add(String input) {
        String temp = "\n" + BLANK_LINE + "added: " + input + "\n" + BLANK_LINE;
        System.out.println(temp);
    }

    public static void list(TaskList taskList) {
        System.out.println("\n" + BLANK_LINE);
        for (int i = 1; i < taskList.size() + 1; i++) {
            String curr = taskList.get(i - 1);
            String toPrint = "     " + i + "." + curr;
            System.out.println(toPrint);
        }
        System.out.println("\n" + BLANK_LINE);
    }
}
