import java.util.*;

public class Chat {

    /**
     * This Chat version is just supposed to take in the commands,
     * and return them, thats all. Mitran.Di.
     */

    public static String LINE_BREAK = "---------------";
    public static String GREETING = "FUNNY FELLA WEIIIIII! \nHow can I help Mr Singhhhhh?";
    public static String BYE = LINE_BREAK + "\n Byeeeee, come back again ah!\n" +
            LINE_BREAK;
    public static String LINE_INTRO = "Nah, here's your list";
    public static String MARK_MESSAGE = "Power la Mr Bosssssss, mark alr bro!";
    public static String UNMARK_MESSAGE = "No probs bro, unmarked already!";

    public static void main(String[] args) {

        // an array of tasks
        Task[] list = new Task[100];
        int counter = 0;
        System.out.println(GREETING);
        // new Data Structure to hold the tasks
        ArrayList<Task> tasks = new ArrayList<Task>();

        // takes in the incoming prompt
        Scanner sc = new Scanner(System.in);

        // Outputs
        while (true) {

            String input = sc.nextLine();

            // we will check from 0 to the first space
            // this will let us know of the command
            String[] command = input.split(" ");

            if (input.equals("bye")) {
                System.out.println(BYE);
                break;
            } else if (input.equals("list")) {

                System.out.println(LINE_BREAK);
                System.out.println("  " + LINE_INTRO);
                int internalCounter = 1;

                // iterate through the list
                for (Task item : list) {
                    if (item != null) {

                        String description = item.getDescription();
                        System.out.println(
                                " " + internalCounter + ". " + "[" + item.getStatusIcon() + "] " + description);
                        ++internalCounter;
                    } else {
                        break;
                    }
                }
                System.out.println(LINE_BREAK);
            } else if (command[0].equals("mark")) {

                int number = Integer.parseInt(command[1]) - 1;

                Task currTask = list[number];
                boolean currState = currTask.getIsDone();
                currTask.setDone(!currState);

                System.out.println(LINE_BREAK);
                System.out.println("   " + MARK_MESSAGE);
                System.out.println("        [X] " + currTask.getDescription());
                System.out.println(LINE_BREAK);
            } else if (command[0].equals("unmark")) {
                int number = Integer.parseInt(command[1]);

                Task currTask = list[number];
                boolean currState = currTask.getIsDone();
                currTask.setDone(!currState);

                System.out.println(LINE_BREAK);
                System.out.println("   " + UNMARK_MESSAGE);
                System.out.println("        [ ] " + currTask.getDescription());
                System.out.println(LINE_BREAK);
            } else {
                // here we declare the new task to be added
                Task t = new Task(input);

                list[counter] = t;
                System.out.println(LINE_BREAK);
                System.out.println(" ok added alr bro: " + input);
                System.out.println(LINE_BREAK);
                ++counter;
            }
        }
    }

}
