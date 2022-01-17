import java.util.Scanner;
import java.util.ArrayList;

public class Bobby {
    public static void main(String[] args) {
        ArrayList<String> stringArray = new ArrayList<String>();
        System.out.println("Bobby greets you. Bobby is here to help.");

        while(true) {
            Scanner scannerObj = new Scanner(System.in);
            String userInput = scannerObj.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bobby bids you farewell.");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Here is what you told Bobby:");
                for (int i = 0; i < stringArray.size(); i++) {
                    int count = i + 1;
                    System.out.println(count + ". " + stringArray.get(i));
                }
            } else {
                stringArray.add(userInput);
            }
        }
    }
}
