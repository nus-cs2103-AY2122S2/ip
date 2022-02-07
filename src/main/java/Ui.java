public class Ui {
    private static final String SEG_LINE = "    ____________________________________________________________";
    private static final String INDENT = "    ";
    private static final String LOGO = INDENT + " ____        _        \n"
            + INDENT + "|  _ \\ _   _| | _____ \n"
            + INDENT + "| | | | | | | |/ / _ \\\n"
            + INDENT + "| |_| | |_| |   <  __/\n"
            + INDENT + "|____/ \\__,_|_|\\_\\___|\n";

    private final String name;

    public Ui(String name) {
        this.name = name;
    }

    public void boot() {
        System.out.println(LOGO);
    }

    public void start() {
        System.out.println(SEG_LINE);
        System.out.println(INDENT + "Hello! I'm " + name + ".");
        System.out.println(INDENT + "What can I do for you?");
        System.out.println(SEG_LINE);
    }
}
