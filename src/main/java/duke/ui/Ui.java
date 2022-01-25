package main.java.ui;

import main.java.Command;
import main.java.dukeexceptions.ForeignException;
import main.java.responses.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ui {
    static private ArrayList<String> commandList = new ArrayList<>(
            Arrays.asList("bye", "delete", "list", "mark", "unmark", "todo", "deadline", "event"));

    Scanner sc;
    
    
    public Ui() {
        sc = new Scanner(System.in);
    }
    

    public String getNextLine() {
        System.out.println("GetNextLine");
        return sc.nextLine();
    }
    public Command getCommand(String cmd) throws ForeignException {
        System.out.println(cmd);
        String[] stringCmdArr = cmd.split(" ");
        if (!commandList.contains(stringCmdArr[0])) {
            throw new ForeignException("");
        }  else {
            return Command.valueOf(stringCmdArr[0]);
        }
    }
    
    static public void callResponse(Response response) {
        response.callback();
    }
    
}
