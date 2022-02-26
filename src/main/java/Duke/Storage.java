package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

class Storage {
    String filePath;
    public Storage(String filePath){
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTaskListFromFile() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(filePath);
        try {
            Scanner scanFile =  new Scanner(f);
            while(scanFile.hasNext()){
                String lineData = scanFile.nextLine();
                String[] taskData = lineData.split("\\|");
                String taskType = taskData[0].trim();
                boolean isCompleted = taskData[1].trim().equals("1");
                String desc = taskData[2].trim();
                Task newTask = null;
                switch(taskType){
                    case "T":
                        newTask = new ToDoTask(desc, isCompleted);
                        break;
                    case "D":
                        String byDateStr = taskData[3].trim();
                        LocalDateTime dt = LocalDateTime.parse(byDateStr, Parser.dtFormat);
                        newTask = new DeadlineTask(desc, isCompleted, dt);
                        break;
                    case "E":
                        String fromDateStr = taskData[3].trim();
                        LocalDateTime fDt = LocalDateTime.parse(fromDateStr, Parser.dtFormat);
                        String toDateStr = taskData[4].trim();
                        LocalDateTime tDt = LocalDateTime.parse(toDateStr, Parser.dtFormat);
                        newTask = new EventTask(desc, isCompleted, fDt, tDt);
                        break;
                }
                if(newTask != null){
                    taskList.add(newTask);
                }
            }
            scanFile.close();
            return taskList;
        }
        catch(FileNotFoundException e){
            // do nothing, no file no load
            throw new DukeException("No file available for loading.");
        }


    }

    public void saveTaskToFile(ArrayList<Task> taskList, Ui uiPrinter){
        File f = new File(filePath);
        try{
            if(!f.exists()) {
                // create data folder if not exist
                f.getParentFile().mkdirs();
                // create duke.txt
                f.createNewFile();
            }

            FileWriter fw = new FileWriter(f);
            for(Task t: taskList){
                fw.write(t.toFileString()+"\n");
            }

            fw.close();
        }
        catch(SecurityException se){
            uiPrinter.printMessage(se.getMessage());
        }
        catch(IOException e){
            uiPrinter.printMessage("Unable to save to filepath data/duke.txt");
        }
    }
}
