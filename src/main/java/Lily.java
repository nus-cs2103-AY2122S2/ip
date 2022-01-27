import java.util.LinkedList;
import java.util.Scanner;

/**
 * Author: Hong Yi En, Ian
 * Last Updated: 23 Jan 2022 (Year 2 Sem 2)
 * 
 * An interactive CLI-based chatbot to store todos
 */
public class Lily {
    private static final String indent = "    ";
    private static LinkedList<Task> list;

    private enum Cmd {
        EXIT, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, REMOVE, INVALID
    }

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
            String command = parsedSentence[0];
            Cmd action; 
            switch (command) {
                case "bye":
                    action = Cmd.EXIT;
                    break;
                case "list":
                    action = Cmd.LIST;
                    break;
                case "mark":
                    action = Cmd.MARK;
                    break;
                case "unmark":
                    action = Cmd.UNMARK;
                    break;
                case "todo":
                    action = Cmd.TODO;
                    break;
                case "deadline":
                    action = Cmd.DEADLINE;
                    break;
                case "event":
                    action = Cmd.EVENT;
                    break;
                case "delete":
                case "remove":
                    action = Cmd.REMOVE;
                    break;
                default:
                    action = Cmd.INVALID;
            }

            switch (action) {
                // throw error if input doesn't match enums later on
                case EXIT:
                    prettyPrint("finally. what took you so long? (´-ω-`)");
                    break userInteracting;

                case LIST:
                    // throw error instead
                    if (list.isEmpty()) {
                       prettyPrint("there's nothing in the list bro");
                    } else {
                        prettyPrint("you told me you had to\n" + printList());
                    }
                    break;

                case MARK:
                    int addIdx = Integer.parseInt(parsedSentence[1]) - 1;
                    /*
                     * if (list.isEmpty())
                     * throw new Error("you cant mark something that isn't there");
                     * else if (already marked)
                     * throw new error you've already finished this
                     */
                    list.get(addIdx).mark();
                    String markMsg = "oh. you've finished it. okay\n"
                            + indent + (addIdx + 1) + "."
                            + list.get(addIdx).toString();
                    prettyPrint(markMsg);
                    break;

                case UNMARK:
                    int delIdx = Integer.parseInt(parsedSentence[1]) - 1;
                    /*
                     * if (list.isEmpty())
                     * throw new Error("you can't unmark something thaj isn't there");
                     * else if (not marked yet)
                     * throw new error you havent done this
                     */
                    list.get(delIdx).unmark();
                    String unmarkMsg = "hey, you gotta get it done later, okay?\n"
                            + indent + (delIdx + 1) + "."
                            + list.get(delIdx).toString();
                    prettyPrint(unmarkMsg);
                    break;

                case TODO:
                    try {
                        Todo t = new Todo(sentence);
                        list.add(t);
                        taskAddedMsg(t);
                    } catch (LilyException le) {
                        errorPretty("Todo description cannot be empty!");
                    }
                    break;
                case DEADLINE:
                    try {
                        Deadline d = new Deadline(sentence);
                        list.add(d);
                        taskAddedMsg(d);
                    } catch (LilyException le) { // need catch "no-/by" error
                        errorPretty("Deadline description cannot be empty!");
                    }
                    break;
                case EVENT:
                    try {
                        Event e = new Event(sentence);
                        list.add(e);
                        taskAddedMsg(e);
                    } catch (LilyException le) { // need catch "no-/at" error
                        errorPretty("Event description cannot be empty!");
                    }
                    break;

                case REMOVE:
                    try {
                        prettyPrint("hmph. then why did you make me track your\n"
                                + indent + list.remove(Integer.parseInt(parsedSentence[1]) - 1)
                                + "\n"
                                + indent + "anyway, now you're left with\n" + printList());
                    } catch (IndexOutOfBoundsException e) { // new error: catch no int input also
                        errorPretty("eh bro your list is shorter than that");
                    }
                    break;

                default:
                    errorPretty("sorry i don't understand what you meant by\n\n"
                            + indent + sentence + "\n\n"
                            + indent + "you can try these instead:\n" + listCommands());
            }
        }
        sc.close();
    }

    protected static String printList() {
        String listMsg = "";
        int i = 1;
        for (Task t : list) {
            listMsg += indent + i + "."
                    + t.toString()
                    + (i == list.size() ? "" : "\n");
            i++;
        }
        return listMsg;
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

    protected static void taskAddedMsg(Task t) {
        String no = list.size() == 1 ? " task " : " tasks ";
        prettyPrint("i've dumped this into your list:\n"
                + indent + t.toString() + "\n"
                + indent + "so now you have " + list.size() + no + "happening. hope you're happy");
    }

    protected static void prettyPrint(String s) {
        System.out.println("\n"
                + indent + "▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n"
                + indent + s + "\n"
                + indent + "▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n");
    }

    protected static void errorPretty(String s) {
        System.err.println("\n"
                + indent + "▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n"
                + indent + s + "\n"
                + indent + "▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼\n");
    }
}
