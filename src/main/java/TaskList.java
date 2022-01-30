/*
    ToDoList class creates a To Do List to simulate a normal day to day task list.
*/

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

class TaskList {
    ArrayList<Task> lst;
    ArrayList<Deadline> dlLst;
    ArrayList<Event> eLst;

    public TaskList() {
        lst = new ArrayList<Task>(100);
        dlLst = new ArrayList<Deadline>();
        eLst = new ArrayList<Event>();
    }

    public TaskList(String loadData) {
        lst = new ArrayList<Task>(100);
        dlLst = new ArrayList<Deadline>();
        eLst = new ArrayList<Event>();
        
        String[] loadDataSplit = loadData.split("\n");
        
        for (int i = 0; i < loadDataSplit.length; i++) {
            String curr = loadDataSplit[i].trim();
            startUpAddTask(curr);
        }

        System.out.printf("Data had been loaded!! Current number of task: %d\n", this.lst.size());
    }

    /*
        Method to add task item into the to do list.
    */
    public void addItem(Task task) {
        lst.add(task);
        if (task instanceof Deadline) {
            Deadline tmp = (Deadline) task;
            dlLst.add(tmp);
        } else if (task instanceof Event) {
            Event tmp = (Event) task;
            eLst.add(tmp);
        }
        System.out.printf("Got it! I've added this task:\n%s\nTotal tasks on the list: %d\n",
                            task.getItemAndStatus(), this.lst.size());
    }

    /*
        Method to delete task from Task List
    */
    public void deleteTask(int index) {
        Task removedTask = lst.remove(index);
        if (removedTask instanceof Deadline) {
            Deadline tmp = (Deadline) removedTask;
            dlLst.remove(tmp);
        } else if (removedTask instanceof Event) {
            Event tmp = (Event) removedTask;
            dlLst.remove(tmp);
        }
        System.out.printf("Successfully removed the following task:\n%s\nYou have %d tasks remaining!!\n", removedTask.getItemAndStatus(), this.lst.size());
    }

    /*
        Method to print Task List out in order with status of each task.
    */
    public void print() {
        if (lst.size() == 0) {
            System.out.println("There is currently no item on the list!!");
        } else {
            System.out.println("Task List:");
            lst.forEach((item) -> System.out.println((lst.indexOf(item)+1) + ". " + item.getItemAndStatus()));
        }
    }

    /*
        Method to mark item of to do list done.
    */
    public void markItem(int index) {
        lst.get(index).markDone();
    }

    public void printEventOn(LocalDate date) {
        ArrayList<Event> tmp = new ArrayList<Event>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");

        if (eLst.size() != 0) {
            for (int i = 0; i < eLst.size(); i++) {
                if (eLst.get(i).dateCompare(date)) {
                    tmp.add(eLst.get(i));
                }
            }

            if (tmp.size() == 0) {
                System.out.printf("No event on %s!!\n", date.format(dtf));
            } else {
                System.out.printf("%d events on %s:\n", tmp.size(), date.format(dtf));
                tmp.forEach((item) -> System.out.println((tmp.indexOf(item) + 1) + ". " + item.getItemAndStatus()));
            }
        } else {
                System.out.printf("No event on %s!!\n", date.format(dtf));
        }
    }

    public void printDeadlineOn(LocalDate date) {
        ArrayList<Deadline> tmp = new ArrayList<Deadline>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");

        if (dlLst.size() != 0) {
            for (int i = 0; i < dlLst.size(); i++) {
                if (dlLst.get(i).dateCompare(date)) {
                    tmp.add(dlLst.get(i));
                }
            }

            if (tmp.size() == 0) {
                System.out.printf("No deadline on %s!!\n", date.format(dtf));
            } else {
                System.out.printf("%d deadline item(s) on %s:\n", tmp.size(), date.format(dtf));
                tmp.forEach((item) -> System.out.println((tmp.indexOf(item) + 1) + ". " + item.getItemAndStatus()));
            }
        } else {
            System.out.printf("No deadline on %s!!\n", date.format(dtf));
        }
    }

    /*
        Method to mark item of to do list undone.
    */
    public void unmarkItem(int index) {
        lst.get(index).markUndone();
    }

    private void startUpAddTask(String input) {
        String[] inputSplit = input.split(" ", 3);

        try {
            switch (inputSplit[0]) {
                case "T":
                    ToDos todo = new ToDos(inputSplit[2].trim(), Integer.parseInt(inputSplit[1].trim()));
                    lst.add(todo);
                    break;
                case "D":
                    String[] dlSubSplit = inputSplit[2].split(" /by ", 2);
                    String[] dlSubSplit2 = dlSubSplit[1].split(" ", 2);
                    Deadline dl;
                    if (dlSubSplit2.length == 1 || dlSubSplit2[1].trim().length() == 0) {
                        dl = new Deadline(dlSubSplit[0].trim(), Integer.parseInt(inputSplit[1].trim()), TaskList.getDate(dlSubSplit[1].trim()));
                    } else {
                        dl = new Deadline(dlSubSplit[0].trim(), Integer.parseInt(inputSplit[1].trim()), TaskList.getDate(dlSubSplit2[0]), TaskList.getTime(dlSubSplit2[1]));
                    }
                    lst.add(dl);
                    dlLst.add(dl);
                    break;
                case "E":
                    String[] eSubSplit = inputSplit[2].split(" /at ", 2);
                    String[] eSubSplit2 = eSubSplit[1].split(" ", 2);
                    Event e = new Event(eSubSplit[0].trim(), Integer.parseInt(inputSplit[1].trim()), TaskList.getDate(eSubSplit2[0].trim()), TaskList.getTime(eSubSplit2[1].trim()));
                    lst.add(e);
                    eLst.add(e);
                    break;
            }
        } catch (DateTimeParseException dtpe) {
            throw new SiriException("Error LOADING data!!");
        }

    }

    public int size() {
        return this.lst.size();
    }

    public String saveData() {
        StringBuilder returned = new StringBuilder();

        lst.forEach((item) -> returned.append(item.saveData() + "\n"));

        return returned.toString();
    }

    private static LocalDate getDate(String dateString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate ld = LocalDate.parse(dateString, dtf);

        return ld;
    }

    private static LocalTime getTime(String timeString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime lt = LocalTime.parse(timeString, dtf);

        return lt;
    }
}