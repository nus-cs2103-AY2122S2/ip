import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<String> list;
    private static Scanner sc;
    private static int numCmd;

    public static void msgWrap(String msg) {
        System.out.println("\t________________________________________________________________________\n\t" +
                msg.replace("\n","\n\t") +
                "\n\t________________________________________________________________________\n");
    }

    private static String readCmd() {
        return sc.nextLine();
    }

    private static void runCmd() {
        String cmd = readCmd();
        switch (cmd.toLowerCase()) {
            case "":
                runCmd();
                return;
            case "thank you":
                msgWrap("You're very much welcome. \n" +
                        "I am always available when you need me. ");
                return;
            case "list":
                msgWrap(String.join("\n", list));
                runCmd();
                return;
            default:
                list.add(String.format("%d. %s", numCmd, cmd));
                numCmd++;
                msgWrap("added: " + cmd);
                runCmd();
        }
    }

    public static void main(String[] args) {
        list = new ArrayList<>();
        sc = new Scanner(System.in);
        numCmd = 1;
        Banner.welcomeMsg();
        runCmd();
    }
}
