import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int numOfTask = 0;
    private static final int EVENT_OFFSET = 5;
    private static final int TODO_OFFSET = 4;
    private static final int DEADLINE_OFFSET = 8;
    private static final int INPUT_OFFSET = 3;

    public static void addToList(Task t){
        String message = "Got it. I've added this task:\n";
        taskList.add(t);
        numOfTask++;
        System.out.println(message + t.toString() + "\nNow you have " + numOfTask + " tasks in the list.");
    }

    public static void deleteTask(int taskNum) {
        String message = "Noted. I've removed this task:\n";
        int actualTaskNum = taskNum - 1;
        System.out.println(message + taskList.get(actualTaskNum).toString());
        taskList.remove(actualTaskNum);
        numOfTask--;
        System.out.println("Now you have " + numOfTask + " tasks in the list.");
    }

    public static void markTask(int taskNum) {
        String message = "Nice! I've marked this task as done:\n" ;
        int actualTaskNum = taskNum - 1; //minus 1 as list index is from 0
        Task t = taskList.get(actualTaskNum); // get the task from the array
        t.setTaskDone();
        System.out.println(message + t.toString());
    }

    public static void unMarkTask(int taskNum) {
        String message = "OK, I've marked this task as not done yet:\n";
        int actualTaskNum = taskNum - 1;
        Task t = taskList.get(actualTaskNum); // get the task from the array
        t.setTaskNotDone();
        System.out.println(message + t.toString());
    }

    public static void printList(){
        String message = "Here are the tasks in your list:";
        System.out.println(message);

        for(int i = 0; i < numOfTask; i++){
            String output = i + 1 + "." + taskList.get(i).toString();
            System.out.println(output);
        }
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm TaskJamie\nWhat can i do for you?";
        String ending =  "Bye. Hope to see you again soon!";
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            try {
                String input = sc.nextLine();

                if(input.length() == 0) {
                    throw new BlankCommandException();
                }

                String[] inputSplit = input.split(" "); //split input by space
                String command = inputSplit[0];

                if (command.equals("bye")) {
                    System.out.print(ending);
                    break;

                } else if (command.equals("list")) {
                    printList();

                } else if (command.equals("todo")) {
                    String description = input.substring(TODO_OFFSET);

                    if(description.length() == 0) {
                        throw new IncompleteCommandException(command);
                    }

                    addToList(new Todo(description));

                } else if (command.equals("deadline")) {
                    String[] inputSlash = input.split("/");
                    String description = inputSlash[0].substring(DEADLINE_OFFSET);

                    if(description.length() == 0) {
                        throw new IncompleteCommandException(command);
                    }

                    String time = inputSlash[1].substring(INPUT_OFFSET);
                    addToList(new Deadline(description, time));

                } else if (command.equals("event")) {
                    String[] inputSlash = input.split("/");
                    String description = inputSlash[0].substring(EVENT_OFFSET);

                    if(description.length() == 0) {
                        throw new IncompleteCommandException(command);
                    }

                    String time = inputSlash[1].substring(INPUT_OFFSET);
                    addToList(new Event(description, time));

                } else if (command.equals("mark")) {
                    markTask(Integer.parseInt(inputSplit[1]));

                } else if (command.equals("unmark")) {
                    unMarkTask(Integer.parseInt(inputSplit[1]));

                } else if (command.equals("delete")) {
                    deleteTask(Integer.parseInt(inputSplit[1]));

                } else {
                    throw new InvalidCommandException();
                }
            }  catch (IncompleteCommandException | InvalidCommandException | BlankCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
