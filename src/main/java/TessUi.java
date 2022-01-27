package tesseract.main;

import java.util.Scanner;

/**
 * Represent the user interface of Tesseract.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class TessUi {
    // deals with interactions with the user
    protected static String INDENT1 = "    ";
    protected static String INDENT2 = "        ";
    protected static String BREAKER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    protected Scanner sc;

    TessUi() {
        sc = new Scanner(System.in);
    }

    String readCommand() {
        return sc.nextLine();
    }

    /**
     * Display the welcome message to the user.
     */
    void sayHi() {
        System.out.println(BREAKER + "\n"
                + "Hi fellow! I am Tesseract\n"
                + "I can bring you to wherever you want in the universe\n"
                + "How can I help you?\n"
                + BREAKER);
    }

    /**
     * Display the farewell message to the user.
     */
    public void sayBye() {
        System.out.println(BREAKER + "\n"
                + "Ok I'm gonna travel to another planet now\n"
                + "Hope to see you again when I'm back :P\n"
                + BREAKER);
    }

    void display(String msg) {
        String res = INDENT1 + BREAKER + "\n"
                + INDENT1 + msg + "\n"
                + INDENT1 + BREAKER + "\n";
        System.out.println(res);
    }

    /**
     * Display the list of tasks to the user.
     */
    public void listTasks(String msg){
        String res = "Look what I have noted down for you~ \n" + msg;
        display(res);
    }

    /**
     * Display the deleted task to the user.
     */
    public void deleteTaskRes(String msg, int numOfTask) {
        String res = "Okies the following task has been removed:\n"
                + INDENT2 + msg + "\n"
                + INDENT1 + "Now you have " + numOfTask + " tasks in the list~\n";
        display(res);
    }

    /**
     * Display the added task to the user.
     */
    public void addTaskRes(String msg, int numOfTask) {
        String res = "This has been added to your schedule. Wish you can finish it on time\n"
                + INDENT2 + msg + "\n"
                + INDENT1 + "Now you have "
                + numOfTask + " tasks waiting to be finished.\n";
        display(res);
    }

    /**
     * Display the task marked as done to the user.
     */
    public void markAsDoneRes(String msg) {
        String res = "Wow you have finished a task! Excellent! \n"
                + INDENT2 + msg + "\n";
        display(res);
    }

    /**
     * Display the task marked as undone to the user.
     */
    public void markAsUndoneRes(String msg) {
        String res = "Seems like you have successfully undone your done task \n"
                + INDENT2 + msg + "\n";
        display(res);
    }

    /**
     * Display the tasks filtered by the condition to the user.
     */
    public void filterRes(String condition, String msg) {
        String res = "Here are the tasks filtered by "
                + condition + ":\n" + msg;
        display(res);
    }

    /**
     * Display the error message to the user.
     */
    public void showError(String err) {
        display(err);
    }

    /**
     * Display to user the existence of bug(s) in the program.
     */
    public void admitBug() {
        String res = "I think there's a bug inside me causing me to malfunction..";
        display(res);
    }
}
