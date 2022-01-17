package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class ChatBot {
    String name = "Duke";
    boolean isRunning = true;
    ArrayList<String> mem = new ArrayList<>();


    public void run() {

        Response start = new StartResponse();
        start.callback();

        while (isRunning) {
            Scanner sc = new Scanner(System.in);
            String Cmd = sc.nextLine();

            if (Cmd.equals("bye")) {
                Response stop = new StopResponse();
                stop.callback();
                isRunning = false;
                break;
            } else if (Cmd.equals("list")) {
                Response lst = new ListResponse(mem);
                lst.callback();
            } else {
                Response curr = new AddResponse(Cmd);
                mem.add(Cmd);
                curr.callback();
            }
        }
    }

    public static void main(String[] args) {
        ChatBot cb = new ChatBot();
        cb.run();
    }
}
