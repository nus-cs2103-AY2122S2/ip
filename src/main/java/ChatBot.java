package main.java;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the chatbot object.
 */
public class ChatBot {
    String name = "Duke";
    boolean isRunning = true;
    ArrayList<String> mem = new ArrayList<>();
    ArrayList<Task> taskList = new ArrayList<>();


    /**
     * method that starts the chatbot.
     */
    public void run() {

        Response start = new StartResponse();
        start.callback();
        Scanner sc = new Scanner(System.in);

        while (isRunning) {
            String Cmd = sc.nextLine();
            String[] Marked = Cmd.split(" ");

            try {
                Command firstCmd = Command.valueOf(Marked[0]);

                if (firstCmd == Command.bye) {
                    Response stop = new StopResponse();
                    stop.callback();
                    this.stop();
                    break;
                } else if (firstCmd == Command.delete) {
                    int index = Integer.parseInt(Marked[1]);
                    Task rm = taskList.get(index - 1);
                    taskList.remove(index - 1);
                    Response curr = new DeleteResponse(rm, taskList.size());
                    curr.callback();
                }else if (firstCmd == Command.list) {
                    Response lst = new ListResponse(taskList);
                    lst.callback();
                } else if (firstCmd == Command.mark) {
                    taskList.get(Integer.parseInt(Marked[1]) - 1).setMark(true);
                } else if (firstCmd == Command.unmark) {
                    taskList.get(Integer.parseInt(Marked[1]) - 1).setMark(false);
                } else if (firstCmd == Command.todo) {
                    String[] ans = Cmd.split("todo ");
                    if (ans.length == 1) {
                        throw new ToDoException("");
                    }
                    Task tempTask = new ToDo(ans[1]);
                    taskList.add(tempTask);
                    new AddTaskResponse(tempTask, taskList).callback();

                } else if (firstCmd == Command.deadline) {
                    String[] ans = Cmd.split(" /by ");
                    Task tempTask = new Deadline(ans[0].replace("deadline ", "") ,ans[1]);
                    taskList.add(tempTask);
                    new AddTaskResponse(tempTask, taskList).callback();
                } else if (firstCmd == Command.event) {
                    String[] ans = Cmd.split(" /at ");
                    Task tempTask = new Events(ans[0].replace("event ", "") ,ans[1]);
                    taskList.add(tempTask);
                    new AddTaskResponse(tempTask, taskList).callback();
                }
                else {
                    throw new ForeignException("");
                }
            } catch (DukeException e)  {
                e.callback();
            }

        }
    }

    /**
     * Method that stops the Chatbot;
     */
    public void stop() {
        this.isRunning = false;
    }
}
