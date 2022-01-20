import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Lily {
    private static final String indent = "    ";
    public static void main(String[] args) {
        boolean listening = true;
        // to be replaced
        // String[] list = new String[100];
        // boolean[] doneList = new boolean[100];
        Task[] list = new Task[100];
        // Arrays.fill(doneList, false);
        int listIdx = 0;

        // LinkedList<Task> list = new LinkedList<>();

        Scanner sc = new Scanner(System.in);

        // welcome message
        String logo = "\n" +
                "    ██╗     ██╗██╗     ██╗   ██╗\n" +
                "    ██║     ██║██║     ╚██╗ ██╔╝\n" +
                "    ██║     ██║██║      ╚████╔╝ \n" +
                "    ██║     ██║██║       ╚██╔╝  \n" +
                "    ███████╗██║███████╗   ██║   \n" +
                "    ╚══════╝╚═╝╚══════╝   ╚═╝   \n\n";

        String welcomeMessage = logo;
        welcomeMessage += indent + "hey.\n"
        + indent + "i don't really wanna track your tasks,\n"
        + indent + "but i guess i have no choice (っ◞‸◟c)\n"
        + indent + "need help with something?\n"
        + "\n"
        + indent + "Commands you can type\n"
        + indent + "> bye: stop talking with Lily\n";
        prettyPrint(welcomeMessage);

        // Lily accepts inputs from user
        userInteracting: while (listening) {
            String sentence = sc.nextLine();
            String[] parsedSentence = sentence.split(" ");
            String action = parsedSentence[0];
            switch(action) {
                // throw error if input doesn't match enums later on
                case "bye":
                    listening = false;
                    prettyPrint("finally. what took you so long? (´-ω-`)");
                    break userInteracting;

                case "list":
                    // throw error if list is empty
                    String todo = "";
                    for (int i = 0; i < listIdx; i++) {
                        todo += ("    " + (i + 1) + "."        // indent, 1.
                        + list[i].getStatusIcon()        // whether it is done 
                        + list[i].getDesc());                  // the task
                        todo += i == (listIdx - 1) ? "" : "\n";
                    }
                    prettyPrint("you told me you had to\n" + todo);
                    break;

                case "mark":
                    int addIdx = Integer.parseInt(parsedSentence[1]) - 1;
                    // throw error if out of bounds
                    list[addIdx].mark();
                    String message = "oh. you've finished it. okay\n"
                    + indent + (addIdx + 1) + "." 
                    + list[addIdx].getStatusIcon()  // whether it is done 
                    + list[addIdx].getDesc();                               // the task
                    prettyPrint(message);
                    break;

                case "unmark":
                    int removeIdx = Integer.parseInt(parsedSentence[1]) - 1;
                    // throw error if out of bounds
                    list[removeIdx].unmark();
                    String unMessage = "hey, you gotta get it done later okay?\n"
                    + indent + (removeIdx + 1) + "." 
                    + list[removeIdx].getStatusIcon()  // whether it is done 
                    + list[removeIdx].getDesc();                               // the task
                    prettyPrint(unMessage);
                    break;

                default:
                    list[listIdx] = new Task(sentence);
                    listIdx++;
                    prettyPrint("i've dumped \"" + sentence + "\" into your list\n"
                    + "\n"
                    + indent + "hope you're happy");
            }
        }
        sc.close();
    }

    protected static void prettyPrint(String s) {
        System.out.println("\n"
        + indent + "▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n"
        + indent + s + "\n"
        + indent + "▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n");
    }
}
