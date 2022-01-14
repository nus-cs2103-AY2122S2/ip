import java.util.Scanner;

public class Duke {
    public static final String BOT_NAME = "Luke";
    private static final String LOGO = "  _           _        \n"
            +" | |         | |       \n"
            +" | |    _   _| | _____ \n"
            +" | |   | | | | |/ / _ \\\n"
            +" | |___| |_| |   <  __/\n"
            +" |______\\__,_|_|\\_\\___|\n";
    ;

    public static void greeting() {
        System.out.println("Hello! I am \n" + LOGO);
        System.out.println("How can I help you?");
        System.out.println("============================================================");
    }

    public static void farewell() {
        System.out.println("============================================================");
        System.out.println("I'll take my leave... Before I go, do you know who is my father?");
        System.out.println("============================================================");
    }

    public static void main(String[] args) {
        Command cmd = new Command();
        Scanner sc = new Scanner(System.in);
        greeting();
        do {
            cmd = cmd.parseCommand(sc.nextLine());
        } while(!cmd.isExitCmd());
        farewell();
    }
}
