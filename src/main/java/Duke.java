import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    private ArrayList<Task> dukeList = new ArrayList<Task>();
    Scanner sc = new Scanner(System.in);

    public void startDuke() {
        displayHelloMessage();
        String command = sc.nextLine();
        while(!command.equals("Bye")) {
            executeCommand(command);
            command = sc.nextLine();
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

    void executeCommand(String command) {
        if (command.equals("list")) {
            displayList();
        } else if (command.length() > 4 && command.substring(0,5).equals("mark ")) {
            int taskIndex = Integer.parseInt(command.substring(5)) - 1;
            Task toBeCompleted = dukeList.get(taskIndex);
            toBeCompleted.isDone = true;
            toBeCompleted.isComplete();
            displayTaskCompletion(toBeCompleted);
        } else if (command.length() > 6 && command.substring(0,7).equals("unmark ")) {
            int taskIndex = Integer.parseInt(command.substring(7)) - 1;
            Task toBeCompleted = dukeList.get(taskIndex);
            toBeCompleted.isDone = false;
            //toBeCompleted.isComplete();
            displayIncompleteTask(toBeCompleted);
        } else {
            displayMessage(command);
            addTaskToList(command);
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

