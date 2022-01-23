import java.util.LinkedList;
import java.util.Scanner;

public class Lily {
    private static final String indent = "    ";
    private static LinkedList<Task> list;
    public static void main(String[] args) {
        list = new LinkedList<>();
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
        userInteracting: while (true) {
            String sentence = sc.nextLine();
            String[] parsedSentence = sentence.split(" ");
            // add functionality to detect the /by and /at
            String action = parsedSentence[0];
            switch(action) {
                // throw error if input doesn't match enums later on
                case "bye":
                    prettyPrint("finally. what took you so long? (´-ω-`)");
                    break userInteracting;

                case "list":
                    /*
                    if (list.isEmpty())
                        throw new Error("There's nothing in the list bro");
                        */
                    String listMsg = "";
                    int i = 1;
                    for (Task t : list) {
                        listMsg += indent + i + "."
                        + t.toString()
                        + (i == list.size() ? "" : "\n");
                        i++;
                    }
                    prettyPrint("you told me you had to\n" + listMsg);
                    break;

                case "mark":
                    int addIdx = Integer.parseInt(parsedSentence[1]) - 1;
                    /*
                    if (list.isEmpty())
                        throw new Error("you cant mark something that isn't there");
                        */
                    list.get(addIdx).mark();
                    String markMsg = "oh. you've finished it. okay\n"
                    + indent + (addIdx + 1) + "." 
                    + list.get(addIdx).toString();
                    prettyPrint(markMsg);
                    break;

                case "unmark":
                    int delIdx = Integer.parseInt(parsedSentence[1]) - 1;
                    /*
                    if (list.isEmpty())
                        throw new Error("you can't mark something thaj isn't there");
                        */
                    list.get(delIdx).unmark();
                    String unmarkMsg = "hey, you gotta get it done later, okay?\n"
                    + indent + (delIdx + 1) + "." 
                    + list.get(delIdx).toString();
                    prettyPrint(unmarkMsg);
                    break;

                /*
                case "todo":
                    desc from 2nd word onwards
                    list.add(new Todo(sentence))
                    addTaskMsg(sentence);
                    break;
                case "deadline":
                    desc from 2nd word onwards to the /by
                    by afterwards
                    list.add(new Deadline(sentence))
                    addTaskMsg(sentence);
                    break;
                case "event":
                    desc from 2nd word onwards to the /at
                    at afterwards
                    list.add(new Event(sentence))
                    addTaskMsg(sentence);
                    break;
                */

                default:
                    prettyPrint("sorry i don't understand what you want me to do. you can try these instead:");
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

    protected static void addTaskMsg(String s) {
        prettyPrint("i've dumped \"" + s + "\" into your list\n"
            + indent + "so now you have " + list.size() + " tasks happening"
            + "\n"
            + indent + "hope you're happy");
    }
}
