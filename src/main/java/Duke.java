import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    private ArrayList<String> dukeList = new ArrayList<String>();
    Scanner sc = new Scanner(System.in);

    public void startDuke() {
        displayHelloMessage();
        
        boolean loop = true;
        while (loop) {
            String input = sc.nextLine();
            if (input.equals("Bye")) {
                displayByeMessage();
                loop = false;
            } else {
                executeCommand(input);
            }
        }
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

    public void addToList(String command) {
        dukeList.add(command);
    }

    public void displayList() {
        displayLine();
        for (int i = 1;i <= dukeList.size(); i++) {
            System.out.println( (i) + ". " + dukeList.get(i-1));
        }
        displayLine();
    }

    void executeCommand(String command) {
        if(!command.equals("list") && !command.equals("Bye")) {
            addToList(command);
            displayMessage(command);
        } else if (command.equals("list")) {
            displayList();
            
        }
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startDuke();
    }
}

