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
        System.out.println("GOOD MORNING GENNERMEN from\n" + logo);

        String border = "   ____________________________________________________________";
        String welcome =
                "GOOD MORNING CHAO RECRUIT! YOU MAY CALL ME ENCIK ENCIK\n" +
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
                    str.append(i + 1 + "." + tasks.get(i));
                    if (i != size - 1) {
                        str.append("\n      ");
                    }
                }
                format(str.toString());
            } else {
                //Split cmd into 2 parts, the type of task, and remaining text
                String[] temp = toEcho.split(" ", 2);
                String cmd = temp[0];
                String rem = temp[1];
                //Assuming input correct
                if (cmd.equals("mark")) {
                    int num = Integer.parseInt(rem) - 1;
                    tasks.get(num).mark();
                    String confirm =
                            "THIS ONE\n        " + tasks.get(num) +
                            "\n      FINISH ALREADY AH? SWEE CHAI BUTTERFLY RECRUIT!";
                    format(confirm);
                } else if (cmd.equals("unmark")) {
                    int num = Integer.parseInt(rem) - 1;
                    tasks.get(num).unmark();
                    String confirm =
                            "I THOUGHT THIS ONE\n        " + tasks.get(num) +
                            "\n      FINISH ALREADY? NEVER MIND THIS WEEKEND CONFINE!";
                    format(confirm);
                } else if (cmd.equals("deadline")) {
                    //deadline
                    String task = rem.split("/by")[0];
                    String dead = rem.split("/by")[1];
                    Task tempTask = new Task(cmd, task, dead);
                    tasks.add(tempTask);
                    format("YOU BETTER FINISH THIS AH:\n        " + tempTask +
                            "\n      YOU GOT " + tasks.size() + " TO DO AH BETTER NOT FORGET!");
                } else if (cmd.equals("event")) {
                    //event
                    String task = rem.split("/at")[0];
                    String dead = rem.split("/at")[1];
                    Task tempTask = new Task(cmd, task, dead);
                    tasks.add(tempTask);
                    format("YOU BETTER REMEMBER THIS AH:\n        " + tempTask +
                            "\n      YOU GOT " + tasks.size() + " TO DO AH BETTER NOT FORGET!");
                } else if (cmd.equals("todo")) {
                    //todo
                    Task tempTask = new Task(cmd, rem);
                    tasks.add(tempTask);
                    format("YOU WANT TO DO THIS AH:\n        " + tempTask + "\n      VERY GOOD!");
                }
            }
            toEcho = input.nextLine();
        }
        format("BYE WHAT BYE? YOU GO DROP TWENTY THEN BYE! DOWN!");
    }
}
