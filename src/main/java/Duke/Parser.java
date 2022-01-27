package Duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Parser {

    protected BufferedReader br;
    protected boolean isTerminated = false;

    /**
     * Default constructor for Parser object.
     */
    public Parser() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Reads user input via commandline.
     *
     * @return User input as a string
     * @throws IOException when there is an error with user input
     */
    public String readInput() throws IOException {
        return this.br.readLine();
    }

    /**
     * Terminates the Parser object, ending the program.
     */
    public void terminate() {
        this.isTerminated = true;
    }
}
