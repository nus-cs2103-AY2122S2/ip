import java.io.*;
import java.util.*;

public class Pikachu {
    ArrayList<String> inputList;
    ArrayList<Boolean> isDone;

    //Constructor
    public Pikachu() {
        inputList = new ArrayList<String>();
        isDone = new ArrayList<Boolean>();
    }

    public void parseInput(String str) {
        String[] split = str.split(" ");

        if (str.toLowerCase().equals("list")) {                        //LIST
            //System.out.println("list command reached!");
            int count = 1;
            for (String s : inputList) {
                if (isDone.get(count-1)) { //task is done
                    System.out.println(count + ". [X] "+s);
                } else {
                    System.out.println(count + ". [ ] "+s);
                }
                count+=1;
            }
            return;
        }

        if (split[0].toLowerCase().equals("mark")) {                  //MARK
            int index = Integer.parseInt(split[1])-1;
            isDone.set(index, true);
            System.out.println("Pikachu has marked this task as done!");
            System.out.println("[X] " + inputList.get(index));
            return;
        }

        if (split[0].toLowerCase().equals("unmark")) {                //UNMARK
            int index = Integer.parseInt(split[1])-1;
            isDone.set(index, false);
            System.out.println("Pikachu has marked this task as not done yet!");
            System.out.println("[ ] " + inputList.get(index));
            return;
        }

        //For non-recognizable inputs
        inputList.add(str);
        isDone.add(false);
        System.out.println(str);
    }
}
