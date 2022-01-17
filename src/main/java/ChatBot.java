package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class ChatBot {
    String name = "Duke";
    boolean isRunning = true;
    ArrayList<String> mem = new ArrayList<>();
    ArrayList<Task> taskList = new ArrayList<>();


    public void run() {

        Response start = new StartResponse();
        start.callback();

        while (isRunning) {
            Scanner sc = new Scanner(System.in);
            String Cmd = sc.nextLine();
            String[] Marked = Cmd.split(" ");

            if (Cmd.equals("bye")) {
                Response stop = new StopResponse();
                stop.callback();
                isRunning = false;
                break;
            } else if (Cmd.equals("list")) {
                Response lst = new ListResponse(taskList);
                lst.callback();
            } else if (Marked[0].equals("mark")) {
                taskList.get(Integer.parseInt(Marked[1]) - 1).setMark(true);
            } else if (Marked[0].equals("unmark")) {
                taskList.get(Integer.parseInt(Marked[1]) - 1).setMark(false);
            }
            else {
                Response curr = new AddResponse(Cmd);
                mem.add(Cmd);

                Task tempTask = new Task(Cmd);
                taskList.add(tempTask);

                curr.callback();
            }
        }
    }

    public static void main(String[] args) {
        ChatBot cb = new ChatBot();
        cb.run();
    }
}
