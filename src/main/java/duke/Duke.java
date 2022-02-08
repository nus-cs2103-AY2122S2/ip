package duke;

import java.util.Scanner;

import duke.output.BooleanOutput;
import duke.output.Output;

public class Duke {

    /** Represents whether or not the parser is accepting userinput. */
    private boolean isPolling = true;
    private Scanner sc = new Scanner(System.in);
    private Parser parser = new Parser();
    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }

    private void run() {
        Ui.printMessage(Ui.GREETING_MESSAGE);
        while (isPolling) {
            String input = sc.nextLine();
            Output output = parser.inputHandler(input);
            if (output instanceof BooleanOutput) {
                BooleanOutput o = (BooleanOutput) output;
                isPolling = o.getBoolean();
            }
            Ui.printMessage(output.toString());
        }
    }
    
    protected String getResponse(String input) {
        Output output = parser.inputHandler(input);
        if (output instanceof BooleanOutput) {
            BooleanOutput o = (BooleanOutput) output;
            isPolling = o.getBoolean();
        }
        return output.getMessage();
    }
}
