import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    private ArrayList<Task> dukeList = new ArrayList<Task>();
    Scanner sc = new Scanner(System.in);

    public void startDuke() {
        displayHelloMessage();
        String command = sc.next();
         String description = sc.nextLine();
         while(!command.equals("Bye")) {
             executeCommand(command, description);
             command = sc.next();
             description = sc.nextLine();
         }
        displayByeMessage();
    }

    public void displayHelloMessage() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        displayLine();
    }

    public void displayLine() {
        System.out.println("--------------------------------------------------");
    }

    public void displayByeMessage() {
        displayLine();
        System.out.println("Bye. Hope to see you soon.");
        displayLine();
    }

    public void displayMessage(String command) {
        displayLine();
        System.out.println("added:" + command);
        displayLine();
    }

    public void addTaskToList(String command) {
        Task newTask = new Task(command);
        dukeList.add(newTask);
    }

    public void displayList() {
        displayLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1;i <= dukeList.size(); i++) {
            System.out.println( (i) + ". " + dukeList.get(i-1));
        }
        displayLine();
    }

    void executeCommand(String command, String description) {
        if (command.equals("list")) {
            displayList();
        } else if (command.equals("mark")) {
            int taskIndex = Integer.parseInt(description.substring(1)) - 1;
            Task toBeCompleted = dukeList.get(taskIndex);
            toBeCompleted.isDone = true;
            toBeCompleted.isComplete();
            displayTaskCompletion(toBeCompleted);
        } else if (command.equals("unmark")) {
            int taskIndex = Integer.parseInt(description.substring(1)) - 1;
            Task toBeCompleted = dukeList.get(taskIndex);
            toBeCompleted.isDone = false;
            //toBeCompleted.isComplete();
            displayIncompleteTask(toBeCompleted);
        } else {
            displayLine();
            System.out.println("Got it. I've added this task: ");
            if (command.equals("event")) {
                String[] descriptionAndTime = description.split("/");
                 String eventDescription = (descriptionAndTime[0].split(" ", 2))[1];
                 String eventTime = (descriptionAndTime[1].split(" ", 2))[1];
                 Event newEvent = new Event(eventDescription, eventTime);
                 dukeList.add(newEvent);
                 System.out.println(newEvent);
            } else if (command.equals("deadline")) {
                String[] descriptionAndTime = description.split("/");
                 String deadlineDescription = (descriptionAndTime[0].split(" ", 2))[1];
                 String deadlineTime = (descriptionAndTime[1].split(" ", 2))[1];
                 Deadline newDeadline = new Deadline(deadlineDescription, deadlineTime);
                 dukeList.add(newDeadline);
                 System.out.println(newDeadline);
            } else {
                ToDo newTodo = new ToDo(description.substring(1));
                dukeList.add(newTodo);
                System.out.println(newTodo);
        }
        System.out.println("Now you have " + dukeList.size() + " tasks in the list.");
        displayLine();
    }

    }

    void displayIncompleteTask(Task complete){
        displayLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(complete);
        displayLine();
    }

    void displayTaskCompletion(Task incomplete) {
        displayLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(incomplete);
        displayLine();
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startDuke();
    }
}
