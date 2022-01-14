import java.util.*;

public class Duke {
    private static void format(String output) {
        String border = "   ____________________________________________________________";
        System.out.println(border + "\n      " + output + "\n" +  border);
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

        //Create ArrayList to store tasks
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        String toEcho = input.nextLine();
        while(!toEcho.equals("bye")) {
            //Add name of task to str for easy printing
            StringBuilder str = new StringBuilder();
            if (toEcho.equals("list")) {
                int size = tasks.size();
                for (int i = 0; i < size; i++) {
                    str.append(i + 1 + ". " + tasks.get(i));
                    if (i != size - 1) {
                        str.append("\n      ");
                    }
                }
                format(str.toString());
                toEcho = input.nextLine();
            } else {
                tasks.add(new Task(toEcho));
                format("added: " + toEcho);
                toEcho = input.nextLine();
            }
        }
        format("BYE WHAT BYE? YOU GO DROP TWENTY THEN BYE! DOWN!");
    }
}
