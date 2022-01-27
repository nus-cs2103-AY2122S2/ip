import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list= new ArrayList<>();

    public TaskList(List<String> strList) {
        for (String task : strList) {
            String[] tempList = task.split("\\|");
            Task holder;
            switch (tempList[0]) {
                case "T":
                    holder = new Todo(tempList[2]);
                    break;
                case "D":
                    holder = new Deadline(tempList[2], tempList[3]);
                    break;
                default:
                    holder = new Event(tempList[2], tempList[3]);
                    break;
            }
            if (tempList[1].equals("1")) {
                holder.markAsDone();
            }
            list.add(holder);
        }
    }

    public TaskList() {}

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void todo(String description, List<Task> list) {
        list.add(new Todo(description));
    }

    public void deadline(String description, List<Task> list) {
        String[] restOfPara  = description.split("/by ", 2);
        list.add(new Deadline(restOfPara[0], restOfPara[1]));
    }

    public void event(String description, List<Task> list) {
        String[] restOfPara2 = description.split("/at ", 2);
        list.add(new Event(restOfPara2[0], restOfPara2[1]));

    }

    public void mark(String number, List<Task> list) {
        int taskNum = Integer.parseInt(number) - 1;
        list.get(taskNum).markAsDone();
        System.out.println(list.get(taskNum).toString());
    }

    public void unmark(String number, List<Task> list) {
        int taskNum = Integer.parseInt(number) - 1;
        list.get(taskNum).markAsNotDone();
        System.out.println(list.get(taskNum).toString());
    }

    public void delete(String number, List<Task> list) {
        int taskNum = Integer.parseInt(number) - 1;
        System.out.println(list.get(taskNum).toString());
        list.remove(taskNum);
    }
}
