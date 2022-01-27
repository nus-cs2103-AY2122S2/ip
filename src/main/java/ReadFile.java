import java.util.ArrayList;

//class to read file and process file into a string format
public class ReadFile {
    public static String readFile(ArrayList<Task> list) {
        int n = list.size() - 1;
        String s = "";

        while (n >= 0) {
            s += list.get(n).toString() + "\n";
            n -= 1;
        }

        return s;
    }
}