/**
 * @author Saravanan Anuja Harish
 * This file contains the implementation of Main class.
 */
import java.util.Scanner;
import Exceptions.EmptyCommandException;
import Exceptions.IllegalCommandException;
import Helper.CarryOn;
import Helper.Ui;

public class Main {

    private final static String EMPTY_STRING = "";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // creates a new instance of conan.

        Conan conan = new Conan();
        CarryOn carryOn = CarryOn.NEXT;
        String userInput = sc.nextLine();

        //keep asking the user for name till u get a valid name
        while (userInput.trim().equalsIgnoreCase(EMPTY_STRING)) {
            Ui.printSeparator();
            Ui.printAskValidName();
            userInput = sc.nextLine();
        }

        // code when a similar user is found.
        boolean isSimilarPreviousUser = conan.tellName(userInput);
        if (isSimilarPreviousUser) {
            userInput = sc.nextLine();
            while(!conan.continueFromLastTime(userInput)) {
                userInput = sc.nextLine();
            }
        }

        // while the user still wants to type in.
        while (carryOn == CarryOn.NEXT) {

            try {
                // get the text from the user.
                String text = sc.nextLine();
                // tell conan the text and update if the user wants to continue.
                if (text.equalsIgnoreCase(EMPTY_STRING)) {
                    throw new EmptyCommandException(EMPTY_STRING);
                }
                carryOn = conan.tell(text);
            } catch (IllegalCommandException e) {
                Ui.printMessage(e.toString());
                Ui.printSeparator();
            }
        }
    }
}
