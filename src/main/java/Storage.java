import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static void loadTaskListFromFile(ArrayList<Task> taskList){
        File f = new File("data/duke.txt");
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
                        LocalDateTime dt = LocalDateTime.parse(byDateStr, Duke.DukeCommand.dtFormat);
                        newTask = new DeadlineTask(desc, isCompleted, dt);
                        break;
                    case "E":
                        String fromDateStr = taskData[3].trim();
                        LocalDateTime fDt = LocalDateTime.parse(fromDateStr, Duke.DukeCommand.dtFormat);
                        String toDateStr = taskData[4].trim();
                        LocalDateTime tDt = LocalDateTime.parse(toDateStr, Duke.DukeCommand.dtFormat);
                        newTask = new EventTask(desc, isCompleted, fDt, tDt);
                        break;
                }
                if(newTask != null){
                    taskList.add(newTask);
                }
            }
        }
        catch(FileNotFoundException e){
            // do nothing, no file no load
        }
    }

    public static void saveTaskToFile(ArrayList<Task> taskList){
        File f = new File("data/duke.txt");
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
            printMessage(se.getMessage());
        }
        catch(IOException e){
            printMessage("Unable to save to filepath data/duke.txt");
        }
    }
}
