import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.*;
import commands.*;
import tasks.*;
public class Duke {
    private static ArrayList<Task> TASKLIST = new ArrayList<>();
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
            try {
                String input = sc.nextLine();
                cmd = processInput(input);
                cmd.execute();
                if (cmd.ends()) {
                    break;
                }
            } catch (DukeException e){
                e.printError();
            }
        }
    }

    public static Command processInput(String input) throws UnknownCommandException, EmptyDescriptionException {
        Command cmd = null;
        String[] inputSplit = input.split(" ", 2);
        String commandType = inputSplit[0].toLowerCase();
        if (commandType.equals("bye")) {
            cmd = new ByeCommand();
        } else if (commandType.equals("list")) {
            cmd = new ListCommand(TASKLIST);
        } else if (commandType.equals("mark")) {
            cmd = new MarkCommand(TASKLIST, Integer.parseInt(inputSplit[1]));
        } else if (commandType.equals("unmark")) {
            cmd = new UnMarkCommand(TASKLIST, Integer.parseInt(inputSplit[1]));
        } else if(commandType.equals("delete")){
            cmd = new DeleteCommand(TASKLIST, Integer.parseInt(inputSplit[1]));
            TASKLIST = cmd.getList();
        } else if (commandType.equals("todo")||commandType.equals("event")||commandType.equals("deadline")){
            if(inputSplit.length<2){
                throw new EmptyDescriptionException(commandType);
            }
            cmd = new AddCommand(TASKLIST, commandType, inputSplit[1]);
            TASKLIST = cmd.getList();
        } else {
            throw new UnknownCommandException();
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
