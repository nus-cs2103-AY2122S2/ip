//Author: Tan Ting Yu
//Student Number: A218235J


/*
 * Commands deals with the interactions with the user and making sense of the user command
 */

public abstract class Commands {
    protected String command;

     /**
     * Constructor for Command Objects
     *
     * @param command - The command entered by the user
     */

    public Commands(String command) {
        this.command = command;
    }


    abstract void execute();

    

}