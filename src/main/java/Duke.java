import java.util.Scanner;

public class Duke {
    private static final String GREETING_MESSAGE = "Hey, Duke here! How can I help you today?";
    private static final String FAREWELL_MESSAGE = "Adios! Take care and see you soon!";

    public static void main(String[] args) {
        System.out.println(GREETING_MESSAGE);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(FAREWELL_MESSAGE);
                break;
            }
            System.out.println(input);
        }
    }
}
