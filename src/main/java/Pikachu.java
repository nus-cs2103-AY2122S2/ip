import java.io.*;
import java.util.*;

public class Pikachu {
    ArrayList<String> inputList;

    //Constructor
    public Pikachu() {
        inputList = new ArrayList<String>();
    }

    public void parseInput(String str) {
        inputList.add(str);

        if (str.equals("list")) {                        //LIST COMMAND
            //System.out.println("list command reached!");
            for (String s : inputList) {
                System.out.println(s);
            }
            return;
        }

        System.out.println(str);
    }
}
