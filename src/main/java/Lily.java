import java.util.LinkedList;
import java.util.Scanner;

/**
 * Author: Hong Yi En, Ian
 * Last Updated: 23 Jan 2022
 * 
 * An interactive CLI-based chatbot to store todos
 */
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
        + indent + "Things you can type\n" + listCommands();
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
                    if (list.isEmpty()) {
                        prettyPrint("there's nothing in the list bro");
                    } else {
                        String listMsg = "";
                        // Add items in the list to the string
                        int i = 1;
                        for (Task t : list) {
                            listMsg += indent + i + "."
                            + t.toString()
                            + (i == list.size() ? "" : "\n");
                            i++;
                        }
                        prettyPrint("you told me you had to\n" + listMsg);
                    }
                    break;

                case "mark":
                    int addIdx = Integer.parseInt(parsedSentence[1]) - 1;
                    /*
                    if (list.isEmpty())
                        throw new Error("you cant mark something that isn't there");
                    else if (already marked)
                        throw new error you've already finished this
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
                        throw new Error("you can't unmark something thaj isn't there");
                    else if (not marked yet)
                        throw new error you havent done this
                        */
                    list.get(delIdx).unmark();
                    String unmarkMsg = "hey, you gotta get it done later, okay?\n"
                    + indent + (delIdx + 1) + "." 
                    + list.get(delIdx).toString();
                    prettyPrint(unmarkMsg);
                    break;

                case "todo":
                    String todoDesc = sentence.substring(5); // "todo " is 5 char long
                    Todo t = new Todo(todoDesc);
                    list.add(t);
                    taskAddedMsg(t);
                    break;
                case "deadline":
                    /*
                        if user didn't type "/by" (byIdx == -1)
                            throw new Error "you didnt' type /by bro, try again"
                    */
                    int byIdx = sentence.indexOf("/by");
                    Deadline d = new Deadline(sentence.substring(9, byIdx - 1), 
                                              sentence.substring(byIdx + 4));
                    list.add(d);
                    taskAddedMsg(d);
                    break;
                case "event":
                    /*
                        if user didn't type "/at" (atIdx == -1)
                            throw new Error "you didnt' type /at bro, try again"
                    */
                    int atIdx = sentence.indexOf("/at");
                    Event e = new Event(sentence.substring(6, atIdx - 1), 
                                        sentence.substring(atIdx + 4));
                    list.add(e);
                    taskAddedMsg(e);
                    break;

                default:
                    prettyPrint("sorry i don't understand what you meant by\n\n"
                    + indent + sentence + "\n\n"
                    + indent + "you can try these instead:\n" + listCommands());
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

    protected static void taskAddedMsg(Task t) {
        String no = list.size() == 1 ? " task " : " tasks ";
        prettyPrint("i've dumped this into your list:\n"
            + indent + t.toString() + "\n"
            + indent + "so now you have " + list.size() + no + "happening. hope you're happy");
    }

    protected static String listCommands() {
        return indent + "> todo: record a task which has no date\n"
             + indent + "> event: note an event with its date after /at\n"
             + indent + "> deadline: note something with its date after /by\n"
             + indent + "> list: show what you have to do\n"
             + indent + "> mark: indicate which numbered task you completed\n"
             + indent + "> unmark: indicate which task you havent completed\n"
             + indent + "> bye: stop talking with Lily";
    }
}
