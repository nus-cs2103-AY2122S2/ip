import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println(output("Hello! I'm Duke by A0221330A.\n    What can I do for you?"));

        List<Task> list = new ArrayList<>();

        boolean exit = false;
        while (!exit) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(output("Bye. Hope to see you again soon!"));
                exit = true;
            } else if (input.equals("list")) {
                StringBuilder text = new StringBuilder();
                for (int i=0; i<list.size();i++){
                    if (i == 0) {
                        text.append(i + 1).append(". ").append(list.get(i).getTask()).append("\n");
                    } else if (i == list.size() - 1){
                        text.append("    ").append(i + 1).append(". ").append(list.get(i).getTask());
                    } else{
                        text.append("    ").append(i + 1).append(". ").append(list.get(i).getTask()).append("\n");
                    }
                }
                System.out.println(output(text.toString()));
            } else {
                list.add(new Task(input));
                System.out.println(output("added: " + input));
            }
        }
    }

    public static String output(String text) {
        String line = "    ____________________________________________________________";
        return line + "\n    " + text + "\n" + line;
    }
}
