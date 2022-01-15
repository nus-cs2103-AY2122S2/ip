import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String intro = "I am a Personal Assistant Chatbot that helps you to keep track of various things.\n"
                + "Let's get started.\n";

        String line = "-------------------------------------------------------------------------------------";

        String bye = "Bye. Hope to see you again soon!";

        System.out.println("Hello from\n" + logo);
        System.out.println(intro);
        System.out.println(line);

        Scanner s = new Scanner(System.in);
        String input;

        while(true) {

             input = s.nextLine();

            if (input.equals("bye")){
                break;
            }
            else {
                System.out.println("\n");
                System.out.println(line);
                Task t = new Task(input);
                System.out.println(t.reply());
                System.out.println(line);
            }
        }

        System.out.println(line);
        System.out.println(bye);
        System.out.println(line);


    }
}
