import java.time.LocalTime;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static String msgWrap(String msg) {
        return "\t____________________________________________________________\n\t" +
                msg.replace("\n","\n\t") +
                "\n\t____________________________________________________________\n";
    }

    private static void welcome() {
        String daytime = dayTime();
        String msg = String.format("Good %s Sir, I am Apollo. \n" +
                "How can I help you on this wonderful %s? ", daytime, daytime.toLowerCase());
        System.out.println(msgWrap(msg));
    }

    private static String dayTime() {
        LocalTime now = LocalTime.now();
        // define the border values
        LocalTime eleven = LocalTime.of(11, 0);
        LocalTime four = LocalTime.of(4, 0);
        LocalTime fifteen = LocalTime.of(15, 0);
        LocalTime eighteenThirty = LocalTime.of(18, 30);

        // check if the time is after four and either before or exactly eleven
        if (now.isAfter(four) &&
                (now.isBefore(eleven) || now.equals(eleven)))
            return "Morning";
            // check if the time is after eleven and either before or exactly fifteen
        else if (now.isAfter(eleven) &&
                (now.isBefore(fifteen) || now.equals(fifteen)))
            return "Afternoon";
            // check if the time is after fifteen and either before or exactly eighteen thirty
        else if (now.isAfter(fifteen) &&
                (now.isBefore(eighteenThirty) || now.equals(eighteenThirty)))
            return "Evening";
            // otherwise, it's night
        else return "Night";
    }

    private static String readCmd() {
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        return cmd.toLowerCase();
    }

    private static void runCmd() {
        String cmd = readCmd();
        switch (cmd) {
            case "thank you":
                System.out.println(msgWrap("You're very much welcome. \n" +
                        "I am always available when you need me. "));
                return;
            default:
                System.out.println(msgWrap(cmd));
                runCmd();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        welcome();
        runCmd();
    }
}
