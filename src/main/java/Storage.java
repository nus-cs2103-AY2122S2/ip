package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {

  File taskList;

  static public boolean storageToMark(String mark) {
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

  public void loadFromDisk(File file, ArrayList<Task> taskList) throws FileNotFoundException {
    Scanner fileReader = new Scanner(file);
    while (fileReader.hasNext()) {
      String command = fileReader.nextLine();
      taskList.add(storageToTask(command));
    }
  }

  public void loadToDisk(File file, ArrayList<Task> taskList) throws IOException {
    FileWriter fileWriter = new FileWriter(file);
    for (int i = 0; i < taskList.size(); i++) {
      fileWriter.write(taskList.get(i).toStore() + "\n");
    }
    fileWriter.close();

  }
}
