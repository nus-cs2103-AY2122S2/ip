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
            // If user types "bye" exit the program, otherwise echo user message
            if (funBox.isBye(userMessage)) {
                funBox.sayBye();
                break;
            } else if (funBox.isList(userMessage)) {
                funBox.showList();
            } else {
                funBox.addToList(userMessage);
            }
        }
        sc.close();
    }
}
