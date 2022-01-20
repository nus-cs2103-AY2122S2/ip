import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<String> list = new ArrayList<String>();

    public static String handle(String command) {
        if (command.equals("bye")) {
            return "See you later alligator :)\n";
        } else if (command.equals("list")) {
            for (Integer i = 0; i < list.size(); i++) {
                System.out.println(i.toString() + " " + list.get(i));
            }
            return "";
        } else {
            list.add(command);
            return "added: " + command;
        }
    }

    public static void main(String[] args) {

        String prince = "______       _\n"
                +  "| ___ \\     (_)\n"
                + "| |_/ /_ __  _  _ __    ___  ___\n"
                + "|  __/| '__|| || '_ \\  / __|/ _ \\ \n"
                + "| |   | |   | || | | || (__|  __/\n"
                + "\\_|   |_|   |_||_| |_| \\___|\\___|\n";

        String divider = "~~~~~~~~~~~~~~~~~~~~~~~\n";

        System.out.println("Hello I'm\n" + prince);
        System.out.println("How can I help you today?");
        System.out.println(divider);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            System.out.println(handle(command) + "\n");
            if (command.equals("bye")) {
                break;
            }
        }
    }
}
