/**
 * Handles outputs to interact with user
 */
public class UI {

    public UI() { }

    public void showWelcomeSMS() {
        String welcome = "Hello! I'm ChatCat\n" + "What can I do for you?\n";
        System.out.println(welcome);
    }

    public void printOutPut(String commandOutput) {
        System.out.println(commandOutput + "\n");
    }


}
