import javax.sound.sampled.Line;
import java.util.Scanner;
public class Duke {
    public static String LINE = "    ____________________________________________________________\n";
    public static String INDENT = "     ";
    public static void main(String[] args) {
        //intro messages
        Scanner sc =  new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE + INDENT + "Hello! I'm Duke\n"+ INDENT + "What can I do for you?\n" + LINE);
        //main program loop
        while(true){
            String input = sc.nextLine();
            if(input.equals("bye")){
                break;
            }
            System.out.println(LINE + INDENT + input + "\n" + LINE);
        }

        System.out.println(LINE + INDENT + "Bye. Hope to see you again soon!\n" + LINE);
    }
}
