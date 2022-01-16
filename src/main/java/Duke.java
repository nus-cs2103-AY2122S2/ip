import javax.sound.sampled.Line;
import java.util.Scanner;
import commands.*;
public class Duke {
    private static String[] TASKLIST = new String[100];
    private static int LISTSIZE = 0;
    public static void main(String[] args) {
        //intro messages
        Scanner sc =  new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Command cmd = new WelcomeCommand();
        cmd.execute();
        //main program loop
        while(true){
            String input = sc.nextLine();
            cmd = processInput(input);
            if(cmd.ends()) {
                cmd.execute();
                break;
            }
            cmd.execute();
        }
    }

    public static Command processInput(String input){
        Command cmd = null;
        switch (input) {
            case "bye":
                cmd = new ByeCommand();
                break;

            case "list":
                cmd = new ListCommand(TASKLIST);
                break;

            default:
                cmd = new AddCommand(TASKLIST, LISTSIZE, input);
                TASKLIST = cmd.getList();
                LISTSIZE++;
                break;
        }
        return cmd;
    }
}
