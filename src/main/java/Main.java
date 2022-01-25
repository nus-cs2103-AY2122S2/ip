/**
 * @author Saravanan Anuja Harish
 * This file contains the implementation of Main class.
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // creates a new instance of conan.

        Conan conan = new Conan();
        CarryOn carryOn = CarryOn.NEXT;
        String userInput = sc.nextLine();
        conan.tellName(userInput);

        // while the user still wants to type in.
        while (carryOn == CarryOn.NEXT) {
            // get the text from the user.
            String text = sc.nextLine();
            // tell conan the text and update if the user wants to continue.
            carryOn = conan.tell(text);

        }
    }
}
