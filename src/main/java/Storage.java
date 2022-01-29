import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;

    public Storage(String p){
        this.filePath = p;
    }

    public List<Task> load(){
        File data = new File(filePath);
        try {
            if(data.createNewFile()){
                return new ArrayList<Task>();
            }
            else{
                return DukeParser.readData(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Task>();
        }
    }

    public void store(List<Task> l) {
        try {
            BufferedWriter b = new BufferedWriter(new FileWriter(filePath, false));
            String s = "";
            for (Task t : l) {
                s += t.storeFormat();
            }
            b.write(s);
            b.close();
        } catch (IOException e) {
            System.out.println("Error when storing");
        }
    }

}
