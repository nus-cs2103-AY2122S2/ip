//Author: Tan Ting Yu
//Student Number: A218235J

import java.util.Scanner;



public class Duke {
    //Taskmaster logo 
    protected String logo = "  _____\n" + " /     \\\n" + "| () () |\n" + " \\  ^  /\n" + "  |||||\n" + "  |||||\n"; 
    protected TaskList tasklist;
    
    /**
     * Constructor for Duke the chatbot 
     */
    public Duke () {
        this.tasklist = new TaskList();
    }

    /**
     * The opening message we see when the program runs
     * 
     */
    public void openingMessage() {
        System.out.println(logo);
        System.out.println("Greetings, I'm Taskmaster, former S.H.I.E.L.D. agent turned mercenary and assassin");
        System.out.println("....................................");
        System.out.println("Okay, what do you want?");
    }

    /**
     * The main chatbot program that will run in a loop
     * 
     */

    public void chatBot() {
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

    public void bye() {
        System.out.println("See you around kiddo, I'm an angsty dude but deep down i'm a lonely man");
    }

    public static void main(String[] args) {
        Duke taskMaster = new Duke(); //Creates Chatbot
        taskMaster.openingMessage(); //Display opening message
        taskMaster.chatBot(); //Runs the chatbot
        taskMaster.bye(); //Exits
    }
}
