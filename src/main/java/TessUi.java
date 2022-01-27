import java.util.Scanner;

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

    void sayHi() {
        System.out.println(BREAKER + "\n" + "Hi fellow! I am Tesseract\n" +
                "I can bring you to wherever you want in the universe\n" +
                "How can I help you?\n"
                + BREAKER);
    }

    void sayBye() {
        System.out.println(BREAKER + "\n" +
                "Ok I'm gonna travel to another planet now\n" +
                "Hope to see you again when I'm back :P\n"
                + BREAKER);
    }

    void display(String msg) {
        String res = INDENT1 + BREAKER + "\n" + INDENT1 + msg + "\n" + INDENT1 + BREAKER + "\n";
        System.out.println(res);
    }

    void listTasks(String msg){
        String res = "Look what I have noted down for you~ \n" + msg;
        display(res);
    }

    void deleteTaskRes(String msg, int numOfTask) {
        String res = "Okies the following task has been removed:\n" + INDENT2 + msg
                + "\n" + INDENT1 + "Now you have " + numOfTask + " tasks in the list~\n";
        display(res);
    }

    void addTaskRes(String msg, int numOfTask) {
        String res = "This has been added to your schedule. Wish you can finish it on time\n"
                + INDENT2 + msg + "\n" + INDENT1
                + "Now you have " + numOfTask + " tasks waiting to be finished.\n";
        display(res);
    }

    void markAsDoneRes(String msg) {
        String res = "Wow you have finished a task! Excellent! \n" + INDENT2 + msg + "\n";
        display(res);
    }

    void markAsUndoneRes(String msg) {
        String res = "Seems like you have successfully undone your done task \n" + INDENT2 + msg + "\n";
        display(res);
    }

    void filterRes(String condition, String msg) {
        String res = "Here are the tasks filtered by " + condition + ":\n" + msg;
        display(res);
    }

    void showError(String err) {
        display(err);
    }

    void admitBug() {
        String res = "I think there's a bug inside me causing me to malfunction..";
        display(res);
    }

    void showBreaker() {
        System.out.println("~~~~~~~~I~~~~~~~~AM~~~~~~~~A~~~~~~~~HAPPY~~~~~~~~~BREAKER~~~~~~~~~~~");
    }
}
