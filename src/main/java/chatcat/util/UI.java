package chatcat.util;

/**
 * Handles outputs to interact with user
 */
public class UI {

    public void showWelcomeSMS() {
        String welcome = "Hello! I'm ChatCat\n"
                + "What can I do for you?\n";
        System.out.println(welcome);
    }

    public void printOutPutWithSpace(String commandOutput) {
        System.out.println(commandOutput + "\n");
    }

    public void printOutPutWithoutSpace(String commandOutput) {
        System.out.println(commandOutput);
    }
}
