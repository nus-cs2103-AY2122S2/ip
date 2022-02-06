package duke.util;

public class Parser {
    private String[] inputArray;
    private String originalInput;

    /**
     * Takes in raw original user input and parses it to an array
     * @param originalInput
     */
    public Parser(String originalInput) {
        this.originalInput = originalInput;
        inputArray = originalInput.split(" ");
    }

    
    /** 
     * @return String Primary command e.g. "todo", "deadline", "delete"
     */
    public String getCommand() {
        return inputArray[0];
    }

    
    /** 
     * @return String[] original string input split by " " into a String[]
     */
    public String[] getInputArray() {
        return inputArray;
    }

    
    /** 
     * @return String orginal raw user input
     */
    public String getOriginalInput() {
        return originalInput;
    }
}