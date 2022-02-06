package chatcat.util;

/**
 * Handles outputs to interact with user
 */
public class Ui {

    public void showWelcomeSMS() {
        String welcome = "Hello! I'm chatcat.ChatCat\n"
                + "What can I do for you?\n";
        System.out.println(welcome);
    }

    public String printOutPutWithSpace(String commandOutput) {
        System.out.println(commandOutput + "\n");
        return commandOutput;
    }

    public String printOutPutWithoutSpace(String commandOutput) {
        System.out.println(commandOutput);
        return commandOutput;
    }
}
