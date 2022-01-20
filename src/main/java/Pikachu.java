import java.io.*;
import java.util.*;
import tasks.*;

public class Pikachu {
    ArrayList<task> inputList;

    //Constructor
    public Pikachu() {
        inputList = new ArrayList<task>();
    }

    public void parseInput(String str) {
        String[] split = str.split(" ");

        if (str.toLowerCase().equals("list")) {                        //LIST
            //System.out.println("list command reached!");
            System.out.println("Here are the tasks in your list:");
            int count = 1;
            String currBool = "[error]"; 
            String currTask = "[error]";
            for (task t : inputList) {
                if (t.isDone) { currBool = "[X]";
                } else { currBool = "[ ]"; }

                if (t.getClass().getSimpleName().equals("ToDo")) { currTask = "[T]";
                } else if (t.getClass().getSimpleName().equals("Deadline")) { currTask = "[D]";
                } else if (t.getClass().getSimpleName().equals("Event")) { currTask = "[E]"; }

                System.out.println(count + ". " + currTask + currBool + " " + t);
                count+=1;
            }
            count-=1;
            System.out.println("Now you have "+count+" task(s) in the list.");
            return;
        }

        if (split[0].toLowerCase().equals("mark")) {                  //MARK
            int index = Integer.parseInt(split[1])-1;
            if (index >= inputList.size()) {                          //Prevent invalid array accesses
                System.out.println("Invalid task number!");
                return;
            }
            inputList.get(index).isDone = true;
            System.out.println("Pikachu has marked this task as done!\n   > "+inputList.get(index));
            return;
        }

        if (split[0].toLowerCase().equals("unmark")) {                //UNMARK
            int index = Integer.parseInt(split[1])-1;
            if (index >= inputList.size()) {                          //Prevent invalid array accesses
                System.out.println("Invalid task number!");
                return;
            }
            inputList.get(index).isDone = false;
            System.out.println("Pikachu has marked this task as not done yet!\n   > "+inputList.get(index));
            return;
        }

        if (split[0].toLowerCase().equals("delete")) {                //DELETE
            int index = Integer.parseInt(split[1])-1;
            if (index >= inputList.size()) {                          //Prevent invalid array accesses
                System.out.println("Invalid task number!");
                return;
            }
            task t = inputList.remove(index);
            System.out.println("Pikachu has deleted this task! You now have "+inputList.size()+" tasks in the list.\n   > " + t);
            return;
        }

        if (split[0].toLowerCase().equals("todo")) {                    //TODO
            String[] split2 = str.split(" ", 2);
            //System.out.printf("For debugging. split2[1] = %s\n", split2[1]);
            ToDo t = new ToDo(split2[1]);
            inputList.add(t);
            System.out.println("Pikachu has added this task to the list! You now have "+inputList.size()+" tasks in the list.");
            System.out.println("[T] [ ] "+t);
            return;
        }

        if (split[0].toLowerCase().equals("deadline")) {               //DEADLINE
            String[] split2 = str.split(" ", 2);
            String[] split3 = split2[1].split("/");
            //System.out.printf("For debugging. split2[1] = %s, split3[1] = %s\n", split2[1], split3[1]);
            Deadline d = new Deadline(split3[0], split3[1]);
            inputList.add(d);
            System.out.println("Pikachu has added this task to the list! You now have "+inputList.size()+" tasks in the list.");
            System.out.println("[D] [ ] "+d);
            return;
        }

        if (split[0].toLowerCase().equals("event")) {                  //EVENT
            String[] split2 = str.split(" ", 2);
            String[] split3 = split2[1].split("/");
            //System.out.printf("For debugging. split2[1] = %s, split3[1] = %s\n", split2[1], split3[1]);
            Event e = new Event(split3[0], split3[1]);
            inputList.add(e);
            System.out.println("Pikachu has added this task to the list! You now have "+inputList.size()+" tasks in the list.");
            System.out.println("[E] [ ] "+e);
            return;
        }

        
        //For non-recognizable inputs
        //inputList.add(new task(str)); //don't think we need to do this
        System.out.println(str);
    }
}
