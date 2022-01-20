// import java.io.*;
import java.util.Scanner;

public class Lily {
    public static void main(String[] args) {
        boolean listening = true;
        String[] list = new String[100];
        int listIdx = 0;

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
        System.out.println("\n    If you type anything else, I'll repeat it back to you."); // update this when new functionality is added
        System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n");

        // case statements
        userInteracting: while (listening) {
            String action = sc.nextLine();
            switch(action) {
                case "bye":
                    listening = false;
                    System.out.println("");
                    System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼");
                    System.out.println("    see ya.");
                    // System.out.println("    hmph.");
                    System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n");
                    break userInteracting;
                case "list":
                    String todo = "";
                    for (int i = 0; i < listIdx; i++) {
                        todo += ("    " + (i + 1) + ". " + list[i]);
                        todo += i == (listIdx - 1) ? "" : "\n";
                    }
                    System.out.println("");
                    System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼");
                    System.out.println("    You told me you had to");
                    System.out.println(todo);
                    System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n");
                    break;
                default:
                    list[listIdx] = action;
                    listIdx++;
                    System.out.println("");
                    System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼");
                    System.out.println("    i've dumped your \"" + action + "\" into your list");
                    System.out.println("    you happy?");
                    System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n");
            }
        }
        sc.close();
    }
}
