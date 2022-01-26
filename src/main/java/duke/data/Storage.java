package main.java.duke.data;

import main.java.duke.dukeexceptions.DukeException;
import main.java.duke.dukeexceptions.ForeignException;
import main.java.duke.task.Deadline;
import main.java.duke.task.Event;
import main.java.duke.task.Task;
import main.java.duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {

  File storageTaskList;
  TaskList runningTaskList;
  
  public Storage(TaskList runningTaskList) {
    this.storageTaskList = new File("data/command.txt");
    this.runningTaskList = runningTaskList;
  }
  
  public void initialiseStorage() {
    File data = new File("data");
    try {
      if (data.exists()) {
      } else {
        data.mkdir();
      }
      if (storageTaskList.exists()) {
      } else {
        storageTaskList.createNewFile();
      }
    } catch (Exception e) {
        e.getMessage();
    } 
  }
  

  static private boolean storageToMark(String mark) {
    if (mark.equals("1")) {
      return true;
    } else {
      return false;
    }
  }

  static private Task storageToTask(String taskString) {
    String[] commandSplit = taskString.split(" \\| ");
    System.out.println(Arrays.toString(commandSplit));
    switch (commandSplit[0]) {
      case "T":
        return new ToDo(commandSplit[2], storageToMark(commandSplit[1]));
      case "D":
        return new Deadline(commandSplit[2], storageToMark(commandSplit[1]), commandSplit[3]);
      case "E":
        return new Event(commandSplit[2], storageToMark(commandSplit[1]), commandSplit[3]);
      default:
        return null;
    }
  }
  

  public TaskList loadFromDisk() {
    try {
      Scanner fileReader = null;
      fileReader = new Scanner(this.storageTaskList);
      TaskList taskList = new TaskList();
      while (fileReader.hasNext()) {
        String command = fileReader.nextLine();
        taskList.addTask(storageToTask(command));
      }
      return taskList;
    } catch (Exception e) {
      e.getMessage();
      return new TaskList();
    }
  }

  public void loadToDisk(TaskList taskList) throws DukeException {
    try {
      FileWriter fileWriter = new FileWriter(this.storageTaskList);
      for (int i = 0; i < taskList.taskLength(); i++) {
        fileWriter.write(taskList.getTask(i).toStore() + "\n");
      }
      fileWriter.close();      
    } catch (IOException e) {
      throw new ForeignException("");
    }
  }
}
