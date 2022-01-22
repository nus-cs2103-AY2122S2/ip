//Author: Tan Ting Yu
//Student Number: A218235J

import java.util.Scanner;



public class Taskmaster {
    //Taskmaster logo 
    protected String logo = "  _____\n" + " /     \\\n" + "| () () |\n" + " \\  ^  /\n" + "  |||||\n" + "  |||||\n"; 
    protected TaskList tasklist;
    protected Storage storage;
    
    /**
     * Constructor for Taskmaster the chatbot 
     */
    private Taskmaster () {
        this.tasklist = new TaskList();
        this.storage = new Storage();
    }

    /**
     * The opening message we see when the program runs
     * 
     */
    private void openingMessage() {
        System.out.println(logo);
        System.out.println("Greetings, I'm Taskmaster, I'm super grumpy 24/7");
        System.out.println("....................................");
        System.out.println("Okay, what do you want?\n");
    }

    /**
     * The main chatbot program that will run in a loop
     * 
     */

    private void chatBot() {
         //Scanner initialization for user input
         Scanner sc = new Scanner(System.in);
         String userInput = sc.nextLine();
         while (!userInput.equals("bye")) {
           Commands command = new Commands(userInput);
           command.performCommand(tasklist);
           userInput = sc.nextLine();
         }
         sc.close();
    }
    
    /**
     * The bye message displayed when the user exits
     * 
     */

    private void bye() {
        System.out.println("See you around kiddo, I'm an angsty dude but deep down i'm a lonely man");
    }

    private void loadingExistingFile() {
        System.out.println("\nLoading up saved task files ...\n");
        storage.loadFile(tasklist);
    }

    private void updateList() {
        storage.updateList(tasklist);
    }

    public static void main(String[] args) {
        Taskmaster taskMaster = new Taskmaster(); //Creates Chatbot
        taskMaster.openingMessage(); //Display opening message
        taskMaster.loadingExistingFile(); //Load existing file
        taskMaster.chatBot(); //Runs the chatbot
        taskMaster.bye(); //Exits
        taskMaster.updateList(); //Save tasks in the hard disk if task list changes
    }
}