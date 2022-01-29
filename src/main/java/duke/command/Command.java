package duke.command;

/**
 * Abstract class for command
 * @param <String> Command object accepts String
 */
public abstract class Command<String> {

    private boolean systemRunning = true;

    /**
     * abstract method for runCommand to be implemented by subclasses
     */
    public abstract void runCommand();

    /**
     * change status of program
     * @param bool boolean to change the status of program
     */
    public void changeRunning(boolean bool) {
        systemRunning = bool;
    }

    /**
     * check status of program
     * @return boolean to check status of program
     */
    public boolean isRunning() {
        return systemRunning;
    }
}
