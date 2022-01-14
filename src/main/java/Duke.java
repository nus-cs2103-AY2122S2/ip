import java.util.*;

public class Duke {
    private static void format(String output) {
        String border = "   ____________________________________________________________";
        System.out.println(border + "\n      " + output + "\n" +  border + "\n");
    }

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
        String welcome =
                "Good MORNING CHAO RECRUIT! YOU MAY CALL ME ENCIK ENCIK\n" +
                "      WHAT YOU WANT?";
        format(welcome);

        Scanner input = new Scanner(System.in);
        String toEcho = input.nextLine();
        while(!toEcho.equals("bye")) {
            format(toEcho);
            toEcho = input.nextLine();
        }
        format("BYE WHAT BYE? YOU GO DROP TWENTY THEN BYE! DOWN!");
    }
}
