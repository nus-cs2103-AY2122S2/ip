package duke;

import duke.misc.Quote;

public class Ui {
    private String response = "";

    /**
     * Welcome Message when Duke Starts up
     *
     * @return Welcome message
     */
    public static String printWelcomeMessage() {
        Quote quoteOfTheDay = new Quote();
        String welcomeResponse = "Hello, My Dear Friend... I'm Duke, your personal motivator!\n";
        welcomeResponse += (quoteOfTheDay.generateQuote());
        welcomeResponse += "\nWhat can i do for you today?";
        return welcomeResponse;
    }

    /**
     * Print statement with divider line
     *
     * @param str input string to be printed
     */
    public void print(Object str) {
        this.response += (String) str;
        this.response += "\n";
    }

    /**
     * Prints oridinary System.out.println
     *
     * @param str input string to be printed
     */
    public void println(Object str) {
        this.response += (String) str;
        this.response += "\n";
    }

    /**
     * Print statement for when system exits
     */
    public void printBye() {
        this.print("Bye. Hope I've motivated you as much as I could have, and SMILE :D\nClosing in 5 Seconds!");
    }

    /**
     * Return Ui response and then clears the response
     *
     * @return Ui Response
     */
    public String returnResponse() {
        String responseToReturn = this.response;
        this.response = "";
        return responseToReturn;

    }

    public void throwDukeException(String message) throws DukeException {
        throw new DukeException(message);
    }

}
