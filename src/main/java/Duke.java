import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String GREETING_MESSAGE = "Hey, Aeromon here! Fancy a cup of tea?";
    private static final String FAREWELL_MESSAGE = "Buai Buai! Ciao for now!";
    private static final String ADD_MESSAGE = "Nicely! I've added for you: ";
    private static final List<String> toDoList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(GREETING_MESSAGE);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(FAREWELL_MESSAGE);
                break;
            } else if (input.equals("list")) {
                int count = 1;
                for (String item : toDoList) {
                    System.out.println(String.format("%d: %s", count, item));
                    count++;
                }
            } else {
                toDoList.add(input);
                System.out.println(ADD_MESSAGE + input);
            }
        }
    }
}
