import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exception.DukeException;

public class Duke {

    /**
     * This Chat version is just supposed to take in the commands,
     * and return them, thats all. Mitran.Di.CHatri. Ton. Udd. Gayi. Laundi.
     */

    public static String LINE_BREAK = "---------------";
    public static String GREETING = "FUNNY FELLA WEIIIIII! \nHow can I help Mr Singhhhhh?";
    public static String BYE = LINE_BREAK + "\n Byeeeee, come back again ah!\n" +
            LINE_BREAK;
    public static String LINE_INTRO = "Nah, here's your list";
    public static String MARK_MESSAGE = "Power la Mr Bosssssss, mark alr bro!";
    public static String UNMARK_MESSAGE = "No probs bro, unmarked already!";

    // reading from the duke.txt should only be done upon starting up the
    // application
    // and then updating everything to the duke.txt should only be done upon bye
    // command --> wil convert the tasks arrayList
    // to duke.txt appropriate data

    /**
     * Method to write to the duke.txt file.
     * 
     * @param filePath path of the file to write to
     * @param addText  text we want to add to the file
     */
    private static void writeToFile(String filePath, String addText) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(addText);
        fw.close();
    }

    /**
     * Method to append to the end of the present file.
     * 
     * @param filePath path of the file to write to
     * @param addText  text we want to add to the file
     */
    private static void appendToFile(String filePath, String addText) throws IOException {
        // adding the true argument allows
        // changes FileWriter to append mode
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(addText);
        fw.close();
    }

    /**
     * Using this method, we will delete the task identified on the linePos
     * provided.
     * 
     * @param filePath - path to perform CRUD operation on
     * @param linePos  - line number the task is on in duke.txt
     * @throws IOException
     */
    private static void deleteFromFile(String filePath, int linePos) throws IOException {
    }

    /**
     * Method to invoke when we want to edit the specified task.
     * 
     * @param filePath - path to perform CRUD operation on
     * @param linePos  - line number the task is on, in duke.txt
     * @throws IOException
     */
    private static void updateTask(String filePath, int linePos) throws IOException {
        // this method will mostly be used to update the marked/unmarked status of the
        // task, and reflect it in the duke.txt file
    }

    /**
     * Will read the lines in duke.txt, and will add each task to the
     * arrayList(tasks) in this file.
     */
    private static void refreshTasks() {

    }

    public static void main(String[] args) throws IOException, DukeException {

        // an array of tasks
        // Task[] list = new Task[100];
        int counter = 0;
        System.out.println(GREETING);
        // new Data Structure to hold the tasks
        ArrayList<Task> tasks = new ArrayList<Task>();

        // takes in the incoming prompt
        Scanner sc = new Scanner(System.in);

        // checks for the existence of data directory
        // and the duke.txt file
        File completeDirectory = new File("data/duke.txt");
        File dataFolder = new File("data");
        String dataPath = "data/duke.txt";

        // Check if the data directory exists.
        // Then create duke.txt if it doesnt.
        // if the folder and file exits, then we read the messages in there,
        // and update our tasks ArrayList.

        if (!completeDirectory.exists()) {
            if (!dataFolder.exists()) {
                // neither the full path nor the Folder exits
                // proceed to create the dataFolder first
                dataFolder.mkdir();
            }
            // here we will create the duke.txt file
            completeDirectory.createNewFile();
        } else {
            System.out.println("duke.txt already exists");
            // add the elements in the file to the tasks here

            // replacing the ArrayList Tasks
            tasks = ParseDukeTxt.readFile(completeDirectory);
        }

        // Outputs
        while (true) {

            String input = sc.nextLine();

            // we will check from 0 to the first space
            // this will let us know of the command
            String[] command = input.split(" ");

            if (input.equals("bye")) {
                // update the duke.txt file
                ParseDukeTxt.updateDukeTxt(dataPath, tasks);
                System.out.println(BYE);
                break;
            } else if (input.equals("list")) {

                System.out.println(LINE_BREAK);
                System.out.println("  " + LINE_INTRO);
                int internalCounter = 1;

                // iterate through the list
                for (Task task : tasks) {
                    if (task != null) {
                        System.out.println(" " + internalCounter + ". " + task);
                        ++internalCounter;
                    } else {
                        break;
                    }
                }
            } else if (command[0].equals("mark")) {

                int number = Integer.parseInt(command[1]) - 1;

                // Task currTask = list[number];
                Task currTask = tasks.get(number);
                boolean currState = currTask.getIsDone();
                currTask.setDone(!currState);

                System.out.println(LINE_BREAK);
                System.out.println("   " + MARK_MESSAGE);
                System.out.println("        [X] " + currTask.getDescription());
            } else if (command[0].equals("unmark")) {
                int number = Integer.parseInt(command[1]) - 1;

                // Task currTask = list[number];
                Task currTask = tasks.get(number);
                boolean currState = currTask.getIsDone();
                currTask.setDone(!currState);

                System.out.println(LINE_BREAK);
                System.out.println("   " + UNMARK_MESSAGE);
                System.out.println("        [ ] " + currTask.getDescription());
            } else if (command[0].equals("deadline")) {
                // deadline make some cups /by the day after
                if (command.length == 1) {
                    DukeException e = new DukeException("bro why la");
                    System.err.println(e.getMessage());
                } else {
                    String[] deadlineInput = input.split("/by");
                    String deadline = deadlineInput[1];
                    String left = deadlineInput[0];
                    String description = left.substring(9, left.length() - 1);
                    // create a new deadline
                    Task newTask = new Deadlines(description, deadline);
                    // adding to the array
                    // list[counter] = newTask;
                    tasks.add(newTask);
                    ++counter;
                    System.out.println(LINE_BREAK);
                    System.out
                            .println("Got it. I added this deadline already bro: \n" + " " + newTask.toString() + "\n");
                    System.out.println("Now you have " + counter + " tasks in the list. \n");
                }
            } else if (command[0].equals("event")) {

                // event project meeting /at Mon 2-4pm
                if (command.length == 1) {
                    DukeException e = new DukeException("OOPS!!! The description of an event cannot be empty.");
                    System.out.println(LINE_BREAK);
                    System.out.println(e.getMessage());
                } else {
                    String[] deadlineInput = input.split("/at");
                    String deadline = deadlineInput[1];
                    String left = deadlineInput[0];
                    String description = left.substring(6, left.length() - 1);
                    // create a new deadline
                    Task newTask = new Events(description, deadline);
                    // adding to the array
                    // list[counter] = newTask;
                    tasks.add(newTask);
                    ++counter;
                    System.out.println(LINE_BREAK);
                    System.out.println("Got it. I added this event already bro: \n" + " " + newTask.toString() + "\n");
                    System.out.println("Now you have " + counter + " tasks in the list. \n");
                }
            } else if (command[0].equals("todo")) {
                // here we declare the new task to be added (TODO)

                if (command.length == 1) {
                    DukeException e = new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(LINE_BREAK);
                    System.out.println(e.getMessage());
                } else {

                    Task t = new ToDos(input);

                    // list[counter] = t;
                    tasks.add(t);
                    System.out.println(LINE_BREAK);
                    System.out.println(" ok added alr bro: " + input);
                    ++counter;
                }
            } else if (command[0].equals("delete")) {
                // deleting a task
                // find the index to be deleted
                int number = Integer.parseInt(command[1]) - 1;
                // task being deleted
                Task beingDeleted = tasks.get(number);
                // deleting operation
                tasks.remove(number);
                System.out.println("Noted. I've removed this task: ");
                System.out.println("  " + beingDeleted);
                System.out.println("Now you have " + tasks.size() + " in the list.");
            } else {
                DukeException e = new DukeException("Tak faham banggg, speak in my language la bayi....");
                System.out.println(e.getMessage());
            }
        }
    }

}
