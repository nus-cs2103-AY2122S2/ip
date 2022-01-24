package main.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This is the chatbot object.
 */
public class ChatBot {
  String name = "Duke";
  boolean isRunning = true;
  ArrayList<String> mem = new ArrayList<>();
  ArrayList<Task> taskList = new ArrayList<>();
  ArrayList<String> commandList = new ArrayList<>(
      Arrays.asList("bye", "delete", "list", "mark", "unmark", "todo", "deadline", "event"));

  /**
   * method that starts the chatbot.
   * 
   * @throws IOException
   */
  public void run() throws IOException {

    Response start = new StartResponse();
    start.callback();
    Scanner sc = new Scanner(System.in);

    // Create data file
    Storage storage = new Storage();

    File data = new File("data");
    if (data.exists()) {
    } else {
      data.mkdir();
    }

    File storageTaskList = new File("data/command.txt");
    if (storageTaskList.exists()) {
      storage.loadFromDisk(storageTaskList, taskList);
    } else {
      storageTaskList.createNewFile();
    }

    while (isRunning) {
      String Cmd = sc.nextLine();
      String[] Marked = Cmd.split(" ");

      try {
        if (!commandList.contains(Marked[0])) {
          throw new ForeignException("");
        }
        Command firstCmd = Command.valueOf(Marked[0]);
        if (firstCmd == Command.bye) {
          Response stop = new StopResponse();
          stop.callback();
          this.stop();
          storage.loadToDisk(storageTaskList, taskList);
          break;
        } else if (firstCmd == Command.delete) {
          int index = Integer.parseInt(Marked[1]);
          Task rm = taskList.get(index - 1);
          taskList.remove(index - 1);
          Response curr = new DeleteResponse(rm, taskList.size());
          curr.callback();
          storage.loadToDisk(storageTaskList, taskList);
        } else if (firstCmd == Command.list) {
          Response lst = new ListResponse(taskList);
          lst.callback();
        } else if (firstCmd == Command.mark) {
          taskList.get(Integer.parseInt(Marked[1]) - 1).setMark(true);
          storage.loadToDisk(storageTaskList, taskList);
        } else if (firstCmd == Command.unmark) {
          taskList.get(Integer.parseInt(Marked[1]) - 1).setMark(false);
          storage.loadToDisk(storageTaskList, taskList);
        } else if (firstCmd == Command.todo) {
          String[] ans = Cmd.split("todo ");
          if (ans.length == 1) {
            throw new ToDoException("");
          }
          Task tempTask = new ToDo(ans[1]);
          taskList.add(tempTask);
          new AddTaskResponse(tempTask, taskList).callback();
          storage.loadToDisk(storageTaskList, taskList);

        } else if (firstCmd == Command.deadline) {
          String[] ans = Cmd.split(" /by ");
          Task tempTask = new Deadline(ans[0].replace("deadline ", ""), ans[1]);
          taskList.add(tempTask);
          new AddTaskResponse(tempTask, taskList).callback();
          storage.loadToDisk(storageTaskList, taskList);

        } else if (firstCmd == Command.event) {
          String[] ans = Cmd.split(" /at ");
          Task tempTask = new Events(ans[0].replace("event ", ""), ans[1]);
          taskList.add(tempTask);
          new AddTaskResponse(tempTask, taskList).callback();
          storage.loadToDisk(storageTaskList, taskList);

        } else {
          throw new ForeignException("");
        }
      } catch (DukeException e) {
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
