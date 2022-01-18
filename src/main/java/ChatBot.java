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

        while (isRunning) {
            Scanner sc = new Scanner(System.in);
            String Cmd = sc.nextLine();
            String[] Marked = Cmd.split(" ");
            try {
                if (Cmd.equals("bye")) {
                    Response stop = new StopResponse();
                    stop.callback();
                    this.stop();
                    break;
                } else if (Marked[0].equals("delete")) {
                    int index = Integer.parseInt(Marked[1]);
                    Task rm = taskList.get(index - 1);
                    taskList.remove(index - 1);
                    Response curr = new DeleteResponse(rm, taskList.size());
                    curr.callback();
                }else if (Cmd.equals("list")) {
                    Response lst = new ListResponse(taskList);
                    lst.callback();
                } else if (Marked[0].equals("mark")) {
                    taskList.get(Integer.parseInt(Marked[1]) - 1).setMark(true);
                } else if (Marked[0].equals("unmark")) {
                    taskList.get(Integer.parseInt(Marked[1]) - 1).setMark(false);
                } else if (Marked[0].equals("todo")) {
                    String[] ans = Cmd.split("todo ");
                    if (ans.length == 1) {
                        throw new ToDoException("");
                    }
                    Task tempTask = new ToDo(ans[1]);
                    taskList.add(tempTask);
                    new AddTaskResponse(tempTask, taskList).callback();

                } else if (Marked[0].equals("deadline")) {
                    String[] ans = Cmd.split(" /by ");
                    Task tempTask = new Deadline(ans[0].replace("deadline ", "") ,ans[1]);
                    taskList.add(tempTask);
                    new AddTaskResponse(tempTask, taskList).callback();
                } else if (Marked[0].equals("event")) {
                    String[] ans = Cmd.split(" /at ");
                    Task tempTask = new Events(ans[0].replace("event ", "") ,ans[1]);
                    taskList.add(tempTask);
                    new AddTaskResponse(tempTask, taskList).callback();
                }
                else {
                    throw new ForeignException("");
                }
            } catch (ToDoException e)  {
                System.out.println(
                        "____________________________________________________________"
                );
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                System.out.println(
                        "____________________________________________________________"
                );
            } catch (ForeignException e) {
                System.out.println(
                        "____________________________________________________________"
                );
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(
                        "____________________________________________________________"
                );
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
