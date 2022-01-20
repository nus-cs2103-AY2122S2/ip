import java.util.Scanner;
import java.util.ArrayList;

public class Apollo {
    public static ArrayList<String> list;
    private static Scanner sc;
    private static int numCmd;

    public static void msgWrap(String msg) {
        System.out.println("\t________________________________________________________________________\n\t " +
                msg.replace("\n","\n\t ") +
                "\n\t________________________________________________________________________\n");
    }

    private static String readCmd() {
        return sc.nextLine();
    }

    private static void runCmd() {
        String cmd = readCmd();
        switch (cmd.toLowerCase().split(" ")[0]) {
            case "":
                runCmd();
                break;
            case "bye":
                msgWrap("See you next time. \n" +
                        "I am always available when you need me. ");
                break;
            case "list":
                msgWrap(String.join("\n", list));
                runCmd();
                break;
            case "mark": case "unmark":
                int n = Integer.parseInt(cmd.split(" ")[1]);
                if (n > list.size()) {
                    msgWrap("Please add more items first. ");
                } else if (cmd.startsWith("mark")) {
                    list.set(n-1, list.get(n-1).substring(0,3)+'X'+list.get(n-1).substring(4));
                    msgWrap(String.format("Alright I've marked this task as done: \n" +
                            "  %s", list.get(n-1).substring(2)));
                } else {
                    list.set(n-1, list.get(n-1).substring(0,3)+' '+list.get(n-1).substring(4));
                    msgWrap(String.format("Alright I've marked this task as not done yet: \n" +
                            "  %s", list.get(n-1).substring(2)));
                }
                runCmd();
                break;
            default:
                list.add(String.format("%d.[ ] %s", numCmd, cmd));
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
