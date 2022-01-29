import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Welcome!");
    }

    public void showGoodBye() {
        System.out.println("Good Bye!");
    }

    public void showError(String err) {
        System.out.println("\t" + err + "\n");
    }

    public void showMarked(Task task) {
        System.out.println("\t" + task.toString() + " marked!\n");
    }

    public void showUnmarked(Task task) {
        System.out.println("\t" + task.toString() + " unmarked!\n");
    }

    public void showDeleted(Task task) {
        System.out.println("\t" + task.toString() + " deleted!\n");
    }

    public void showAdded(Task task) {
        System.out.println("\t" + task.toString() + " added!\n");
    }

    public String getCommand() {
        return sc.nextLine();
    }
}
