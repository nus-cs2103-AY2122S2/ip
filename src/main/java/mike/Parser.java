package mike;

public class Parser {
    private final String userInput;
    private final String[] splitString;

    /**
     * Constructor for Parser.
     *
     * @param userInput The String to be parsed.
     */
    public Parser(String userInput) {
        this.userInput = userInput.trim(); //Parser trims userInput automatically
        this.splitString = this.userInput.split(" ");
    }

    /**
     * Returns the user input.
     *
     * @return User input.
     */
    public String getUserInput() {
        return userInput;
    }

    /**
     * Returns the command portion of the user input.
     *
     * @return String representing the command from a user's input.
     */
    public String getCommand() {
        return this.splitString[0];
    }

    /**
     * Returns the index portion of the user input.
     *
     * @return An integer representing the index of a task in the list.
     */
    public int getIndex() {
        return Integer.parseInt(this.splitString[1]);
    }

    /**
     * Returns true if the user input is the exit command ("bye").
     *
     * @return boolean representing whether the user has input the exit command.
     */
    public boolean isBye() {
        return getUserInput().equals("bye");
    }

    /**
     * Returns true if the user input does not contain any characters.
     *
     * @return boolean representing whether the user has input a String without characters.
     */
    public boolean isBlank() {
        return getUserInput().isEmpty();
    }

    /**
     * Removes the "command" portion out from the input string and returns the remainder of the string.
     *
     * @return The input string from the user with the "command" portion removed.
     */
    public String removeCommandFromString() {
        int indexOfFirstSpace = this.userInput.indexOf(" ");
        int indexOfTaskParameters = indexOfFirstSpace + 1;
        return this.userInput.substring(indexOfTaskParameters);
    }

    /**
     * Returns the name of a to-do after processing the user input.
     *
     * @return String representing the name of a to-do.
     */
    String getTodoName() {
        return removeCommandFromString();
    }

    /**
     * Returns the name of a deadline after processing the user input.
     *
     * @return String representing the name of a deadline.
     */
    public String getDeadlineName() {
        String userInputCommandRemoved = removeCommandFromString();
        int indexOfBy = userInputCommandRemoved.indexOf("/by");
        assert indexOfBy >= 0 : "index should be more than or equal to 0";
        String name = userInputCommandRemoved.substring(0, indexOfBy - 1);
        return name;
    }

    /**
     * Returns the date of a deadline after processing the user input.
     *
     * @return String representing the date of a deadline.
     */
    public String getDeadlineDate() {
        String userInputCommandRemoved = removeCommandFromString();
        int indexOfBy = userInputCommandRemoved.indexOf("/by");
        int indexOfEndDate = indexOfBy + 4;
        assert indexOfEndDate >= 0 : "index should be more than or equal to 0";
        String endDate = userInputCommandRemoved.substring(indexOfEndDate);
        return endDate;
    }

    /**
     * Returns the name of an event after processing the user input.
     *
     * @return String representing the name of an event.
     */
    public String getEventName() {
        String userInputCommandRemoved = removeCommandFromString();
        int indexOfBy = userInputCommandRemoved.indexOf("/at");
        assert indexOfBy >= 0 : "index should be more than or equal to 0";
        String name = userInputCommandRemoved.substring(0, indexOfBy - 1);
        return name;
    }

    /**
     * Returns the date of an event after processing the user input.
     *
     * @return String representing the date of an event.
     */
    public String getEventDate() {
        String userInputCommandRemoved = removeCommandFromString();
        int indexOfBy = userInputCommandRemoved.indexOf("/at");
        int indexOfEndDate = indexOfBy + 4;
        String date = userInputCommandRemoved.substring(indexOfEndDate);
        return date;
    }

    /**
     * Returns the keywords to be searched for from user input.
     *
     * @return The keywords to be searched for.
     */
    public String getSearchWords() {
        return removeCommandFromString();
        //Todo:fix bug where if user inputs "find", it searches for occurrences of "find".
    }
}
