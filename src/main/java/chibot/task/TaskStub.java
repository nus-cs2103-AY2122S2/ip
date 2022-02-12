package chibot.task;

/**
 * A STUB of a Task instance for testing.
 */
public class TaskStub extends Task {

    /**
     * Constructor for the class
     *
     * @param s Filler String for super class constructor.
     */
    public TaskStub(String s) {
        super(s);
    }

    /**
     * Returns a fixed boolean for testing purposes.
     *
     * @param wordsToCheck Array of keywords.
     * @return True.
     */
    public boolean checkDescriptionForWords(String[] wordsToCheck) {
        return true;
    }

    /**
     * Returns a fixed task description.
     *
     * @return String representation of the task description.
     */
    @Override
    public String toString() {
        return "forT1";
    }
}
