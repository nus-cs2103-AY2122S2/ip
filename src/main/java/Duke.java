import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("A very good day to you master, I'm Blue the Genie");
        System.out.println("What do you wish for today? Your wish is my command");
        System.out.println("*-**-**-**-**-**-**-**-**-**-****-**-****-**-****-**");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.next();
            if (command.equals("bye")) {
                System.out.println("Rub my lamp to summon me again");
                System.out.println("Good bye for now master");
                System.out.println("*-**-**-**-**-**-**-**-**-**-****-**-****-**-****-**");
                break;
            } else {
                System.out.println(command);
                System.out.println("*-**-**-**-**-**-**-**-**-**-****-**-****-**-****-**");
            }
        }
        sc.close();

    }
}
