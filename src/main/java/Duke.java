import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hola! soy José\nQué puedo hacer por ti? UwU");
        String input;

        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Adiós. Espero volver a verte pronto!");
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
