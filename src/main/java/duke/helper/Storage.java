package duke.helper;

import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;


    public Storage(String filePath) {
        String[] pathArr = filePath.split("/");
        String directoryPath = "/" + pathArr[0];
        try {
            file = new File(System.getProperty("user.dir") + "/" + filePath);
            if (!file.isFile()) {
                File dir;
                dir = new File(System.getProperty("user.dir") + directoryPath);
                dir.mkdir();
                file.createNewFile();
                System.out.println("not a file yet, creating");
            }
        } catch (IOException e) {
            System.out.println("error initiating file");
        }
    }
    public void save(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : taskList){
                fw.write(task.getDetail());
            }
            fw.close();
            System.out.println("saving in progress");
        } catch (IOException e) {
//            System.out.println("saving failed");
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> load(){
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            String nextLine;
            while (sc.hasNextLine()) {
                nextLine = sc.nextLine();
                Task task = Parser.parseFileLine(nextLine);
                taskList.add(task);
            }
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage() + " error loading ");
        }
        return taskList;
    }
}
