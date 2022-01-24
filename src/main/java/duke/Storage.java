package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String path;
    private String file_dir;

    public Storage(String path, String file_dir) {
        this.path = path;
        this.file_dir = file_dir;
    }

    public TaskList load() throws FileNotFoundException {
        TaskList list = new TaskList();
        File file = new File(path); // create a File for the given file path
        if (file.exists()) {
            Scanner scanner = new Scanner(file); // create a Scanner using the File as the source
            while (scanner.hasNextLine()) {
                String[] taskLine = scanner.nextLine().split("~");
                Task task;
                String taskType = taskLine[0];
                try {
                    if (taskType.equals("T")) {
                        task = new Todo(taskLine[2]);
                    } else if (taskType.equals("D")) {
                        String dateTime = taskLine[3];
                        //Time is added
                        if(taskLine.length == 5) {
                            dateTime += " " + taskLine[4];
                        }
                        task = new Deadline(taskLine[2], dateTime);
                    } else if (taskType.equals("E")) {
                        String dateTime = taskLine[3];
                        //Time is added
                        if(taskLine.length == 5) {
                            dateTime += " " + taskLine[4];
                        }
                        task = new Event(taskLine[2], dateTime);
                    } else {
                        throw new DukeException("Invalid task was read");
                    }
                    if (taskLine[1].equals("X")) {
                        task.setDone(true);
                    }
                    list.addTaskNoPrint(task);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        return list;
    }

    public void storeTasks(ArrayList<Task> tasks) {
        //Saving the changes back to file
        File file = new File(file_dir);
        //if prev file exists, delete it and replace with new empty file
        try {
            if (!file.exists()) {
                file.mkdir();
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        //Writing to empty txt file
        try {
            FileWriter fileWriter = new FileWriter(path,false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Task t: tasks) {
                String taskToAppend = "";

                //Identify task type
                if (t instanceof Todo) {
                    taskToAppend += "T~";
                } else if(t instanceof Deadline) {
                    taskToAppend += "D~";
                } else if(t instanceof Event) {
                    taskToAppend += "E~";
                }

                //Identify if task is done
                if (t.done) {
                    taskToAppend += "X~";
                } else {
                    taskToAppend += " ~";
                }
                taskToAppend += t.taskName + "~";
                if(t instanceof Deadline) {
                    Deadline tempTask = (Deadline) t;
                    String date = tempTask.date.toString();
                    if(tempTask.time != null) {
                        date += "~" + tempTask.time.toString();
                    }
                    taskToAppend += date.trim();
                } else if (t instanceof Event) {
                    Event tempTask = (Event) t;
                    String date = tempTask.date.toString();
                    if(tempTask.time != null) {
                        date += "~" + tempTask.time.toString();
                    }
                    taskToAppend += date.trim();
                }
                printWriter.println(taskToAppend);
            }
            printWriter.close();
        } catch(IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
