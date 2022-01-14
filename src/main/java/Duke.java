import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo =
                " _____           _ _   \n" +
                "|  ___|         (_) |   \n" +
                "| |__ _ __   ___ _| | __\n" +
                "|  __| '_ \\ / __| | |/ /\n" +
                "| |__| | | | (__| |   < \n" +
                "\\____/_| |_|\\___|_|_|\\_\\\n";
        System.out.println("Good morning gennermen from\n" + logo);

        String border = "   ____________________________________________________________";
        System.out.println(border +
                "\n      Good MORNING CHAO RECRUIT! YOU MAY CALL ME ENCIK ENCIK\n" +
                "      WHAT YOU WANT?\n" + border + "\n");

        Scanner input = new Scanner(System.in);
        String toEcho = input.nextLine();
        while(!toEcho.equals("bye")) {
            System.out.println(toEcho);
            toEcho = input.nextLine();
        }
        System.out.println(border +
                "\n      BYE WHAT BYE? YOU GO DROP TWENTY THEN BYE! DOWN!\n" + border + "\n");
    }
}
