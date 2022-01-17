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
            } else if (Marked[0].equals("todo")) {
                String[] ans = Cmd.split("todo ");
                Task tempTask = new ToDo(ans[1]);
                taskList.add(tempTask);
                new AddTask(tempTask, taskList).callback();
            } else if (Marked[0].equals("deadline")) {
                String[] ans = Cmd.split(" /by ");
                Task tempTask = new Deadline(ans[0].replace("deadline ", "") ,ans[1]);
                taskList.add(tempTask);
                new AddTask(tempTask, taskList).callback();
            } else if (Marked[0].equals("event")) {
                String[] ans = Cmd.split(" /at ");
                Task tempTask = new Events(ans[0].replace("event ", "") ,ans[1]);
                taskList.add(tempTask);
                new AddTask(tempTask, taskList).callback();
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
