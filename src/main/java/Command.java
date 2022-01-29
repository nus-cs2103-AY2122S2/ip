public abstract class Command<T> {

    private boolean systemRunning = true;
    public abstract void runCommand();

    public void changeRunning(boolean bool) {
        systemRunning = bool;
    }

    public boolean isRunning() {
        return systemRunning;
    }
}
