package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class ChatBot {
    String name = "Duke";
    boolean isRunning = true;


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
            }

            Response curr = new EchoResponse(Cmd);
            curr.callback();

        }
    }

    public static void main(String[] args) {
        ChatBot cb = new ChatBot();
        cb.run();
    }
}
