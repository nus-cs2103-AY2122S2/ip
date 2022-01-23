package auxiliary;

import Exceptions.InvalidInputException;

public abstract class Chatbot {
    protected boolean isAwake;
    protected abstract String speak(String input);
    protected abstract String greet();
    protected abstract String farewell();
    public abstract String feedCommandAndReply(String input);

    public boolean isAwake() {
        return isAwake;
    }

    public void setAwake(boolean awake) {
        this.isAwake = awake;
    }

    protected Chatbot() {
        this.isAwake = true;
    }
}
