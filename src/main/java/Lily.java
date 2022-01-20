// import java.io.*;
import java.util.Scanner;

public class Lily {
    public static void main(String[] args) {
        boolean listening = true;
        Scanner sc = new Scanner(System.in);

        System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼");
        String logo = "\n" +
                "    ██╗     ██╗██╗     ██╗   ██╗\n" +
                "    ██║     ██║██║     ╚██╗ ██╔╝\n" +
                "    ██║     ██║██║      ╚████╔╝ \n" +
                "    ██║     ██║██║       ╚██╔╝  \n" +
                "    ███████╗██║███████╗   ██║   \n" +
                "    ╚══════╝╚═╝╚══════╝   ╚═╝   \n\n";
        System.out.println(logo);
        System.out.println("    Hey.\n" +
                "    Need help with something?\n");
        System.out.println("    Commands you can type");
        System.out.println("    > bye: stop talking with Lily");
        System.out.println("\n    If you type anything else, I'll repeat it back to you.");
        System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n");

        // case statements
        while (listening) {
            String action = sc.nextLine();
            if (action.equals("bye")) {
                listening = false;
                System.out.println("");
                System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼");
                System.out.println("    see ya.");
                System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n");
                break;
            } else {
                System.out.println("");
                System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼");
                System.out.println("    " + action);
                System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n");
            }
        }
        sc.close();
    }
}
