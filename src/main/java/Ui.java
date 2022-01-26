import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui(){
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("========================");
    }

    public void showLine() {
        System.out.println("========================");
    }

    public void print(String str) {
        System.out.println(str);
    }

    public String readCommand(){
        return this.sc.nextLine();
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void printInvalidCommand() {
        System.out.println("Invalid Task.\nValid tasks are: \"todo\", \"deadline\" and \"event\"");
    }
    public void close() {
        sc.close();
    }

}
