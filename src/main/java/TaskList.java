import java.util.ArrayList;

public class TaskList {
    static ArrayList<Task> tasklist;

    public TaskList(){
        tasklist = new ArrayList<Task>();
    }

    public ArrayList<Task> get(){
        return this.tasklist;
    }

    public static void markTaskNum(int taskNum, String check){
        if (check.equals("true")) {
            tasklist.get(taskNum).mark();
        }
    }

    /**
     * Deletes a specified index (starts from 1) from the input ArrayList and shifts
     * all subsequent task numbers accordingly by +1.
     *
     * @param num index (starts from 1) to delete
     */
    public static void deleter(int num){
        if (num > 0 && num <= Task.totalTask){
            num--;
            System.out.println(" Noted. I've removed this task: ");
            System.out.printf("  [%s][%s] %s\n",tasklist.get(num).type, tasklist.get(num).getStatus(), tasklist.get(num).name);
            tasklist.remove(num);
            Task.totalTask--;
            for(int i = num; i <Task.totalTask; i++){
                tasklist.get(i).decrementNum();
            }
            System.out.printf("Now you have %d tasks in the list.\n", Task.totalTask);
        } else {
            System.out.println("☹ OOPS!!! There is no such task found.");
        }
        Storage.writeAllToFile();
    }

    /**
     * Marks/Unmarks tasks as done/undone. Marking tasks that are already marked will have no change;
     * same with unmarking unmarked tasks.
     *
     * @param input Original input string that was entered
     */
    public static void markTask(String input){
        String[] inputArr = input.split(" ");
        int taskNum = Integer.parseInt(inputArr[1]) - 1;
        Task curr = tasklist.get(taskNum);
        if (input.startsWith("mark")){
            curr.mark();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.printf("  [%s][%s] %s\n", curr.type, curr.getStatus(), curr.name);
        } else {
            curr.unmark();
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.printf("  [%s][%s] %s\n", curr.type, curr.getStatus(), curr.name);
        }
        Storage.writeAllToFile();
    }

    /**
     * Lists all tasks in tasklist. (in input order)
     *
     */
    public static void listAllTask(){
        System.out.printf("Here are the tasks in your list:\n");
        for(int i=0; i<Task.totalTask; i++){
            System.out.print(tasklist.get(i).toString());
        }
        System.out.println("__________________________________________________________________");
    }

    public static void addTask(String name, String time, String type, boolean isReading){
        if (type.equals("D")){
            tasklist.add(new Deadline(name, Task.totalTask, time, isReading));
        } else if (type.equals("E")){
            tasklist.add(new Event(name, Task.totalTask, time, isReading));
        } else {
            tasklist.add(new ToDo(name, Task.totalTask, isReading));
        }
    }
}
