import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    private ArrayList<String> list;
    private int numItems;

    private Duke() {
        this.list = new ArrayList<>();
        this.numItems = 0;
    }

    private void addItem(String item) {
        this.list.add(item);
        numItems++;
        System.out.println("added: " + item);
    }

    private void printItems() {
        for(int i = 0; i < this.numItems; i++) {
            System.out.println(String.valueOf(i + 1) + ". " + list.get(i));
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String greetings = "Greetings from Ann \n" + "What can I do for you?";
        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine();
            if(input.toLowerCase().equals("bye")) {
                System.out.println("Sad to see you go :( See you again soon!");
                break;
            } else if (input.toLowerCase().equals("list")){
                duke.printItems();
            } else {
                duke.addItem(input);
            }
        }
    }
}
