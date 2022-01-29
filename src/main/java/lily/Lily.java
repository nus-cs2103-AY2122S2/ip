package lily;
import lily.task.Deadline;
import lily.task.Event;
import lily.task.LilyException;
import lily.task.Task;
import lily.task.Todo;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Main class which runs an interactive CLI-based chatbot which manages your todos
 * 
 * @author Hong Yi En, Ian
 * @version Jan 2022 (AY21/22 Sem 2)
 */
public class Lily {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Lily(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LilyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(tasks, ui, storage);
    }

    public void run() {
        ui.showWelcome(tasks);
        this.parser.parse(); // executes commands also
    }

    public static void main(String[] args) {
        new Lily("data/tasks.txt").run();
    }
}

class OldLily {
    private static final String indent = "    ";
    private static LinkedList<Task> list;

    private enum Cmd {
        EXIT, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, REMOVE, INVALID
    }

    private static LinkedList<Task> load(String filePath) throws IOException {
        LinkedList<Task> result = new LinkedList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            @SuppressWarnings("unchecked")
            LinkedList<Task> read = (LinkedList<Task>) ois.readObject();

            ois.close();
            fis.close();
            result = read;
        } catch (ClassNotFoundException c) {
           System.out.println("Class not found");
            c.printStackTrace();
        } 
        return result;
    }

    private static void save(String filePath, LinkedList<Task> list) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
        fos.close();
    }

    public static void main(String[] args) {
        String saveFilePath = "data/list.txt";
        Scanner userInput = new Scanner(System.in);

        // welcome message
        String logo = "\n" +
                "    ██╗     ██╗██╗     ██╗   ██╗\n" +
                "    ██║     ██║██║     ╚██╗ ██╔╝\n" +
                "    ██║     ██║██║      ╚████╔╝ \n" +
                "    ██║     ██║██║       ╚██╔╝  \n" +
                "    ███████╗██║███████╗   ██║   \n" +
                "    ╚══════╝╚═╝╚══════╝   ╚═╝   \n\n";
        String welcomeMessage = logo;

        // load saved state if any
        try {
            // import saved data to list
            list = load(saveFilePath);

            // welcome user back
            welcomeMessage += indent + "sup, welcome back\n"
                    + indent + "here's your list from the last time\n"
                    + "\n"
                    + printList();
        } catch (IOException e) {
            File dataFolder = new File("/data");
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }
            list = new LinkedList<>();

            // welcome new user message
            errorPretty(indent + "oh bother, another new one");
            welcomeMessage += indent + "hey.\n"
                    + indent + "i don't really wanna track your tasks,\n"
                    + indent + "but i guess i have no choice (っ◞‸◟c)\n"
                    + indent + "need help with something?\n"
                    + "\n"
                    + indent + "Things you can type\n" + listCommands();
        } finally {
            prettyPrint(welcomeMessage);
        }


        // Lily accepts inputs from user
        userInteracting: while (true) {
            String sentence = userInput.nextLine();
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
            case EXIT:
                String exitMsg = "finally. what took you so long? (´-ω-`)\n";
                try {
                    if (!list.isEmpty()) {
                        save(saveFilePath, list);
                        exitMsg += indent + "oh, i've saved your list as well\n"
                                + indent + "i'll bring it up for you next time. bye";
                    }
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                prettyPrint(exitMsg);
                break userInteracting;

            case LIST:
                if (list.isEmpty()) {
                    errorPretty("there's nothing in the list bro");
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
                    * if input doesn't have an int, ask which number you want to mark also.
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
                    * if input doesn't have an int, ask which number you want to mark also.
                    */
                list.get(delIdx).unmark();
                String unmarkMsg = "hey, you gotta get it done later, okay?\n"
                        + indent + (delIdx + 1) + "."
                        + list.get(delIdx).toString();
                prettyPrint(unmarkMsg);
                break;

            case TODO:
                try {
                    Todo t = new Todo(findTodoDescStart(sentence));
                    list.add(t);
                    taskAddedMsg(t);
                } catch (LilyException le) {
                    errorPretty("Todo description cannot be empty!");
                }
                break;
            case DEADLINE:
                /*
                    if user didn't type "/by" (byIdx == -1)
                        throw new Error "you didnt' type /by bro, try again"
                    if user didnt' type a desc
                        throw new error you didnt type a description man, try again
                */
                try {
                    String[] parsedDeadline = findDeadlineDescStart(sentence);
                    Deadline d = new Deadline(parsedDeadline[0], parsedDeadline[1]);
                    list.add(d);
                    taskAddedMsg(d);
                } catch (LilyException le) { // need catch "no-/by" error
                    errorPretty("Deadline description cannot be empty!");
                }
                break;
            // broken, can't detect /at
            case EVENT:
                /*
                    if user didn't type "/at" (atIdx == -1)
                        throw new Error "you didnt' type /at bro, try again"
                    if user didnt' type a desc
                        throew new error you didnt type a description man, try again
                */
                try {
                    String[] parsedEvent = findEventDescStart(sentence);
                    Event e = new Event(parsedEvent[0], parsedEvent[1]);
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
        userInput.close();
    }

    private static String findTodoDescStart(String s) throws LilyException {
        try {
            return s.substring(5); // "todo " is 5 char long
        } catch (StringIndexOutOfBoundsException e) {
            throw new LilyException(e.getMessage());
        }
    }

    private static String[] findDeadlineDescStart(String s) throws LilyException {
        String[] result = new String[2];
        try {
            result[0] = s.substring(9, s.indexOf("/by") - 1);
            result[1] = s.substring(s.indexOf("/by") + 4);
            return result;
        } catch (StringIndexOutOfBoundsException e) {
            throw new LilyException(e.getMessage());
        }
    }
    
    private static String[] findEventDescStart(String s) throws LilyException {
        String[] result = new String[2];
        try {
            result[0] = s.substring(6, s.indexOf("/at") - 1);
            result[1] = s.substring(s.indexOf("/at") + 4); // different exception is thrown if indexOf cant find /at
            return result;
        } catch (StringIndexOutOfBoundsException e) {
            throw new LilyException(e.getMessage());
        }
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
