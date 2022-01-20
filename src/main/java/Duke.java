import java.util.Scanner; //import Scanner
import java.util.ArrayList; //import ArrayList

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String dukeGreeting = "Hello! I'm Duke \nWhat can I do for you?";
        boolean endChat = false;
        String endMessage = "Bye. Hope to see you again soon!";

        //arraylist to store the list of tasks
        ArrayList<String> arrayLst = new ArrayList<>();

        System.out.println(dukeGreeting);
        levelTwoRespond(arrayLst);
        System.out.println(endMessage);


    }

    /**
     * Method that handles the response for Level-1.
     */
    public static void levelOneRespond () {
        Scanner sc = new Scanner(System.in);
        String bye = "bye";
        String dukeMessage = "Duke: ";
        String endMessage = "Bye. Hope to see you again soon!";
        String input = sc.nextLine();
        if (!input.equals(bye)) {
            System.out.println(dukeMessage + input);
            levelOneRespond();
        }
    }

    /**
     * Method that handles the response for Level-2. Includes Add and List
     * @param arrayLst arraylist that stores the String entries for add and can be listed out.
     */
    public static void levelTwoRespond (ArrayList<String> arrayLst) {
        Scanner sc = new Scanner(System.in);
        String bye = "bye";
        String lst = "list";
        String input = sc.nextLine();
        if (input.equals(bye));
        else if (input.equals(lst)) {
            int i = 0;
            for (String item : arrayLst) {
                i += 1;
                System.out.println(i + ". " + item);
            }
            levelTwoRespond(arrayLst);
        } else {
            String added = "added: ";
            arrayLst.add(input);
            System.out.println(added + input);
            levelTwoRespond(arrayLst);
        }
    }
}
