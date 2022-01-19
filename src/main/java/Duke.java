import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("A very good day to you master, I'm Blue the Genie");
        System.out.println("What do you wish for today? Your wish is my command");
        System.out.println("*-**-**-**-**-**-**-**-**-**-****-**-****-**-****-**");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> storeList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Rub my lamp to summon me again");
                System.out.println("Good bye for now master");
                System.out.println("*-**-**-**-**-**-**-**-**-**-****-**-****-**-****-**");
                break;
            } else if (command.equals("list")) {
                int sizeOfList = storeList.size();
                System.out.println("Everything in my blue brain now: ");
                for (int i = 1; i <= sizeOfList; i++) {
                    System.out.println(i + "."+ " " + storeList.get(i -1));
                }
            } else {
                storeList.add(command);
                System.out.println("stored in my blue brain: " + command);
                System.out.println("*-**-**-**-**-**-**-**-**-**-****-**-****-**-****-**");
            }
        }
        sc.close();

    }
}
