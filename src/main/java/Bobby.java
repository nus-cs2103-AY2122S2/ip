import java.util.Scanner;

public class Bobby {
    public static void main(String[] args) {
        System.out.println("Bobby greets you. Bobby is here to help.");

        while(true) {
            Scanner scannerObj = new Scanner(System.in);
            String userInput = scannerObj.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bobby bids you farewell.");
                break;
            } else {
                System.out.println(userInput);
            }
        }
    }
}
