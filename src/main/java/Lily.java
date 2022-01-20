import java.util.Arrays;
import java.util.Scanner;

public class Lily {
    public static void main(String[] args) {
        boolean listening = true;
        String[] list = new String[100];
        boolean[] doneList = new boolean[100];
        Arrays.fill(doneList, false);
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
            String sentence = sc.nextLine();
            String[] parsedSentence = sentence.split(" ");
            String action = parsedSentence[0];
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
                        todo += ("    " + (i + 1) + "."          // indent, 1.
                        + "[" + (doneList[i] ? "X" : " ") + "] "  // whether it is done 
                        + list[i]);                              // the task
                        todo += i == (listIdx - 1) ? "" : "\n";
                    }
                    System.out.println();
                    System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼");
                    System.out.println("    You told me you had to");
                    System.out.println(todo);
                    System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n");
                    break;

                case "mark":
                    int addIdx = Integer.parseInt(parsedSentence[1]) - 1;
                    // throw error if out of bounds
                    doneList[addIdx] = true;
                    System.out.println();
                    System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼");
                    System.out.println("    right. now that thing's done");
                    System.out.println("    " + (addIdx + 1) + "."     // indent, 1
                        + "[" + (doneList[addIdx] ? "X" : " ") + "] "  // whether it is done 
                        + list[addIdx]);                               // the task
                    System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n");
                    break;

                case "unmark":
                    int removeIdx = Integer.parseInt(parsedSentence[1]) - 1;
                    // throw error if out of bounds
                    doneList[removeIdx] = false;
                    System.out.println();
                    System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼");
                    System.out.println("    hey, you gotta get it done later okay?");
                    System.out.println("    " + (removeIdx + 1) + "."     // indent, 1
                        + "[" + (doneList[removeIdx] ? "X" : " ") + "] "  // whether it is done 
                        + list[removeIdx]);                               // the task
                    System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n");
                    break;

                default:
                    list[listIdx] = sentence;
                    listIdx++;
                    System.out.println();
                    System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼");
                    System.out.println("    i've dumped your \"" + sentence + "\" into your list");
                    System.out.println("    you happy?");
                    System.out.println("    ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n");
            }
        }
        sc.close();
    }
}
