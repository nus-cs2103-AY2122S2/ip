import javax.sound.sampled.Line;
import java.util.Scanner;
import commands.*;
import tasks.*;
public class Duke {
    private static Task[] TASKLIST = new Task[100];
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
        String[] inputSplit = input.split(" ");
        if(inputSplit.length == 1 && inputSplit[0].equals("bye")){
            cmd = new ByeCommand();
        } else if(inputSplit.length == 1 && inputSplit[0].equals("list")){
            cmd = new ListCommand(TASKLIST);
        } else if(inputSplit.length == 2 && inputSplit[0].equals("mark") && isInteger(inputSplit[1])) {
            cmd = new MarkCommand(TASKLIST, Integer.parseInt(inputSplit[1]));
        } else if(inputSplit.length == 2 && inputSplit[0].equals("unmark") && isInteger(inputSplit[1])){
            cmd = new UnMarkCommand(TASKLIST, Integer.parseInt(inputSplit[1]));
        } else {
            cmd = new AddCommand(TASKLIST, LISTSIZE, input);
            TASKLIST = cmd.getList();
            LISTSIZE++;
        }
        return cmd;
    }

    public static boolean isInteger(String input){
        for(int i = 0; i < input.length(); i++){
            if( !Character.isDigit(input.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
