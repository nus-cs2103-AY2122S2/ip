import java.util.*;

public class Heylo {
    public static void main(String[] args) {
        String logo =
                " _   _                  _\n" +
                        "| | | |   ___   _   _  | |   ___\n" +
                        "| |_| |  / _ \\ | | | | | |  / _ \\ \n" +
                        "|  _  | |  __/ | |_| | | | | (_) |\n" +
                        "|_| |_|  \\___|  \\__, | |_|  \\___/\n" +
                        "                |___/\n";
        System.out.println(logo);
        System.out.println("Heylo! What can I do for you today? :)\n");

        Scanner sc = new Scanner(System.in);

        while(true) {
            String command = sc.nextLine();
            System.out.printf(" ");

            if(command.equals("Bye")) {
                System.out.println("See you again! :)");
                return;
            } else {
                System.out.println(command);
            }
            System.out.println("__________________________________________________________");
        }
    }
}
