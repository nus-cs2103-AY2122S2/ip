//Author: Tan Ting Yu
//Student Number: A218235J

public class Taskmaster {
    //Taskmaster logo 

    protected UserInterface ui;

    /**
     * Constructor for Taskmaster the chatbot 
     */
    private Taskmaster () {
        this.ui = new UserInterface();
    }

    public static void main(String[] args) {
        Taskmaster taskMaster = new Taskmaster(); //Creates Chatbot
        taskMaster.ui.runChatBot(); //Display opening message
    }
}