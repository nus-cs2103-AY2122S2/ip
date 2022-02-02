import java.util.*;

public class Duke {

    /**
     * This Chat version is just supposed to take in the commands,
     * and return them, thats all. Mitran.Di.CHatri. Ton. Udd. Gayi. Laundi. ah.
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
                for (Task task : list) {
                    if (task != null) {
                        System.out.println(" " + internalCounter + ". " + task);
                        ++internalCounter;
                    } else {
                        break;
                    }
                }
            } else if (command[0].equals("mark")) {

                int number = Integer.parseInt(command[1]) - 1;

                Task currTask = list[number];
                boolean currState = currTask.getIsDone();
                currTask.setDone(!currState);

                System.out.println(LINE_BREAK);
                System.out.println("   " + MARK_MESSAGE);
                System.out.println("        [X] " + currTask.getDescription());
            } else if (command[0].equals("unmark")) {
                int number = Integer.parseInt(command[1]) - 1;

                Task currTask = list[number];
                boolean currState = currTask.getIsDone();
                currTask.setDone(!currState);

                System.out.println(LINE_BREAK);
                System.out.println("   " + UNMARK_MESSAGE);
                System.out.println("        [ ] " + currTask.getDescription());
            } else if (command[0].equals("deadline")) {
                // deadline make some cups /by the day after

                String[] deadlineInput = input.split("/by");
                String deadline = deadlineInput[1];
                String left = deadlineInput[0];
                String description = left.substring(9, left.length() - 1);
                // create a new deadline
                Task newTask = new Deadlines(description, deadline);
                // adding to the array
                list[counter] = newTask;
                ++counter;
                System.out.println(LINE_BREAK);
                System.out.println("Got it. I added this deadline already bro: \n" + " " + newTask.toString() + "\n");
                System.out.println("Now you have " + counter + " tasks in the list. \n");
            } else if (command[0].equals("event")) {

                // event project meeting /at Mon 2-4pm

                String[] deadlineInput = input.split("/at");
                String deadline = deadlineInput[1];
                String left = deadlineInput[0];
                String description = left.substring(6, left.length() - 1);
                // create a new deadline
                Task newTask = new Events(description, deadline);
                // adding to the array
                list[counter] = newTask;
                ++counter;
                System.out.println(LINE_BREAK);
                System.out.println("Got it. I added this event already bro: \n" + " " + newTask.toString() + "\n");
                System.out.println("Now you have " + counter + " tasks in the list. \n");
            } else {
                // here we declare the new task to be added (TODO)
                Task t = new ToDos(input);

                list[counter] = t;
                System.out.println(LINE_BREAK);
                System.out.println(" ok added alr bro: " + input);
                ++counter;
            }
        }
    }

}
