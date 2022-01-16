import java.util.Scanner;

/**
 * The FunBox class is used as the outer shell of FunBoxGear,
 * which contains the functionality of FunBox
 */
public class FunBox {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Initiate FunBoxGear
        FunBoxGear funBox = new FunBoxGear();
        // Greet the users
        funBox.greet();

        boolean isLoop = true;
        while (isLoop) {
            String userMessage = sc.nextLine();

            if (!funBox.getCommands(userMessage)) {
                isLoop = false;
            }
        }
        sc.close();
    }
}
