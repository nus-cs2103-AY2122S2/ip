import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeParser {

    public static ArrayList<Task> readData(File f) throws IOException {
        Scanner s = new Scanner(f);
        ArrayList<Task> ans = new ArrayList<Task>();
        while(s.hasNext()){
            String[] k = s.nextLine().split("\\|");
            if(k[0].equals("T")){
                ToDos j = new ToDos(k[2]);
                if(k[1].equals("true")){
                    j.done = true;
                }
                ans.add(j);
            }
            else if(k[0].equals("D")){
                Task j = new Deadlines(k[2],k[3]);
                if(k[1].equals("true")){
                    j.done = true;
                }
                ans.add(j);
            }
            else if(k[0].equals("E")){
                Task j = new Events(k[2],k[3]);
                if(k[1].equals("true")){
                    j.done = true;
                }
                ans.add(j);
            }
        }
        return ans;
    }

}
