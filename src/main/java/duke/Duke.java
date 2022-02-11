package duke;

import java.util.LinkedList;
import java.util.Queue;

import duke.exceptions.CorruptedSaveException;

public class Duke {

    private Parser parser;
    /** Contains pending messages from Duke */
    private Queue<String> outputQueue;

    public Duke() {
        this.outputQueue = new LinkedList<>();
        try {
            this.parser = Parser.fromSave();
        } catch (CorruptedSaveException e) {
            bufferResponse(e.getMessage());
            this.parser = new Parser();
        }
        //bufferResponse(Ui.GREETING_MESSAGE);
    }

    public void generateOutput(String input) {
        String output = parser.inputHandler(input);
        bufferResponse(output);
    }

    public void bufferResponse(String response) {
        outputQueue.add(response);
    }
    
    public String getNextResponse() {
        return outputQueue.poll();
    }

    public boolean isOutputBufferEmpty() {
        return outputQueue.isEmpty();
    }
}
