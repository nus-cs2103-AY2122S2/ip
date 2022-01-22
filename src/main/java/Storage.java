import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static int saveFile(String path, String fileName, ArrayList<Task> arr) {
        try {
            Files.createDirectories(Paths.get(path));
            String filePath = path + "/" + fileName;
            File myObj = new File(filePath);
            myObj.createNewFile();
            FileWriter writer = new FileWriter(filePath);
            StringBuilder sb = new StringBuilder();
            for(Task t : arr) {
                if(t.toString().charAt(1) == 'T')
                    sb.append(String.format("%s,%s,%s,", t.toString().charAt(1), t.isDone?"T":"F", t.getTaskName()) + ";");
                else
                    sb.append(String.format("%s,%s,%s,%s", t.toString().charAt(1), t.isDone?"T":"F", t.getTaskName(), t.getDesc()) + ";");
            }
            sb.setLength((sb.length() - 1));
            writer.write(sb.toString());
            writer.close();
            return 0;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    public static int loadFile(String filePath, ArrayList<Task> arr) {
        try {
            FileInputStream fs = new FileInputStream(filePath);
            Scanner sc = new Scanner(fs);
            String ss = "";
            while (sc.hasNextLine()) {
                ss += sc.nextLine();
            }
            sc.close();
            for(String s : ss.split(";")) {
                String[] args = s.split(",");
                if(args[0].equals("T")) {
                    arr.add(new ToDoTask(args[2], args[1] == "T"));
                } else if (args[0].equals("D")) {
                    arr.add(new DeadlineTask(args[2], args[1] == "T", args[3]));
                } else if (args[0].equals("E")) {
                    arr.add(new EventTask(args[2], args[1] == "T", args[3]));
                }
            }
            return 0;
        }
        catch (FileNotFoundException e) {
            return -1;
        }
    }
}