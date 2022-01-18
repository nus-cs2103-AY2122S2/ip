import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    private ArrayList<String> list;
    private ArrayList<Boolean> doneList;
    private int numItems;

    private Duke() {
        this.list = new ArrayList<>();
        this.doneList = new ArrayList<>();
        this.numItems = 0;
    }

    private void addItem(String item) {
        this.list.add(item);
        this.doneList.add(false);
        numItems++;
        System.out.println("added: " + item);
    }

    private void mark(int index) {
        this.doneList.set(index - 1, true);
        System.out.println("Great job! I've marked this task as done:");
        System.out.println("  [X] " + list.get(index - 1));
    }

    private void unmark(int index) {
        this.doneList.set(index - 1, false);
        System.out.println("Okay! I've marked this task as not done yet:");
        System.out.println("  [ ] " + list.get(index - 1));
    }

    private void printItem(int index) {
        String item = String.valueOf(index + 1) + ".";
        if(doneList.get(index)) {
            item += "[X] " + list.get(index);
        } else {
            item += "[ ] " + list.get(index);
        }
        System.out.println(item);
    }

    private void listItems() {
        for(int i = 0; i < this.numItems; i++) {
            this.printItem(i);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String greetings = "Greetings from Ann \n" + "What can I do for you?";
        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine().toLowerCase();
            if(input.equals("bye")) {
                System.out.println("Sad to see you go :( See you again soon!");
                break;
            } else if (input.equals("list")){
                duke.listItems();
            } else if(input.substring(0,4).equals("mark")) {
                duke.mark(Integer.parseInt(input.substring(5)));
            } else if(input.substring(0,6).equals("unmark")) {
                duke.unmark(Integer.parseInt(input.substring(7)));
            } else {
                duke.addItem(input);
            }
        }
    }
}
