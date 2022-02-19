package duke;

import java.util.LinkedList;
import java.util.Queue;

import duke.exceptions.CorruptedSaveException;
import duke.ui.Ui;

public class Duke {

    private Parser parser;
    /** Contains pending messages from Duke */
    private Queue<String> outputQueue;

    /**
     * Creates a new instance of duke.
     */
    public Duke() {
        this.outputQueue = new LinkedList<>();
        try {
            this.parser = Parser.fromSave();
        } catch (CorruptedSaveException e) {
            bufferResponse(e.getMessage());
            this.parser = new Parser();
        }
        bufferResponse(Ui.GREETING_MESSAGE);
    }

    /**
     * Generates an output response from Duke, and buffers it to duke's output queue.
     * @param input The input supplied to Duke.
     */
    public void generateOutput(String input) {
        String output = parser.inputHandler(input);
        bufferResponse(output);
    }

    /**
     * Enqueues a string to Duke's output queue.
     * @param response The string to be enqueued.
     */
    public void bufferResponse(String response) {
        outputQueue.add(response);
    }

    /**
     * Dequeues a string from Duke's output queue.
     * @return A string from Duke's buffered queue of outputs.
     */
    public String pollNextResponse() {
        return outputQueue.poll();
    }

    /** Checks if there are any responses left in Duke's output buffer. */
    public boolean isOutputBufferEmpty() {
        return outputQueue.isEmpty();
    }
}
