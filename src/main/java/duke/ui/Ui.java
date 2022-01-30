package duke.ui;

import duke.responses.Response;

import java.util.Scanner;

/**
 * This is the User Interface
 */
public class Ui {
    Scanner sc;

    /**
     * Constructor of UI
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * @return the String represent of the next line
     */
    public String getNextLine() {   
        return sc.nextLine();
    }

    /**
     * causes response to perform a call back method
     * @param response Response after the execution of a Command
     */
    public void callResponse(Response response) {
        response.callback();
    }
      
    
}
