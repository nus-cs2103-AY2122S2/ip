/**
 * @author Saravanan Anuja Harish
 * This file contains the implementation of Main class.
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Conan conan = new Conan();
        CarryOn carryOn = CarryOn.NEXT;
        Scanner sc = new Scanner(System.in);

        while (carryOn == CarryOn.NEXT) {
            String task = sc.nextLine();
            carryOn = conan.tell(task);
        }
    }
}
