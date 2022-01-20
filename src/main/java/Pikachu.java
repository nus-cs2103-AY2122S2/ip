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

                System.out.println("   " + count + ". " + currTask + currBool + " " + t);
                count+=1;
            }
            count-=1;
            System.out.println("Now you have "+count+" task(s) in the list.");
            return;
        }

        if (split[0].toLowerCase().equals("mark")) {                  //MARK
            int index = 0;
            try { index = Integer.parseInt(split[1])-1;
            } catch (Exception e) { System.out.println("Invalid input for mark!"); return; }
            if (index >= inputList.size() || index <= -1) {                          //Prevent invalid array accesses
                System.out.println("Invalid task number!");
                return;
            }
            task t = inputList.get(index);
            t.isDone = true;

            String currBool = "[error]"; 
            String currTask = "[error]";
            if (t.isDone) { currBool = "[X]";
            } else { currBool = "[ ]"; }
            if (t.getClass().getSimpleName().equals("ToDo")) { currTask = "[T]";
            } else if (t.getClass().getSimpleName().equals("Deadline")) { currTask = "[D]";
            } else if (t.getClass().getSimpleName().equals("Event")) { currTask = "[E]"; }
            System.out.println("Pikachu has marked this task as done!\n   > "+currTask+currBool+" "+t);
            return;
        }

        if (split[0].toLowerCase().equals("unmark")) {                //UNMARK
            int index = 0;
            try { index = Integer.parseInt(split[1])-1;
            } catch (Exception e) { System.out.println("Invalid input for unmark!"); return; }
            if (index >= inputList.size() || index <= -1) {                          //Prevent invalid array accesses
                System.out.println("Invalid task number!");
                return;
            }
            task t = inputList.get(index);
            t.isDone = false;

            String currBool = "[error]"; 
            String currTask = "[error]";
            if (t.isDone) { currBool = "[X]";
            } else { currBool = "[ ]"; }
            if (t.getClass().getSimpleName().equals("ToDo")) { currTask = "[T]";
            } else if (t.getClass().getSimpleName().equals("Deadline")) { currTask = "[D]";
            } else if (t.getClass().getSimpleName().equals("Event")) { currTask = "[E]"; }
            System.out.println("Pikachu has marked this task as not done yet!\n   > "+currTask+currBool+" "+t);
            return;
        }

        if (split[0].toLowerCase().equals("delete")) {                //DELETE
            int index = 0;
            try { index = Integer.parseInt(split[1])-1;
            } catch (Exception e) { System.out.println("Invalid input for delete!"); return; }
            if (index >= inputList.size() || index <= -1) {                          //Prevent invalid array accesses
                System.out.println("Invalid task number!");
                return;
            }
            task t = inputList.remove(index);

            String currBool = "[error]"; 
            String currTask = "[error]";
            if (t.isDone) { currBool = "[X]";
            } else { currBool = "[ ]"; }
            if (t.getClass().getSimpleName().equals("ToDo")) { currTask = "[T]";
            } else if (t.getClass().getSimpleName().equals("Deadline")) { currTask = "[D]";
            } else if (t.getClass().getSimpleName().equals("Event")) { currTask = "[E]"; }
            System.out.println("Pikachu has deleted this task!\n   > "+currTask+currBool+" "+t);
            System.out.println("You now have "+inputList.size()+" tasks in the list.");
            return;
        }

        if (split[0].toLowerCase().equals("todo")) {                    //TODO
            String[] split2 = str.split(" ", 2);
            //System.out.printf("For debugging. split2[1] = %s\n", split2[1]);
            try {
            ToDo t = new ToDo(split2[1]);
            inputList.add(t);
            System.out.println("Pikachu has added this task to the list!");
            System.out.println("   > [T][ ] "+t);
            System.out.println("You now have "+inputList.size()+" tasks in the list.");
            return;
            } catch (Exception e) {
                System.out.println("Task description is empty!");
                return;
            }
        }

        if (split[0].toLowerCase().equals("deadline")) {               //DEADLINE
            try {
            String[] split2 = str.split(" ", 2);
            String[] split3 = split2[1].split("/");
            //System.out.printf("For debugging. split2[1] = %s, split3[1] = %s\n", split2[1], split3[1]);
            Deadline d = new Deadline(split3[0], split3[1]);
            inputList.add(d);
            System.out.println("Pikachu has added this task to the list!");
            System.out.println("   > [D][ ] "+d);
            System.out.println("You now have "+inputList.size()+" tasks in the list.");
            return;
            } catch (Exception e) {
                System.out.println("Task description is empty/No deadline has been specified! Please specify a deadline using /.");
                return;
            }
        }

        if (split[0].toLowerCase().equals("event")) {                  //EVENT
            try {
            String[] split2 = str.split(" ", 2);
            String[] split3 = split2[1].split("/");
            //System.out.printf("For debugging. split2[1] = %s, split3[1] = %s\n", split2[1], split3[1]);
            Event e = new Event(split3[0], split3[1]);
            inputList.add(e);
            System.out.println("Pikachu has added this task to the list!");
            System.out.println("   > [E][ ] "+e);
            System.out.println("You now have "+inputList.size()+" tasks in the list.");
            return;
            } catch (Exception e) {
                System.out.println("Task description is empty/No event duration has been specified! Please specify a duration using /.");
                return;
            }
        }

        
        //For non-recognizable inputs
        System.out.println("Pikachu does not understand...");
    }
}
