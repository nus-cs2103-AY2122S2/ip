import java.util.*;

public class Van {
    public static void main(String[] args) {
        String command = null;
        Scanner input = new Scanner(System.in);
        String divide = "-------------------------------------------";
        System.out.println("Hello I am Van");
        System.out.println("How may i assist you");
        System.out.println(divide);
        command = input.nextLine();
        System.out.println(divide);
        while (!command.equalsIgnoreCase("bye")) {
            System.out.println(command);
            System.out.println(divide);
            command = input.nextLine();
            System.out.println(divide);
        }
        System.out.println("Bye");
    }
}
