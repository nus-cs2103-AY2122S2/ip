import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    Hello! I'm Duke\n" +
                "    What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            Command comm = new Command(sc.nextLine());
            if (comm.execute() == true) {
                break;
            }
        }
    }
}
