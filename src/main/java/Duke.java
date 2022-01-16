import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> strings = new ArrayList<>();
        String input;

        System.out.println("Hola! soy José\nQué puedo hacer por ti? UwU");

        while (scanner.hasNext()) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Adiós. Espero volver a verte pronto!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < strings.size(); i++) {
                    System.out.println(i + 1 + ": " + strings.get(i));
                }
            } else {
                System.out.println("added: " + input);
                strings.add(input);
            }
        }

        scanner.close();
    }
}
