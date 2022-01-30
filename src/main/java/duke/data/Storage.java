package duke.data;

import duke.dukeexceptions.DukeException;
import duke.dukeexceptions.ForeignException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/***
 * Storage is a class that allows users to access a persist storage
 */
public class Storage {


  private File persistStore;
  private TaskList runningTaskList;
  
  /***
   * Constructs the contents of the Storage with a TaskList
   * @param runningTaskList that to be added to the Storage
   */
  public Storage(TaskList runningTaskList) {
    this.persistStore = new File("data/command.txt");
    this.runningTaskList = runningTaskList;
  }

  /***
   * Function that is run upon initialisation of the storage.
   * Loods the persist storage into a TaskList.
   */
  public void initialiseStorage() {
    File data = new File("data");
    try {
      if (data.exists()) {
      } else {
        data.mkdirs();
      }
      if (persistStore.exists()) {
      } else {
        persistStore.createNewFile();
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
    String[] stringCmdUnits = taskString.split(" \\| ");
    switch (stringCmdUnits[0]) {
    case "T":
      return new ToDo(stringCmdUnits[2], storageToMark(stringCmdUnits[1]));
    case "D":
      return new Deadline(stringCmdUnits[2], storageToMark(stringCmdUnits[1]), stringCmdUnits[3]);
    case "E":
      return new Event(stringCmdUnits[2], storageToMark(stringCmdUnits[1]), stringCmdUnits[3]);
    default:
      return null;
    }
  }

  /***
   * function that loads from permanent storage to the TaskList.
   */
  public TaskList loadFromDisk() {
    try {
      Scanner fileReader = null;
      fileReader = new Scanner(persistStore);
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

  /***
   * function that loads the TaskList into the permanent storage.
   */
  public void loadToDisk(TaskList taskList) throws DukeException {
    try {
      FileWriter fileWriter = new FileWriter(persistStore);
      for (int i = 0; i < taskList.taskLength(); i++) {
        fileWriter.write(taskList.getTask(i).toStore() + "\n");
      }
      fileWriter.close();      
    } catch (IOException e) {
      throw new ForeignException("");
    }
  }
}
